package br.com.usp.lafieco.service.interfaces;

import java.util.List;

import br.com.usp.lafieco.bean.GeneProduct;

public interface IProteinService {

	List<GeneProduct> getGeneProductService(String idProtein);
	
}
