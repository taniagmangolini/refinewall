package br.com.usp.lafieco.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

import br.com.usp.lafieco.model.BlastResult;

public interface IBlastService {

	String runBlast(String sequence, String email);

	String checkBlastStatus(String jobId);

	String getBlastResult(String jobId);

	void runBlastMultipleSequences(MultipartFile file, String email);

	void saveBlastResult(BlastResult blastResult);
}
