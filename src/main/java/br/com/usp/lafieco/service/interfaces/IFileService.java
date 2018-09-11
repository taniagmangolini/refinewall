package br.com.usp.lafieco.service.interfaces;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import br.com.usp.lafieco.model.BlastResult;

public interface IFileService {

	Map<String, String> processMultipleSequenceFile(MultipartFile file);

	List<String> processCSVFile(InputStream inputFS);

	Map<String, String> exportBlastResultMapToFile(Map<String, String> jobResult, List<String> jobIds, Map<String, Map<String, String>> sequencesJobs,
			String folderName);

	void exportBlastResultToFile(String jobId, String gene, String export, Map<String, String> errors, String folderName);

	void exportErrors(Map<String, String> errors, String folderName);

	void deleteTxtFile(String fileName, String folderName);

	String exportBlastJobsToFile(List<String> jobIds, String sequencesFile);

	String getFolderForSequenceFile(String sequencesFile, Boolean fullName);

	File checkIfExistsBlastJobFile(String sequencesFile);

	File checkIfExistsTxtFile(String folder);
	
	List<String> readFile(String fileName, String folder) ;
	
	Map<String, BlastResult>  processBlastResultFile(String gene,String folderName);
}
