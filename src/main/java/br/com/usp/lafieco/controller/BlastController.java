package br.com.usp.lafieco.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.usp.lafieco.enums.BlastJobStatusEnum;
import br.com.usp.lafieco.exception.CustomException;
import br.com.usp.lafieco.service.interfaces.IBlastService;
import br.com.usp.lafieco.service.interfaces.IFileService;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/blast")
public class BlastController {

	@Autowired
	private IBlastService blastService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private IFileService fileService;

	@CrossOrigin
	@PostMapping
	@ResponseBody
	public String runBlast(@RequestParam("sequence") String sequence, @RequestParam("email") String email) {

		String job = blastService.runBlast(sequence, email);

		System.out.println("Blast job: " + job);

		if (job == null || job.trim().equalsIgnoreCase("")) {
			throw new CustomException(messageSource.getMessage("messages.errorBlastJob", new Object[] {}, Locale.US));
		}

		return job;
	}

	@CrossOrigin
	@GetMapping("/status")
	@ResponseBody
	public String checkStatus(@RequestParam("jobId") String jobId) {

		String status = blastService.checkBlastStatus(jobId);

		System.out.println("Blast job: " + status);

		if (status == null || status.trim().equalsIgnoreCase("")) {
			throw new CustomException(
					messageSource.getMessage("messages.errorBlastJobStatus", new Object[] {}, Locale.US));
		}

		return status;
	}

	@CrossOrigin
	@GetMapping("/result")
	@ResponseBody
	public String getBlastResult(@RequestParam("jobId") String jobId) {

		String result = blastService.getBlastResult(jobId);

		System.out.println("Result for Blast job: " + result);

		if (result == null || result.trim().equalsIgnoreCase("")) {
			throw new CustomException(
					messageSource.getMessage("messages.errorBlastJobStatus", new Object[] {}, Locale.US));
		}

		return result;
	}

	@CrossOrigin
	@PostMapping("/multiple")
	@ResponseBody
	public List<String> runBlastMultipleSequences(@RequestParam("file") MultipartFile file,
			@RequestParam("email") String email) {
		
		List<String> sequences = fileService.processMultipleSequenceFile(file);
		
		List<String> jobIds = new ArrayList<String>();

		Map<String, String> jobResult = new HashMap<String, String>();
		
		System.out.println(sequences);

		if (sequences != null && !sequences.isEmpty()) {
			for (String sequence : sequences) {
				String jobId = this.runBlast(sequence, email);
				jobIds.add(jobId);
			}
		}
		if (jobIds != null && !jobIds.isEmpty()) {

			Integer attempts = 0;

			while ( (jobIds.size() != jobResult.size()) && attempts <= (jobIds.size() * 2) ) {
				for (String jobId : jobIds) {
					if (jobResult.get(jobId) == null) {
						String status = this.checkStatus(jobId);
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
		return jobIds;
	}

}
