package br.com.usp.lafieco.service;

import java.net.URI;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.usp.lafieco.bean.GeneProduct;
import br.com.usp.lafieco.bean.org.uniprot.uniprot.Uniprot;
import br.com.usp.lafieco.enums.FormatEnum;
import br.com.usp.lafieco.service.interfaces.IUniprotService;

@Component
public class UniprotService implements IUniprotService {

	private final String BASE_URL = "https://www.uniprot.org/uniprot/?query=";

	@Override
	public Uniprot getUniprot(String idProtein) {
		
		System.out.println("GENE PRODUCTS FOR =>>>> " + idProtein);
		
		Uniprot uniprot = null;
		
		RestTemplate restTemplate = new RestTemplate();
		
		String base_url = BASE_URL;
		
		URI targetUrl = UriComponentsBuilder.fromUriString(base_url) // Build the url
				.queryParam("query", idProtein) // search param
				.queryParam("format", FormatEnum.XML.getFormat()) // columns
				.build().encode().toUri();

		try {

			uniprot = restTemplate.getForObject(targetUrl, Uniprot.class);

			System.out.println("uniprot=>>>> " + uniprot.getEntry().get(0).getAccession());

		} catch (RuntimeException e) {
			
			e.printStackTrace();
		}

		return uniprot;
	}

}
