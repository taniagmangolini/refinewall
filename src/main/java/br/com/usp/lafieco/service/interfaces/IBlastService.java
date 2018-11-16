package br.com.usp.lafieco.service.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.usp.lafieco.model.BlastResult;
import br.com.usp.lafieco.model.Sucest;

public interface IBlastService {

	String runBlast(String sequence, String email);

	String checkBlastStatus(String jobId);

	String getBlastResult(String jobId);

	//void runBlastMultipleSequences(MultipartFile file, String email, Boolean blastFilesAvailable);

	//void saveBlastResultForSucest(BlastResult blastResult, Sucest sucest);
	
	List<BlastResult> runBlastSequence(String sequence, String email);

}
