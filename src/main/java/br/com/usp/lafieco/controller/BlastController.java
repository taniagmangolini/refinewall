package br.com.usp.lafieco.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.usp.lafieco.exception.CustomException;
import br.com.usp.lafieco.service.IBlastService;

@RestController
@CrossOrigin(maxAge=3600)
@RequestMapping("/blast")
public class BlastController {

	
	@Autowired
	private IBlastService blastService;
	
	@Autowired
	private MessageSource messageSource;
	

	@CrossOrigin
	@GetMapping
	@ResponseBody
	public String runBlast(
			@RequestParam("sequence") String sequence, @RequestParam("email") String email ) {

		String job = blastService.runBlast(sequence, email);

		System.out.println("Blast job: " + job);

		
		
		if (job == null || job.trim().equalsIgnoreCase("")) {
			throw new CustomException(messageSource.getMessage("messages.errorBlastJob", new Object[] {}, Locale.US));
		}

		return job;
}
}
