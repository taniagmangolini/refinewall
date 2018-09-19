package br.com.usp.lafieco.vo;

import java.util.List;

import br.com.usp.lafieco.bean.org.uniprot.uniprot.Uniprot;
import br.com.usp.lafieco.model.BlastResult;
import br.com.usp.lafieco.model.Sucest;

public class RefineResultVO {


	private List<Sucest> sucests;

	private List<BlastResult> blastResults;
	
	private Uniprot uniprot;

	public List<Sucest> getSucests() {
		return sucests;
	}

	public void setSucests(List<Sucest> sucests) {
		this.sucests = sucests;
	}

	public Uniprot getUniprot() {
		return uniprot;
	}

	public void setUniprot(Uniprot uniprot) {
		this.uniprot = uniprot;
	}

	public List<BlastResult> getBlastResults() {
		return blastResults;
	}

	public void setBlastResults(List<BlastResult> blastResults) {
		this.blastResults = blastResults;
	}
	
}
