package br.com.usp.lafieco.service.interfaces;

import br.com.usp.lafieco.vo.RefineResultVO;

public interface IRefineService {

	RefineResultVO refineSequence(String sequence, String email) ;
	
	RefineResultVO refineById(String id, String email) ;
	
	RefineResultVO refineSequence(String sequence, String email, String jobId) ;
}
