package br.com.usp.lafieco.service;

import java.net.URI;
import java.util.List;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.usp.lafieco.bean.GoSearchGeneProductResult;
import br.com.usp.lafieco.bean.GeneProduct;
import br.com.usp.lafieco.service.interfaces.IProteinService;

public class ProteinService implements IProteinService {

	private final String BASE_URL = "https://www.ebi.ac.uk/QuickGO/services/geneproduct/search";

	@Override
	public List<GeneProduct> getGeneProductService(String idProtein) {
		System.out.println("GENE PRODUCTS FOR =>>>> " + idProtein);
		List<GeneProduct> geneProducts = null;
		RestTemplate restTemplate = new RestTemplate();
		String base_url = BASE_URL;
		URI targetUrl = UriComponentsBuilder.fromUriString(base_url) // Build the url
				.queryParam("query", idProtein) // Add query params
				.build().encode().toUri();

		try {

			GoSearchGeneProductResult geneProductSearch = restTemplate.getForObject(targetUrl,
					GoSearchGeneProductResult.class);
			geneProducts = geneProductSearch.getResults();

		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		if (geneProducts != null && !geneProducts.isEmpty()) {
			System.out.println("GENE PRODUCTS =>>>> " + geneProducts.get(0).getId());
		}

		return geneProducts;
	}

}
