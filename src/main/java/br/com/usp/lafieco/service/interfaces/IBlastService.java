package br.com.usp.lafieco.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IBlastService {

	String runBlast(String sequence, String email) ;
	
	String checkBlastStatus(String jobId);
	
	String getBlastResult(String jobId);
		
	void runBlastMultipleSequences(MultipartFile file, String email) ;
}
