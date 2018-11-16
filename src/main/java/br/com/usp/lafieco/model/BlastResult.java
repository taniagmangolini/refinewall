package br.com.usp.lafieco.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "blast_result")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "newVersionAvailable", "sucest", "gaps", "db","positives", "length", "organismName","organismIdentifier", "geneName", "proteinExistence", "identities" ,"organismIdentifier", "hibernateLazyInitializer", "handler"})
public class BlastResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn
	private Sucest sucest;
	
	@Column(name = "db")
	private String db;
	
	@Column(name = "unique_identifier", length = 500)
	private String uniqueIdentifier;
	
	@Column(name = "entry_name" , length = 500)
	private String entryName;
	
	@Column(name = "proteinName", length = 500)
	private String proteinName;
	
	@Column(name = "organism_name", length = 500)
	private String organismName;
	
	@Column(name = "organism_identifier", length = 500)
	private String organismIdentifier;
	
	@Column(name = "gene_name", length = 1024)
	private String geneName;
	
	@Column(name = "protein_existence")
	private Integer proteinExistence;
	
	@Column(name = "sequence_version")
	private Integer sequenceVersion;
	
	@Column(name = "score")
	private Double score;
	
	@Column(name = "evalue", length = 255)
	private String evalue;
	
	@Column(name = "identities")
	private Integer identities;
	
	@Column(name = "length")
	private Integer length;
	
	@Column(name = "positives")
	private Integer positives;
	
	@Column(name = "gaps")
	private Integer gaps;
	
	@Column(name = "fulltextblast", length = 10000)
	private String fullText;
	
	@Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name = "disabled_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date disabledAt;
    
    @Column (name = "new_v_available")
    private Boolean newVersionAvailable;
    
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    
    @Column(name = "sucest_busca")
    private String sucestBusca;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}

	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}

	public String getEntryName() {
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}

	public String getProteinName() {
		return proteinName;
	}

	public void setProteinName(String proteiName) {
		this.proteinName = proteiName;
	}

	public String getOrganismName() {
		return organismName;
	}

	public void setOrganismName(String organismName) {
		this.organismName = organismName;
	}

	public String getOrganismIdentifier() {
		return organismIdentifier;
	}

	public void setOrganismIdentifier(String organismIdentifier) {
		this.organismIdentifier = organismIdentifier;
	}

	public String getGeneName() {
		return geneName;
	}

	public void setGeneName(String geneName) {
		this.geneName = geneName;
	}

	public Integer getProteinExistence() {
		return proteinExistence;
	}

	public void setProteinExistence(Integer proteinExistence) {
		this.proteinExistence = proteinExistence;
	}

	public Integer getSequenceVersion() {
		return sequenceVersion;
	}

	public void setSequenceVersion(Integer sequenceVersion) {
		this.sequenceVersion = sequenceVersion;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getEvalue() {
		return evalue;
	}

	public void setEvalue(String evalue) {
		this.evalue = evalue;
	}

	public Integer getIdentities() {
		return identities;
	}

	public void setIdentities(Integer identities) {
		this.identities = identities;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getPositives() {
		return positives;
	}

	public void setPositives(Integer positives) {
		this.positives = positives;
	}

	public Integer getGaps() {
		return gaps;
	}

	public void setGaps(Integer gaps) {
		this.gaps = gaps;
	}

	public String getFullText() {
		return fullText;
	}

	public void setFullText(String fullText) {
		this.fullText = fullText;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getDisabledAt() {
		return disabledAt;
	}

	public void setDisabledAt(Date disabledAt) {
		this.disabledAt = disabledAt;
	}

	public Sucest getSucest() {
		return sucest;
	}

	public void setSucest(Sucest sucest) {
		this.sucest = sucest;
	}

	public String getSucestBusca() {
		return sucestBusca;
	}

	public void setSucestBusca(String sucestBusca) {
		this.sucestBusca = sucestBusca;
	}


	public Boolean getNewVersionAvailable() {
		return newVersionAvailable;
	}

	public void setNewVersionAvailable(Boolean newVersionAvailable) {
		this.newVersionAvailable = newVersionAvailable;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((db == null) ? 0 : db.hashCode());
		result = prime * result + ((disabledAt == null) ? 0 : disabledAt.hashCode());
		result = prime * result + ((entryName == null) ? 0 : entryName.hashCode());
		result = prime * result + ((evalue == null) ? 0 : evalue.hashCode());
		result = prime * result + ((fullText == null) ? 0 : fullText.hashCode());
		result = prime * result + ((gaps == null) ? 0 : gaps.hashCode());
		result = prime * result + ((geneName == null) ? 0 : geneName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((identities == null) ? 0 : identities.hashCode());
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result + ((organismIdentifier == null) ? 0 : organismIdentifier.hashCode());
		result = prime * result + ((organismName == null) ? 0 : organismName.hashCode());
		result = prime * result + ((positives == null) ? 0 : positives.hashCode());
		result = prime * result + ((proteinExistence == null) ? 0 : proteinExistence.hashCode());
		result = prime * result + ((proteinName == null) ? 0 : proteinName.hashCode());
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		result = prime * result + ((sequenceVersion == null) ? 0 : sequenceVersion.hashCode());
		result = prime * result + ((sucestBusca == null) ? 0 : sucestBusca.hashCode());
		result = prime * result + ((uniqueIdentifier == null) ? 0 : uniqueIdentifier.hashCode());
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
		BlastResult other = (BlastResult) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (db == null) {
			if (other.db != null)
				return false;
		} else if (!db.equals(other.db))
			return false;
		if (disabledAt == null) {
			if (other.disabledAt != null)
				return false;
		} else if (!disabledAt.equals(other.disabledAt))
			return false;
		if (entryName == null) {
			if (other.entryName != null)
				return false;
		} else if (!entryName.equals(other.entryName))
			return false;
		if (evalue == null) {
			if (other.evalue != null)
				return false;
		} else if (!evalue.equals(other.evalue))
			return false;
		if (fullText == null) {
			if (other.fullText != null)
				return false;
		} else if (!fullText.equals(other.fullText))
			return false;
		if (gaps == null) {
			if (other.gaps != null)
				return false;
		} else if (!gaps.equals(other.gaps))
			return false;
		if (geneName == null) {
			if (other.geneName != null)
				return false;
		} else if (!geneName.equals(other.geneName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (identities == null) {
			if (other.identities != null)
				return false;
		} else if (!identities.equals(other.identities))
			return false;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (organismIdentifier == null) {
			if (other.organismIdentifier != null)
				return false;
		} else if (!organismIdentifier.equals(other.organismIdentifier))
			return false;
		if (organismName == null) {
			if (other.organismName != null)
				return false;
		} else if (!organismName.equals(other.organismName))
			return false;
		if (positives == null) {
			if (other.positives != null)
				return false;
		} else if (!positives.equals(other.positives))
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
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		if (sequenceVersion == null) {
			if (other.sequenceVersion != null)
				return false;
		} else if (!sequenceVersion.equals(other.sequenceVersion))
			return false;
		if (sucestBusca == null) {
			if (other.sucestBusca != null)
				return false;
		} else if (!sucestBusca.equals(other.sucestBusca))
			return false;
		if (uniqueIdentifier == null) {
			if (other.uniqueIdentifier != null)
				return false;
		} else if (!uniqueIdentifier.equals(other.uniqueIdentifier))
			return false;
		return true;
	}
	
	
}
