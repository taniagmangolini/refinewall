package br.com.usp.lafieco.vo;

import java.io.Serializable;
import java.util.List;

public class ProteinVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String proteinName;

	private String organism;

	private String proteinExistence;

	private List<String> ecNumber;

	public String getProteinName() {
		return proteinName;
	}

	public void setProteinName(String proteinName) {
		this.proteinName = proteinName;
	}

	public String getOrganism() {
		return organism;
	}

	public void setOrganism(String organism) {
		this.organism = organism;
	}

	public String getProteinExistence() {
		return proteinExistence;
	}

	public void setProteinExistence(String proteinExistence) {
		this.proteinExistence = proteinExistence;
	}


	public List<String> getEcNumber() {
		return ecNumber;
	}

	public void setEcNumber(List<String> ecNumber) {
		this.ecNumber = ecNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ecNumber == null) ? 0 : ecNumber.hashCode());
		result = prime * result + ((organism == null) ? 0 : organism.hashCode());
		result = prime * result + ((proteinExistence == null) ? 0 : proteinExistence.hashCode());
		result = prime * result + ((proteinName == null) ? 0 : proteinName.hashCode());
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
		ProteinVO other = (ProteinVO) obj;
		if (ecNumber == null) {
			if (other.ecNumber != null)
				return false;
		} else if (!ecNumber.equals(other.ecNumber))
			return false;
		if (organism == null) {
			if (other.organism != null)
				return false;
		} else if (!organism.equals(other.organism))
			return false;
		if (proteinExistence == null) {
			if (other.proteinExistence != null)
				return false;
		} else if (!proteinExistence.equals(other.proteinExistence))
			return false;
		if (proteinName == null) {
			if (other.proteinName != null)
				return false;
		} else if (!proteinName.equals(other.proteinName))
			return false;
		return true;
	}

	
}
