package br.com.usp.lafieco.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "blast_result")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "sucest"})
public class BlastResult {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "id_sucest")
	private Long idSucest;
	
	@Column(name = "id_search")
	private Long idSearch;
	
	@Column(name = "db")
	private String db;
	
	@Column(name = "unique_identifier")
	private String uniqueIdentifier;
	
	@Column(name = "entry_name")
	private String entryName;
	
	@Column(name = "proteinName")
	private String proteinName;
	
	@Column(name = "organism_name")
	private String organismName;
	
	@Column(name = "organism_identifier")
	private String organismIdentifier;
	
	@Column(name = "gene_name")
	private String geneName;
	
	@Column(name = "protein_existence")
	private Integer proteinExistence;
	
	@Column(name = "sequence_version")
	private Integer sequenceVersion;
	
	@Column(name = "score")
	private Integer score;
	
	@Column(name = "evalue")
	private String evalue;
	
	@Column(name = "identities")
	private Integer identities;
	
	@Column(name = "length")
	private Integer length;
	
	@Column(name = "positives")
	private Integer positives;
	
	@Column(name = "gaps")
	private Integer gaps;
	
	@Column(name = "fulltext")
	private String fullText;
	
	@Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name = "disabled_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date disabledAt;
    
	private String sucest;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdSucest() {
		return idSucest;
	}

	public void setIdSucest(Long idSucest) {
		this.idSucest = idSucest;
	}

	public Long getIdSearch() {
		return idSearch;
	}

	public void setIdSearch(Long idSearch) {
		this.idSearch = idSearch;
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
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

	public String getSucest() {
		return sucest;
	}

	public void setSucest(String sucest) {
		this.sucest = sucest;
	}

}
