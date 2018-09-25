package br.com.usp.lafieco.service.interfaces;

import br.com.usp.lafieco.vo.UniprotVO;

public interface IUniprotService {

	UniprotVO getUniprot(String idProtein);
	
}
