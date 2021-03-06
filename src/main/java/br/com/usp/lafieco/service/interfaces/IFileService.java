package br.com.usp.lafieco.service.interfaces;

import java.util.List;
import java.util.Map;

import br.com.usp.lafieco.model.BlastResult;

public interface IFileService {

	Map<String, BlastResult>  processBlastResultFile(String gene,String folderName);
		 
	Map<String, BlastResult> processBlastResultFile(List<String> lines);
	
	Map<String, BlastResult> processBlastResultFile(String gene, String folderName, List<String> lines, Boolean isCompleteSearch) ;

	String readSucestBlastFile(String sucestGene) ;
	
	//Map<String, BlastResult> processSucestBlastResultFiles( String folderName, Map<String, Sucest> sucests);

	//Map<String, Sucest> processMultipleSequenceFile(MultipartFile file);
	
	//List<String> processCSVFile(InputStream inputFS);

	//Map<String, String> exportBlastResultMapToFile(Map<String, String> jobResult, List<String> jobIds, Map<String, Sucest> sucestJobs,
	//		String folderName);

	//void exportBlastResultToFile(String jobId, String gene, String export, Map<String, String> errors, String folderName);

	//void exportErrors(Map<String, String> errors, String folderName);

	//void deleteTxtFile(String fileName, String folderName);

	//String exportBlastJobsToFile(List<String> jobIds, String sequencesFile);

	//String getFolderForSequenceFile(String sequencesFile, Boolean fullName);

	//File checkIfExistsBlastJobFile(String sequencesFile);

	//File checkIfExistsTxtFile(String folder);
	
	//List<String> readFile(String fileName, String folder) ;
	

}
