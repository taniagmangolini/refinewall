package br.com.usp.lafieco.service.interfaces;

public interface IBlastService {

	String runBlast(String sequence, String email) ;
	
	String checkBlastStatus(String jobId);
	
	String getBlastResult(String jobId);
}
