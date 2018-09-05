package br.com.usp.lafieco.service.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.usp.lafieco.entity.BlastResult;
import br.com.usp.lafieco.exception.CustomException;
import br.com.usp.lafieco.service.interfaces.IFileService;

@Component
public class FileService implements IFileService {

	@Autowired
	private MessageSource messageSource;

	private static String SEQUENCE_SEPARATOR = ",";

	private static String EXPORT_FOLDER = System.getProperty("file.separator") + "tmp"
			+ System.getProperty("file.separator") + "refine_export";

	private static String BLAST_JOBS_PREFIX = "BLAST_JOBS_";

	private static String BLAST_RESULT_FOLDER = "RESULT";

	private static String BLAST_ERROR_FILE = "BLAST_ERROR_";

	private static String TXT = ".txt";

	public List<String> readFile(String fileName, String folder) {

		List<String> lines = null;

		try {

			File fileToRead = new File(
					EXPORT_FOLDER + System.getProperty("file.separator") + folder + System.getProperty("file.separator")
							+ BLAST_RESULT_FOLDER + System.getProperty("file.separator") + fileName);

			System.out.println("fileToRead " + fileToRead);

			lines = Files.readAllLines(Paths.get(fileToRead.getPath()));

		} catch (IOException e) {
			e.printStackTrace();
			throw new CustomException(
					messageSource.getMessage("messages.errorReadFile", new Object[] { fileName, folder }, Locale.US));
		}

		return lines;
	}

	public Map<String, String> processMultipleSequenceFile(MultipartFile file) {

		List<String> sequences = new ArrayList<String>();

		Map<String, String> sequencesMap = new HashMap<String, String>();

		InputStream inputFS;

		try {

			inputFS = file.getInputStream();

			sequences = processCSVFile(inputFS);

			for (int i = 0; i < sequences.size(); i++) {

				String[] idAndSequence = null;

				if (sequences != null && !sequences.get(i).trim().equalsIgnoreCase("")) {

					idAndSequence = sequences.get(i).split(":");

					sequencesMap.put(idAndSequence[0].trim(), idAndSequence[1].trim());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new CustomException(messageSource.getMessage("messages.errorInputFile", new Object[] {}, Locale.US));
		}
		return sequencesMap;
	}

	public List<String> processCSVFile(InputStream inputFS) {

		List<String> sequences = new ArrayList<String>();

		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(inputFS));

			String line;

			StringBuilder fileContent = new StringBuilder();

			while ((line = reader.readLine()) != null) {

				fileContent.append(line);
			}

			String[] sequencesArray = fileContent.toString().split(SEQUENCE_SEPARATOR);

			sequences.addAll(Arrays.asList(sequencesArray));

			reader.close();

		} catch (IOException e) {
			throw new CustomException(messageSource.getMessage("messages.errorCSVFile",
					new Object[] { e.getMessage(), e.getCause() }, Locale.US));
		}

		return sequences;
	}

	public Map<String, String> exportBlastResultMapToFile(Map<String, String> jobResult, List<String> jobIds,
			Map<String, Map<String, String>> sequencesJobs, String folderName) {

		Map<String, String> errors = null;

		if (jobResult != null && !jobResult.isEmpty()) {

			for (String jobId : jobIds) {

				Map<String, String> geneSequence = sequencesJobs.get(jobId);

				String gene = null;

				Iterator it = geneSequence.entrySet().iterator();

				for (Map.Entry<String, String> entry : geneSequence.entrySet()) {
					gene = entry.getKey();
					break;
				}

				if (jobResult.get(jobId) != null && gene != null) {

					this.exportBlastResultToFile(jobId, gene, jobResult.get(jobId), errors, folderName);

				} else {

					if (errors == null) {
						errors = new HashMap<String, String>();
					}
					errors.put(jobId, messageSource.getMessage("messages.errorToExportJobWithoutResult",
							new Object[] {}, Locale.US));
				}
			}
		}
		return errors;
	}

	public String exportBlastJobsToFile(List<String> jobIds, String folderName) {

		String fileName = null;

		if (jobIds != null && !jobIds.isEmpty()) {

			fileName = BLAST_JOBS_PREFIX + folderName + "_" + Calendar.getInstance().getTimeInMillis() + TXT;

			try {

				FileWriter writer = new FileWriter(EXPORT_FOLDER + System.getProperty("file.separator") + folderName
						+ System.getProperty("file.separator") + fileName);

				String collect = jobIds.stream().collect(Collectors.joining(","));

				System.out.println(collect);

				writer.write(collect);

				writer.close();
			} catch (IOException e) {
				fileName = null;
				throw new CustomException(messageSource.getMessage("messages.exportJobIds",
						new Object[] { e.getMessage() + " - " + e.getCause() }, Locale.US));
			}
		}

		return fileName;
	}

	public void exportBlastResultToFile(String jobId, String gene, String export, Map<String, String> errors,
			String folderName) {

		if (export != null && !export.trim().equalsIgnoreCase("")) {

			String fileName = gene + TXT;

			try {

				String resultFolder = this.getFolderForSequenceFile(EXPORT_FOLDER + System.getProperty("file.separator")
						+ folderName + System.getProperty("file.separator") + BLAST_RESULT_FOLDER, true);

				if (resultFolder != null) {

					Files.write(Paths.get(resultFolder + System.getProperty("file.separator") + fileName),
							export.getBytes());
				}

			} catch (IOException e) {
				System.out.println("ERRORS = > " + jobId + " - " + e.getMessage() + " - " + e.getCause());
				errors.put(jobId + "-" + gene, messageSource.getMessage("messages.errorToExport",
						new Object[] { e.getMessage() + " - " + e.getCause() }, Locale.US));
			}

		} else {

			errors.put(jobId + "-" + gene,
					messageSource.getMessage("messages.errorExportEmpty", new Object[] { jobId }, Locale.US));
		}
	}

	/**
	 * Sample Item to process: >TR:C5Z0W2_SORBI C5Z0W2 Uncharacterized protein
	 * OS=Sorghum bicolor OX=4558 GN=SORBI_3009G194900 PE=4 SV=1 Length=229
	 * 
	 * Score = 319 bits (818), Expect = 1e-110 Identities = 153/163 (94%), Positives
	 * = 157/163 (96%), Gaps = 1/163 (1%)
	 * 
	 * @param jobId
	 * @param gene
	 * @param errors
	 * @param folderName
	 */
	public Map<String, BlastResult> processBlastResultFile(String gene, String folderName) {

		Map<String, BlastResult> mapResult = new HashMap<String, BlastResult>();

		try {

			List<String> lines = this.readFile(gene + TXT, folderName);

			if (lines != null && !lines.isEmpty()) {

				StringBuilder textLine = new StringBuilder();

				String identifier = null;

				for (String line : lines) {

					if (line.contains(">TR:") || line.contains(">SP:")) {

						if (identifier != null) {

							if (mapResult.get(identifier) != null) {

								mapResult.get(identifier).setFullText(textLine.toString());
								
								mapResult.get(identifier).setSucest(gene);
							}

							identifier = null;
						}

						textLine = new StringBuilder();

						textLine.append(line);

						textLine.append(" ");

						String[] elements = line.split(" ");

						if (elements != null && elements[0] != null) {

							identifier = elements[0];

							mapResult.put(identifier, new BlastResult());
						}

					} else if (identifier != null && !line.trim().equalsIgnoreCase("")) {

						textLine.append(" ");

						textLine.append(line);

						textLine.append(" ");
					}
				}

				if (!mapResult.isEmpty()) {

					Iterator it = mapResult.entrySet().iterator();

					for (Map.Entry<String, BlastResult> entry : mapResult.entrySet()) {

						String id = entry.getKey();

						BlastResult blastResult = entry.getValue();

						/*
						 * Sample:
						 * ">TR:A0A068URA5_COFCA A0A068URA5 Uncharacterized protein OS=Coffea canephora OX=49390   GN=GSCOC_T00033080001 PE=4 SV=1  Length=1946   Score = 43.9 bits (102),  Expect = 2e-05   Identities = 19/23 (83%), Positives = 22/23 (96%), Gaps = 0/23 (0%)  Query  1     SRGLQISRILGGHKKDRAARNKE  23               SRGLQISRILGGH+KDR +RNK+  Sbjct  1924  SRGLQISRILGGHRKDRTSRNKD  1946 "
						 */
						String fullText = blastResult.getFullText();

						if (fullText != null && !fullText.trim().equalsIgnoreCase("")) {
							
							//remove extra spaces
							fullText = fullText.replaceAll("( )+", " ");
							
							// extract TR:A0A068URA5_COFCA parts
							String db_unique = fullText.substring(0, id.length());
							String[] parts = db_unique.split(":");
							blastResult.setDb(parts[0].replaceAll(">", "")); 
							blastResult.setUniqueIdentifier(parts[1].trim());

							// remove TR:A0A068URA5_COFCA part from the string
							fullText = fullText.substring(id.length()).trim();

							// get the entry name: A0A068URA5
							String entryName = fullText.substring(0, fullText.indexOf(" "));
							fullText = fullText.substring(fullText.indexOf(" ")).trim();
							blastResult.setEntryName(entryName.trim());

							// get the protein name: Uncharacterized protein 
							String proteinName = fullText.substring(0, fullText.indexOf("OS="));
							
							fullText = fullText.substring(fullText.indexOf("OS=")).trim();
							blastResult.setProteiName(proteinName.trim());

							// get the organism name:  OS=Coffea canephora
							String organismName = fullText.substring(0, fullText.indexOf("OX="));
							String[] organismNameParts = organismName.split("=");
							fullText = fullText.substring(fullText.indexOf("OX=")).trim();
							blastResult.setOrganismName(organismNameParts[1].trim());
							
							//OX=49390   GN=GSCOC_T00033080001 PE=4 SV=1 
							fullText = fullText.substring(0, fullText.indexOf("Length=")).trim();
							String[] parts2 = fullText.split(" ");
							for(int i = 0; i < parts2.length; i++) {
								
								if(parts2[0].contains("OX=")) {
									
									String[] OXParts = parts2[i].split("=");
									blastResult.setOrganismIdentifier(OXParts[1].trim());
									
								} else if(parts2[0].contains("GN=")) {
									
									String[] GNParts = parts2[i].split("=");
									blastResult.setGeneName(GNParts[1].trim());
									
								} else if(parts2[0].contains("PE=")) {
									
									String[] PEParts = parts2[i].split("=");
									blastResult.setProteinExistence(Integer.parseInt(PEParts[1].trim()));
									
								} else if(parts2[0].contains("SV=")) {
									
									String[] SVParts = parts2[i].split("=");
									blastResult.setSequenceVersion(Integer.parseInt(SVParts[1]));
								}
							}
						}
					}
				}
			}

		} catch (RuntimeException e) {
			System.out.println("ERRORS = > " + e.getMessage() + " - " + e.getCause());
		}

		return mapResult;
	}

	public void exportErrors(Map<String, String> errors, String folderName) {

		if (errors != null && !errors.isEmpty()) {

			String fileName = BLAST_ERROR_FILE + Calendar.getInstance().getTimeInMillis() + TXT;

			try {

				for (String key : errors.keySet()) {

					if (errors.get(key) != null) {

						Files.write(Paths.get(EXPORT_FOLDER + System.getProperty("file.separator") + folderName
								+ System.getProperty("file.separator") + fileName), errors.get(key).getBytes());
					}
				}

			} catch (IOException e) {
				System.out.println("#### ERROR TO EXPORT ERRORS ! " + e.getMessage() + e.getCause());
			}
		}
	}

	public void deleteTxtFile(String fileName, String folderName) {
		try {

			File file = new File(EXPORT_FOLDER + System.getProperty("file.separator") + folderName
					+ System.getProperty("file.separator") + fileName + TXT);

			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!");
			} else {
				System.out.println("Delete operation is failed.");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public File checkIfExistsBlastJobFile(String sequencesFolder) {

		String fileName = BLAST_JOBS_PREFIX + sequencesFolder + TXT;

		return checkIfExistsTxtFile(EXPORT_FOLDER + System.getProperty("file.separator") + sequencesFolder
				+ System.getProperty("file.separator") + fileName);
	}

	public File checkIfExistsTxtFile(String fileToCheck) {

		File file = null;

		try {

			file = new File(fileToCheck);

			if (!file.exists()) {
				file = null;
			}

		} catch (Exception e) {
			file = null;
			e.printStackTrace();
		}

		return file;
	}

	public String getFolderForSequenceFile(String sequencesFile, Boolean fullName) {

		String sequencesFileFolder = null;

		File file = null;

		if (sequencesFile != null) {

			if (fullName) {

				sequencesFileFolder = sequencesFile;
				file = new File(sequencesFileFolder);

			} else {

				Integer extensionBeginning = sequencesFile.lastIndexOf(".");

				if (extensionBeginning != -1) {

					sequencesFileFolder = sequencesFile.substring(0, extensionBeginning);

				} else {
					sequencesFileFolder = sequencesFile;
				}

				file = new File(EXPORT_FOLDER + System.getProperty("file.separator") + sequencesFileFolder);
			}

			// create a folder for the sequence file
			if (!file.exists()) {
				if (file.mkdirs()) {
					System.out.println("Directory was created!");
				} else {
					System.out.println("Failed to create directory!");
				}
			}

			// if (file.exists() && fullName) {
			// sequencesFileFolder = file.getAbsolutePath();
			// }

		}

		return sequencesFileFolder;
	}

}
