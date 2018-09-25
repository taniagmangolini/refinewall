package br.com.usp.lafieco.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.usp.lafieco.bean.org.uniprot.uniprot.EvidencedStringType;
import br.com.usp.lafieco.bean.org.uniprot.uniprot.GeneNameType;
import br.com.usp.lafieco.bean.org.uniprot.uniprot.GeneType;
import br.com.usp.lafieco.bean.org.uniprot.uniprot.ProteinType;
import br.com.usp.lafieco.bean.org.uniprot.uniprot.Uniprot;
import br.com.usp.lafieco.enums.FormatEnum;
import br.com.usp.lafieco.service.interfaces.IUniprotService;
import br.com.usp.lafieco.vo.ProteinVO;
import br.com.usp.lafieco.vo.UniprotVO;

@Component
public class UniprotService implements IUniprotService {

	private final String BASE_URL = "https://www.uniprot.org/uniprot/?query=";

	@Override
	public UniprotVO getUniprot(String idProtein) {

		System.out.println("GENE PRODUCTS FOR =>>>> " + idProtein);

		Uniprot uniprot = null;

		UniprotVO uniprotVO = null;

		RestTemplate restTemplate = new RestTemplate();

		String base_url = BASE_URL;

		URI targetUrl = UriComponentsBuilder.fromUriString(base_url) // Build the url
				.queryParam("query", idProtein) // search param
				.queryParam("format", FormatEnum.XML.getFormat()) // columns
				.build().encode().toUri();

		try {

			uniprot = restTemplate.getForObject(targetUrl, Uniprot.class);

			System.out.println("uniprot=>>>> " + uniprot.getEntry().get(0).getAccession());

			uniprotVO = new UniprotVO();
			uniprotVO.setAccessions(new ArrayList<String>());
			uniprotVO.setGenes(new ArrayList<String>());
			uniprotVO.setProtein(new ProteinVO());

			for (String item : uniprot.getEntry().get(0).getAccession()) {
				uniprotVO.getAccessions().add(item);
			}

			// mount the uniprot value object
			if (uniprot.getEntry().get(0).getProtein() != null
					&& uniprot.getEntry().get(0).getProtein().getSubmittedName() != null
					&& !uniprot.getEntry().get(0).getProtein().getSubmittedName().isEmpty()) {

				for (ProteinType.SubmittedName item : uniprot.getEntry().get(0).getProtein().getSubmittedName()) {

					uniprotVO.getProtein()
							.setProteinName(item.getFullName().getValue() != null ? item.getFullName().getValue() : "");

					uniprotVO.getProtein().setEcNumber(new ArrayList<String>());

					if (item.getEcNumber() != null && !item.getEcNumber().isEmpty()) {

						for (EvidencedStringType ec : item.getEcNumber()) {

							uniprotVO.getProtein().getEcNumber().add(ec.getValue() != null ? ec.getValue() : "");
						}
					}

				}
			}

			if (uniprot.getEntry().get(0).getGene() != null && !uniprot.getEntry().get(0).getGene().isEmpty()) {
				for (GeneType item : uniprot.getEntry().get(0).getGene()) {
					List<GeneNameType> geneNames = item.getName();
					for (GeneNameType name : geneNames) {
						uniprotVO.getGenes().add(name.getValue() != null ? name.getValue() : "");
					}
				}
			}
			
			if (uniprot.getEntry().get(0).getSequence() != null) {
				uniprotVO.setSequence(uniprot.getEntry().get(0).getSequence().getValue() != null ? uniprot.getEntry().get(0).getSequence().getValue() : "");
			}
			
		} catch (RuntimeException e) {

			e.printStackTrace();
		}

		return uniprotVO;
	}

}
