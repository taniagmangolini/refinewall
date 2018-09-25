package br.com.usp.lafieco.vo;

import java.io.Serializable;
import java.util.List;

public class UniprotVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<String> accessions;
	
	private ProteinVO protein;
	
	private List<String> genes;
	
	private String sequence;
	
	public List<String> getAccessions() {
		return accessions;
	}
	
	public void setAccessions(List<String> accessions) {
		this.accessions = accessions;
	}
	
	public ProteinVO getProtein() {
		return protein;
	}
	
	public void setProtein(ProteinVO protein) {
		this.protein = protein;
	}

	public List<String> getGenes() {
		return genes;
	}

	public void setGenes(List<String> genes) {
		this.genes = genes;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accessions == null) ? 0 : accessions.hashCode());
		result = prime * result + ((genes == null) ? 0 : genes.hashCode());
		result = prime * result + ((protein == null) ? 0 : protein.hashCode());
		result = prime * result + ((sequence == null) ? 0 : sequence.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UniprotVO other = (UniprotVO) obj;
		if (accessions == null) {
			if (other.accessions != null)
				return false;
		} else if (!accessions.equals(other.accessions))
			return false;
		if (genes == null) {
			if (other.genes != null)
				return false;
		} else if (!genes.equals(other.genes))
			return false;
		if (protein == null) {
			if (other.protein != null)
				return false;
		} else if (!protein.equals(other.protein))
			return false;
		if (sequence == null) {
			if (other.sequence != null)
				return false;
		} else if (!sequence.equals(other.sequence))
			return false;
		return true;
	}
	
	
}
