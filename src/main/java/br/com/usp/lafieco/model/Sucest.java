package br.com.usp.lafieco.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "sucest")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "idBlastJob", "resultBlastContents", "hibernateLazyInitializer", "handler"})
public class Sucest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "gene", length = 10000)
	private String gene;
	
	@Column(name = "description", length =  5000)
	private String description;
	
	@Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;
	
	@Column(name = "path", length =  5000)
	private String path;
	
	@JsonManagedReference
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "sucest", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private List<BlastResult> blastResults;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "sucest", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private List<SucestSequence> sequences;
	

	
	@JsonIgnore
	@Transient
	private String idBlastJob;
	
	@JsonIgnore
	@Transient
	private List<String> resultBlastContents;
	
	/*The datasource used is limited to 10000 lines. So it is not the best solution, but it is a way to save some lines*/
	@Column(name = "domains", length =  5000)
	private String domainsString;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<BlastResult> getBlastResults() {
		return blastResults;
	}

	public void setBlastResults(List<BlastResult> blastResults) {
		this.blastResults = blastResults;
	}

	public String getIdBlastJob() {
		return idBlastJob;
	}

	public void setIdBlastJob(String idBlastJob) {
		this.idBlastJob = idBlastJob;
	}
	
	public List<String> getResultBlastContents() {
		return resultBlastContents;
	}

	public void setResultBlastContents(List<String> resultBlastContents) {
		this.resultBlastContents = resultBlastContents;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getGene() {
		return gene;
	}

	public void setGene(String gene) {
		this.gene = gene;
	}

	public List<SucestSequence> getSequences() {
		return sequences;
	}

	public void setSequences(List<SucestSequence> sequences) {
		this.sequences = sequences;
	}	

	
	public String getDomainsString() {
		return domainsString;
	}

	public void setDomainsString(String domainsString) {
		this.domainsString = domainsString;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((gene == null) ? 0 : gene.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((sequences == null) ? 0 : sequences.hashCode());
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
		Sucest other = (Sucest) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (gene == null) {
			if (other.gene != null)
				return false;
		} else if (!gene.equals(other.gene))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (sequences == null) {
			if (other.sequences != null)
				return false;
		} else if (!sequences.equals(other.sequences))
			return false;
		return true;
	}

	

}
