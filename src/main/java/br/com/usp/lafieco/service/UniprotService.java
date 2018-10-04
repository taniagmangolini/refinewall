package br.com.usp.lafieco.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.usp.lafieco.bean.org.uniprot.uniprot.DbReferenceType;
import br.com.usp.lafieco.bean.org.uniprot.uniprot.EvidencedStringType;
import br.com.usp.lafieco.bean.org.uniprot.uniprot.GeneNameType;
import br.com.usp.lafieco.bean.org.uniprot.uniprot.GeneType;
import br.com.usp.lafieco.bean.org.uniprot.uniprot.KeywordType;
import br.com.usp.lafieco.bean.org.uniprot.uniprot.ProteinType.SubmittedName;
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

			if (uniprot != null) {
				uniprotVO = this.getUniprotVO(uniprot);
			}

		} catch (RuntimeException e) {

			e.printStackTrace();
		}

		return uniprotVO;
	}

	private UniprotVO getUniprotVO(Uniprot uniprot) {

		UniprotVO uniprotVO = new UniprotVO();
		uniprotVO.setAccessions(new ArrayList<String>());
		uniprotVO.setGenes(new ArrayList<String>());
		uniprotVO.setProtein(new ProteinVO());

		for (String item : uniprot.getEntry().get(0).getAccession()) {
			uniprotVO.getAccessions().add(item);
		}

		// protein data
		if (uniprot.getEntry().get(0).getProtein() != null
				&& (uniprot.getEntry().get(0).getProtein().getRecommendedName() != null
						|| uniprot.getEntry().get(0).getProtein().getSubmittedName() != null)) {

			if (uniprot.getEntry().get(0).getProtein().getRecommendedName() != null) {

				uniprotVO.getProtein().setProteinName(
						uniprot.getEntry().get(0).getProtein().getRecommendedName().getFullName().getValue());

			} else if (uniprot.getEntry().get(0).getProtein().getSubmittedName() != null) {

				StringBuilder sbProteinNames = new StringBuilder();

				// all submitted names
				for (SubmittedName submittedName : uniprot.getEntry().get(0).getProtein().getSubmittedName()) {
					sbProteinNames.append(submittedName.getFullName().getValue());
					sbProteinNames.append("; ");
				}

				if(!sbProteinNames.toString().equalsIgnoreCase("")) {
					uniprotVO.getProtein().setProteinName(sbProteinNames.toString().substring(0, sbProteinNames.toString().length() -2 ));
				}
			}

			uniprotVO.getProtein().setEcNumber(new ArrayList<String>());

			if (uniprot.getEntry().get(0).getProtein().getRecommendedName() != null
					&& uniprot.getEntry().get(0).getProtein().getRecommendedName().getEcNumber() != null
					&& !uniprot.getEntry().get(0).getProtein().getRecommendedName().getEcNumber().isEmpty()) {

				for (EvidencedStringType ec : uniprot.getEntry().get(0).getProtein().getRecommendedName()
						.getEcNumber()) {

					uniprotVO.getProtein().getEcNumber().add(ec.getValue() != null ? ec.getValue() : "");
				}
				// all submitted ec numbers
			} else if (uniprot.getEntry().get(0).getProtein().getSubmittedName() != null) {
				
				StringBuilder sbECNumbers = new StringBuilder();
				
				for (SubmittedName sbname : uniprot.getEntry().get(0).getProtein().getSubmittedName()) {
					if (!sbname.getEcNumber().isEmpty()) {
						for (EvidencedStringType ec : sbname.getEcNumber()) {
							sbECNumbers.append(ec.getValue());
							sbECNumbers.append("; ");
						}
					}
				}
				
				if(!sbECNumbers.toString().equalsIgnoreCase("")) {
					uniprotVO.getProtein().getEcNumber().add(sbECNumbers.toString().substring(0, sbECNumbers.toString().length() -2 ));
				}
			}
		}

		// genes
		if (uniprot.getEntry().get(0).getGene() != null && !uniprot.getEntry().get(0).getGene().isEmpty()) {
			for (GeneType item : uniprot.getEntry().get(0).getGene()) {
				List<GeneNameType> geneNames = item.getName();
				for (GeneNameType name : geneNames) {
					uniprotVO.getGenes().add(name.getValue() != null ? name.getValue() : "");
				}
			}
		}

		// organism
		if (uniprot.getEntry().get(0).getOrganism() != null) {
			uniprotVO.setOrganism(uniprot.getEntry().get(0).getOrganism().getName().get(0).getValue());
		}

		// sequence
		if (uniprot.getEntry().get(0).getSequence() != null) {
			uniprotVO.setSequence(uniprot.getEntry().get(0).getSequence().getValue() != null
					? uniprot.getEntry().get(0).getSequence().getValue()
					: "");
		}

		// databases
		if (uniprot.getEntry().get(0).getDbReference() != null
				&& !uniprot.getEntry().get(0).getDbReference().isEmpty()) {
			uniprotVO.setDatabases(new ArrayList<String>());
			for (DbReferenceType db : uniprot.getEntry().get(0).getDbReference()) {
				uniprotVO.getDatabases().add(db.getType() + ": " + db.getId());
			}
		}

		// keywords
		if (uniprot.getEntry().get(0).getKeyword() != null && !uniprot.getEntry().get(0).getKeyword().isEmpty()) {
			uniprotVO.setKeywords(new ArrayList<String>());
			for (KeywordType key : uniprot.getEntry().get(0).getKeyword()) {
				uniprotVO.getKeywords().add(key.getValue());
			}
		}

		return uniprotVO;
	}
}
