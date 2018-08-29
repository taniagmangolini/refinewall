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

					/*
					 * Files.write(Paths.get(EXPORT_FOLDER + System.getProperty("file.separator") +
					 * folderName + System.getProperty("file.separator") + resultFolder +
					 * System.getProperty("file.separator") + fileName), export.getBytes());
					 */

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
