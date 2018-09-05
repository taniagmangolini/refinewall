package br.com.usp.lafieco.service.interfaces;

import br.com.usp.lafieco.bean.org.uniprot.uniprot.Uniprot;

public interface IUniprotService {

	Uniprot getUniprot(String idProtein);
	
}
