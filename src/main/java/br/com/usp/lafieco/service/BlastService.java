package br.com.usp.lafieco.service;

import java.net.URI;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.usp.lafieco.enums.DatabaseEnum;
import br.com.usp.lafieco.enums.ProgramEnum;
import br.com.usp.lafieco.enums.SequenceTypeEnum;
import br.com.usp.lafieco.exception.CustomException;

@Component
public class BlastService implements IBlastService {

	private final String BASE_URL = "https://www.ebi.ac.uk/Tools/services/rest/ncbiblast";
	private final String RUN_BLAST = "/run";
	private final String STATUS_BLAST_JOB = "/status";
	private final String SEQUENCE = "sequence";
	private final String EMAIL = "email";
	private final String PROGRAM = "program";
	private final String STYPE = "stype";
	private final String DATABASE = "database";
	
	@Autowired
	private MessageSource messageSource;

	public String runBlast(String sequence, String email) {

		String job = "";
		try {

			System.out.println("running a blast ...");


			RestTemplate restTemplate = new RestTemplate();

			URI targetUrl = UriComponentsBuilder.fromUriString(BASE_URL + RUN_BLAST) // Build the url
					.queryParam(EMAIL, email) 
					.queryParam(SEQUENCE, sequence) 
					.queryParam(PROGRAM, ProgramEnum.BLASTP) 
					.queryParam(DATABASE, DatabaseEnum.UNIPROTKB_VIRIDIPLANTAE) 
					.queryParam(STYPE, SequenceTypeEnum.PROTEIN).build().encode().toUri();

			job = restTemplate.getForObject(targetUrl,String.class);

			System.out.println("got the blast job id: " + job);

		} catch (RuntimeException e) {
			throw new CustomException(messageSource.getMessage("messages.errorBlast",
					new Object[] { e.getMessage() + " -  " + e.getCause() }, Locale.US));
		}
		
		return job;
	}
	
	public void checkBlastStatus(String jobId) {
		
	}
	
	public void getBlastResult(String jobId) {
		
	}
}
