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

import br.com.usp.lafieco.exception.CustomException;
import br.com.usp.lafieco.model.BlastResult;
import br.com.usp.lafieco.model.Sucest;
import br.com.usp.lafieco.model.SucestSequence;
import br.com.usp.lafieco.repository.SucestRepository;
import br.com.usp.lafieco.service.interfaces.IFileService;

@Component
public class FileService implements IFileService {

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private SucestRepository sucestRepository;

	private static String SEQUENCE_SEPARATOR = "\\$";

	// LINUX
	private static String EXPORT_FOLDER = System.getProperty("file.separator") + "tmp"
			+ System.getProperty("file.separator") + "refine_export";

	// WINDOWS
	/*
	 * private static String EXPORT_FOLDER = "C:"+
	 * System.getProperty("file.separator") + "tmp" +
	 * System.getProperty("file.separator") + "refine_export";
	 * 
	 */

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

	public Map<String, Sucest> processMultipleSequenceFile(MultipartFile file) {

		List<String> sequences = new ArrayList<String>();

		Map<String, Sucest> sequencesMap = new HashMap<String, Sucest>();

		InputStream inputFS;

		try {

			inputFS = file.getInputStream();

			sequences = processCSVFile(inputFS);

			for (int i = 0; i < sequences.size(); i++) {

				String[] idAndSequence = null;

				if (sequences != null && !sequences.get(i).trim().equalsIgnoreCase("")) {

					idAndSequence = sequences.get(i).split("#");

					Sucest sucest = new Sucest();

					sucest.setGene(idAndSequence[0].trim());

					sucest.setDescription(idAndSequence[1].trim());

					List<SucestSequence> sucestSequences = new ArrayList<SucestSequence>();
					SucestSequence sucestSequence = new SucestSequence();
					sucestSequence.setSequence(idAndSequence[2].trim());
					sucestSequence.setSucest(sucest);
					sucestSequences.add(sucestSequence);
					sucest.setSequences(sucestSequences);

					sequencesMap.put(sucest.getGene(), sucest);
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
			Map<String, Sucest> sucestJobs, String folderName) {

		Map<String, String> errors = null;

		if (jobResult != null && !jobResult.isEmpty()) {

			for (String jobId : jobIds) {

				Sucest sucest = sucestJobs.get(jobId);

				if (jobResult.get(jobId) != null && sucest != null && sucest.getGene() != null) {

					// export the file
					this.exportBlastResultToFile(jobId, sucest.getGene(), jobResult.get(jobId), errors, folderName);

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

	public Map<String, BlastResult> processBlastResultFile(List<String> lines) {
		return processBlastResultFile(null, null, lines);
	}

	public Map<String, BlastResult> processBlastResultFile(String gene, String folderName) {
		return processBlastResultFile(gene, folderName, null);
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
	public Map<String, BlastResult> processBlastResultFile(String gene, String folderName, List<String> lines) {

		Map<String, BlastResult> mapResult = new HashMap<String, BlastResult>();

		try {

			if (lines == null) {

				lines = this.readFile(gene + TXT, folderName);
			}

			if (lines != null && !lines.isEmpty()) {

				StringBuilder textLine = new StringBuilder();

				String identifier = null;

				for (String line : lines) {

					if (line.contains(">TR:") || line.contains(">SP:")) {

						if (identifier != null) {

							if (mapResult.get(identifier) != null) {

								mapResult.get(identifier).setFullText(textLine.toString());

								if (gene != null) {
									mapResult.get(identifier).setSucestBusca(gene);
								}
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

				// if there is just one result
				if (!mapResult.isEmpty() && mapResult.size() == 1 && identifier != null) {
					if (mapResult.get(identifier) != null) {

						mapResult.get(identifier).setFullText(textLine.toString());

						mapResult.get(identifier).setSucestBusca(gene);
					}
				}

				if (!mapResult.isEmpty()) {

					Iterator it = mapResult.entrySet().iterator();

					for (Map.Entry<String, BlastResult> entry : mapResult.entrySet()) {

						String id = entry.getKey();

						BlastResult blastResult = entry.getValue();

						/*
						 * Line Sample:
						 * ">TR:A0A068URA5_COFCA A0A068URA5 Uncharacterized protein OS=Coffea canephora OX=49390   GN=GSCOC_T00033080001 PE=4 SV=1  Length=1946   Score = 43.9 bits (102),  Expect = 2e-05   Identities = 19/23 (83%), Positives = 22/23 (96%), Gaps = 0/23 (0%)  Query  1     SRGLQISRILGGHKKDRAARNKE  23               SRGLQISRILGGH+KDR +RNK+  Sbjct  1924  SRGLQISRILGGHRKDRTSRNKD  1946 "
						 */
						String fullText = blastResult.getFullText();

						if (fullText != null && !fullText.trim().equalsIgnoreCase("")) {

							// remove extra spaces
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
							blastResult.setProteinName(proteinName.trim());

							// OX=49390 GN=GSCOC_T00033080001 PE=4 SV=1
							Integer OSBeginPosition = fullText.trim().indexOf("OS=");
							Integer OXBeginPosition = fullText.trim().indexOf("OX=");
							Integer GNBeginPosition = fullText.trim().indexOf("GN=");
							Integer PEBeginPosition = fullText.trim().indexOf("PE=");
							Integer SVBeginPosition = fullText.trim().indexOf("SV=");
							Integer lengthBeginPosition = fullText.trim().indexOf("Length=");
							Integer scoreBeginPosition = fullText.trim().indexOf("Score =");
							Integer expectBeginPosition = fullText.trim().indexOf("Expect =");
							Integer identitiesBeginPosition = fullText.trim().indexOf("Identities =");
							Integer positivesBeginPosition = fullText.trim().indexOf("Positives =");
							Integer gapsBeginPosition = fullText.trim().indexOf("Gaps =");
							Integer queryBeginPosition = fullText.trim().indexOf("Query");

							// OS
							if (OSBeginPosition != -1 && OXBeginPosition != 1) {
								String[] OSParts = fullText.substring(OSBeginPosition, OXBeginPosition).trim()
										.split("=");
								blastResult.setOrganismName(OSParts[1].trim());
							}

							// OX
							String[] OXParts = null;
							if (OXBeginPosition != -1 && GNBeginPosition != -1) {

								OXParts = fullText.substring(OXBeginPosition, GNBeginPosition).trim().split("=");

							} else if (OXBeginPosition != -1 && PEBeginPosition != -1) {

								OXParts = fullText.substring(OXBeginPosition, PEBeginPosition).trim().split("=");
							}

							blastResult.setOrganismIdentifier(OXParts[1].trim());

							// GN
							if (GNBeginPosition != -1 && PEBeginPosition != -1) {
								String[] GNParts = fullText.substring(GNBeginPosition, PEBeginPosition).trim()
										.split("=");
								blastResult.setGeneName(GNParts[1].trim());
							}

							// PE
							if (PEBeginPosition != -1 && SVBeginPosition != -1) {
								String[] PEParts = fullText.substring(PEBeginPosition, SVBeginPosition).trim()
										.split("=");
								blastResult.setProteinExistence(Integer.parseInt(PEParts[1].trim()));
							}

							// SV
							if (SVBeginPosition != -1 && lengthBeginPosition != -1) {
								String[] SVParts = fullText.substring(SVBeginPosition, lengthBeginPosition).trim()
										.split("=");
								blastResult.setSequenceVersion(Integer.parseInt(SVParts[1].trim()));
							}

							// Length
							if (lengthBeginPosition != -1 && scoreBeginPosition != -1) {
								String[] lenghtParts = fullText.substring(lengthBeginPosition, scoreBeginPosition)
										.trim().split("=");
								blastResult.setLength(Integer.parseInt(lenghtParts[1].trim()));
							}

							// Score
							if (scoreBeginPosition != -1 && expectBeginPosition != -1) {
								String[] scoreParts = fullText.substring(scoreBeginPosition, expectBeginPosition).trim()
										.split("=");
								String scoreValuePart = scoreParts[1].substring(scoreParts[1].indexOf("(") + 1,
										scoreParts[1].indexOf(")"));
								blastResult.setScore(Integer.parseInt(scoreValuePart));
							}

							// Expect
							if (expectBeginPosition != -1 && identitiesBeginPosition != -1) {
								String[] expectParts = fullText.substring(expectBeginPosition, identitiesBeginPosition)
										.trim().split("=");
								blastResult.setEvalue(expectParts[1].trim());
							}

							// Identities
							if (identitiesBeginPosition != -1 && positivesBeginPosition != -1) {
								String[] identitiesParts = fullText
										.substring(identitiesBeginPosition, positivesBeginPosition).trim().split("=");
								String identitiesValuePart = identitiesParts[1].substring(
										identitiesParts[1].indexOf("(") + 1, identitiesParts[1].indexOf("%)"));
								blastResult.setIdentities(Integer.parseInt(identitiesValuePart));
							}

							// Positives
							if (positivesBeginPosition != -1 && gapsBeginPosition != -1) {
								String[] positivesParts = fullText.substring(positivesBeginPosition, gapsBeginPosition)
										.trim().split("=");
								String positivesValueParts = positivesParts[1]
										.substring(positivesParts[1].indexOf("(") + 1, positivesParts[1].indexOf("%)"));
								blastResult.setPositives(Integer.parseInt(positivesValueParts));

							}

							// Gaps
							if (gapsBeginPosition != -1 && queryBeginPosition != -1) {
								String[] gapsParts = fullText.substring(gapsBeginPosition, queryBeginPosition).trim()
										.split("=");
								String gapsValuesParts = gapsParts[1].substring(gapsParts[1].indexOf("(") + 1,
										gapsParts[1].indexOf("%)"));
								blastResult.setGaps(Integer.parseInt(gapsValuesParts));
							}
						}
					}
				}
			}

		} catch (RuntimeException e) {
			System.out.println(
					messageSource.getMessage("messages.errorProcessFile", new Object[] { gene, folderName }, Locale.US)
							+ e.getMessage() + " - " + e.getCause());
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

	public Map<String, BlastResult> processSucestBlastResultFiles(String folderName, Map<String, Sucest> sucests) {

		Map<String, BlastResult> mapResult = null;

		if (sucests != null && !sucests.isEmpty()) {

			Iterator it = sucests.entrySet().iterator();

			mapResult = new HashMap<String, BlastResult>();

			for (Map.Entry<String, Sucest> entry : sucests.entrySet()) {

				Sucest sucest = entry.getValue();
				
				if(sucest.getGene().equalsIgnoreCase("SCRLFL4103A08.g")) {
					System.out.println("Here");
				}

				if (sucest != null) {

					// process the file
					Map<String, BlastResult> mapResultGene = this.processBlastResultFile(sucest.getGene(), folderName);

					if (mapResultGene != null && !mapResultGene.isEmpty()) {
						mapResult.putAll(mapResultGene);
						
					} else {
						//if does not exists blast matches just save the sucest without any blasts
						sucestRepository.save(sucest);
					}
				}
			}
		}
		return mapResult;
	}
}
