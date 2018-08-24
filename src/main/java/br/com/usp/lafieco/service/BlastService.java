package br.com.usp.lafieco.service;

import java.util.HashMap;
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

import br.com.usp.lafieco.enums.DatabaseEnum;
import br.com.usp.lafieco.enums.ProgramEnum;
import br.com.usp.lafieco.enums.SequenceTypeEnum;
import br.com.usp.lafieco.exception.CustomException;

@Component
public class BlastService implements IBlastService {

	private final String BASE_URL = "https://www.ebi.ac.uk/Tools/services/rest/ncbiblast";
	private final String RUN_BLAST = "/run";
	private final String STATUS_BLAST_JOB = "/status/"; //status/{jobId} 
	private final String RESULT_BLAST_JOB = "/result/"; //result/{jobId}/out?format=0} 
	private final String OUTPUT_TEXT_FORMAT = "/out?format=0";
	private final String SEQUENCE = "sequence";
	private final String EMAIL = "email";
	private final String PROGRAM = "program";
	private final String SEQUENCE_TYPE = "stype";
	private final String DATABASE = "database";
	private final String JOB_ID = "jobId";
	@Autowired
	private MessageSource messageSource;

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

			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(body, headers);

			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
			data = response.getBody();
		
			System.out.println("got the blast job id: " + data);

		} catch (RuntimeException e) {
			throw new CustomException(messageSource.getMessage("messages.errorBlast",
					new Object[] { e.getMessage() + " -  " + e.getCause() }, Locale.US));
		}
		
		this.checkBlastStatus(data);
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
		
			System.out.println("blast result: " + blastResult);

		} catch (RuntimeException e) {
			throw new CustomException(messageSource.getMessage("messages.errorBlastJobStatus",
					new Object[] { e.getMessage() + " -  " + e.getCause() }, Locale.US));
		}
		
		return blastResult;
	}
}
