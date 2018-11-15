package br.com.usp.lafieco.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import br.com.usp.lafieco.enums.BlastJobStatusEnum;
import br.com.usp.lafieco.enums.DatabaseEnum;
import br.com.usp.lafieco.enums.ProgramEnum;
import br.com.usp.lafieco.enums.SequenceTypeEnum;
import br.com.usp.lafieco.exception.CustomException;
import br.com.usp.lafieco.model.BlastResult;
import br.com.usp.lafieco.model.Sucest;
import br.com.usp.lafieco.repository.BlastResultRepository;
import br.com.usp.lafieco.service.interfaces.IBlastService;
import br.com.usp.lafieco.service.interfaces.IFileService;

@Component
public class BlastService implements IBlastService {

	private final String BASE_URL = "https://www.ebi.ac.uk/Tools/services/rest/ncbiblast";
	private final String RUN_BLAST = "/run";
	private final String STATUS_BLAST_JOB = "/status/"; // status/{jobId}
	private final String RESULT_BLAST_JOB = "/result/"; // result/{jobId}/out?format=0}
	private final String OUTPUT_TEXT_FORMAT = "/out?format=0";
	private final String SEQUENCE = "sequence";
	private final String EMAIL = "email";
	private final String PROGRAM = "program";
	private final String SEQUENCE_TYPE = "stype";
	private final String DATABASE = "database";
	private final Integer MAX_ATTEMPTS = 100;

	@Autowired
	private IFileService fileService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private BlastResultRepository blastRepository;

	public String runBlast(String sequence, String email) {

		String data = "";

		String url = BASE_URL + RUN_BLAST;

		try {

			System.out.println("running a blast ...");

			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

			// Create the request body as a MultiValueMap
			MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

			body.add(EMAIL, email);
			body.add(SEQUENCE, sequence);
			body.add(PROGRAM, ProgramEnum.BLASTP.getProgram());
			body.add(DATABASE, DatabaseEnum.UNIPROTKB_VIRIDIPLANTAE.getDatabase());
			body.add(SEQUENCE_TYPE, SequenceTypeEnum.PROTEIN.getType());

			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(body,
					headers);

			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

			data = response.getBody();

			System.out.println("got the blast job id: " + data);

		} catch (RuntimeException e) {

			throw new CustomException(messageSource.getMessage("messages.errorBlast",
					new Object[] { e.getMessage() + " -  " + e.getCause() }, Locale.US));
		}

		return data;
	}

	public String checkBlastStatus(String jobId) {

		String url = BASE_URL + STATUS_BLAST_JOB + jobId;
		String status = "";

		try {

			System.out.println("checking blast status for the jobId " + jobId);

			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

			status = restTemplate.getForObject(url, String.class);

			System.out.println("blast job " + jobId + " status: " + status);

		} catch (RuntimeException e) {

			throw new CustomException(messageSource.getMessage("messages.errorBlastJobStatus",
					new Object[] { e.getMessage() + " -  " + e.getCause() }, Locale.US));
		}

		return status;
	}

	public String getBlastResult(String jobId) {

		String url = BASE_URL + RESULT_BLAST_JOB + jobId + OUTPUT_TEXT_FORMAT;

		String blastResult = "";

		try {

			System.out.println("checking blast result for the jobId " + jobId);

			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

			blastResult = restTemplate.getForObject(url, String.class);

			//System.out.println("blast result: " + blastResult);

		} catch (RuntimeException e) {

			throw new CustomException(messageSource.getMessage("messages.errorBlastJobStatus",
					new Object[] { e.getMessage() + " -  " + e.getCause() }, Locale.US));
		}

		return blastResult;
	}

	public List<BlastResult> runBlastSequence(String sequence, String email) {

		Integer attempts = 0;

		String jobId = this.runBlast(sequence, email);

		Map<String, BlastResult> blastResult = null;

		List<BlastResult> blastResultFiltered = null;

		if (jobId != null) {

			Map<String, String> jobResult = new HashMap<String, String>();

			while ((jobResult.size() == 0) && attempts <= MAX_ATTEMPTS) {

				String status = this.checkBlastStatus(jobId);

				if (status.equals(BlastJobStatusEnum.FINISHED.getStatus())) {

					String result = this.getBlastResult(jobId);

					jobResult.put(jobId, result);

					List<String> lines = Arrays.asList(result.split("\\n"));

					blastResult = fileService.processBlastResultFile(lines);

				} else {

					attempts += 1;
				}

			}

			if (blastResult != null && !blastResult.isEmpty()) {

				blastResultFiltered = new ArrayList<BlastResult>();

				for (Map.Entry<String, BlastResult> entry : blastResult.entrySet()) {
					
					blastResultFiltered.add(entry.getValue());

				}

			}

		}

		return blastResultFiltered;
	}

	public void runBlastMultipleSequences(MultipartFile file, String email, Boolean blastFilesAvailable) {

		Map<String, Sucest> sucests = fileService.processMultipleSequenceFile(file);

		Map<String, Sucest> sucestJobs = null;

		List<String> jobIds = null;

		Map<String, String> jobResult = null;

		Map<String, String> errors = new HashMap<String, String>();

		String folderName = null;

		if (sucests != null && !sucests.isEmpty()) {

			if (!blastFilesAvailable) {

				sucestJobs = new HashMap<String, Sucest>();

				jobResult = new HashMap<String, String>();
			}

			jobIds = new ArrayList<String>();

			Iterator it = sucests.entrySet().iterator();

			for (Map.Entry<String, Sucest> entry : sucests.entrySet()) {

				Sucest sucest = entry.getValue();

				String sequence = sucest.getSequences().get(0).getSequence();

				folderName = fileService.getFolderForSequenceFile(file.getOriginalFilename(), false);

				sucest.setPath(folderName);

				// run blast if the blast result files are not available to process
				if (!blastFilesAvailable) {

					String jobId = this.runBlast(sequence, email);

					if (jobId != null) {

						sucest.setIdBlastJob(jobId);

						sucestJobs.put(jobId, sucest);

						jobIds.add(jobId);
					}
				}
			}

			// export job ids to a file
			if (!blastFilesAvailable && !jobIds.isEmpty()) {

				fileService.exportBlastJobsToFile(jobIds, folderName);
			}
		}

		if (!blastFilesAvailable && jobIds != null && !jobIds.isEmpty()) {

			Integer attempts = 0;

			while ((jobIds.size() != jobResult.size()) && attempts <= MAX_ATTEMPTS) {

				for (String jobId : jobIds) {

					if (jobResult.get(jobId) == null) {

						String status = this.checkBlastStatus(jobId);

						if (status.equals(BlastJobStatusEnum.FINISHED.getStatus())) {

							String result = this.getBlastResult(jobId);

							jobResult.put(jobId, result);

						} else {

							attempts += 1;
						}
					}
				}
			}
		}

		if (folderName != null) {

			if (!blastFilesAvailable) {

				errors = fileService.exportBlastResultMapToFile(jobResult, jobIds, sucestJobs, folderName);

				blastFilesAvailable = true;
			}

			if (errors != null && !errors.isEmpty()) {

				fileService.exportErrors(errors, folderName);

			} else {

				// process result files
				Map<String, BlastResult> mapResult = fileService.processSucestBlastResultFiles(folderName, sucests);

				if (mapResult == null || mapResult.isEmpty()) {

					throw new CustomException(
							messageSource.getMessage("messages.errorProcessFile", new Object[] {}, Locale.US));
				}

			}
		}
	}

	public void saveBlastResultForSucest(BlastResult blastResult, Sucest sucest) {

		if (blastResult != null && blastResult.getUniqueIdentifier() != null && blastResult.getSucestBusca() != null
				&& blastRepository.findByUniqueIdentifierAndSucestBusca(blastResult.getUniqueIdentifier(),
						blastResult.getSucestBusca()) == null) {

			try {

				blastResult.setSucest(sucest);

				blastRepository.save(blastResult);

			} catch (RuntimeException e) {

				System.out.println("Erro to save: " + blastResult.getEntryName() + " - sucest : "
						+ blastResult.getSucestBusca());
			}

		}
	}
}
