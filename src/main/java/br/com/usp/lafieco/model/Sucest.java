package br.com.usp.lafieco.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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

@Entity
@Table(name = "sucest")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "idBlastJob", "resultBlastContents"})
public class Sucest {
	
	@Id
	private String id;
	
	@Column(name = "sequence", length = 10000)
	private String sequence;
	
	@Column(name = "description", length =  5000)
	private String description;
	
	@Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;
	
	@Column(name = "path", length =  5000)
	private String path;
	
	@OneToMany(mappedBy = "sucest", cascade = CascadeType.ALL)
	private List<BlastResult> blastResults;
	
	@JsonIgnore
	@Transient
	private String idBlastJob;
	
	@JsonIgnore
	@Transient
	private List<String> resultBlastContents;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (sequence == null) {
			if (other.sequence != null)
				return false;
		} else if (!sequence.equals(other.sequence))
			return false;
		return true;
	}
	
	
}
