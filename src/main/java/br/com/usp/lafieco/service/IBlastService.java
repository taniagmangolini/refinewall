package br.com.usp.lafieco.service;

public interface IBlastService {

	String runBlast(String sequence, String email) ;
	
	void checkBlastStatus(String jobId);
	
	void getBlastResult(String jobId);
}
