package br.com.usp.lafieco.entity;

public class BlastResult {

	private Long id;
	private Long idSucest;
	private Long idSearch;
	private String db;
	private String uniqueIdentifier;
	private String entryName;
	private String proteiName;
	private String organismName;
	private String organismIdentifier;
	private String geneName;
	private Integer proteinExistence;
	private Integer sequenceVersion;
	private Integer score;
	private Integer evalue;
	private Integer identities;
	private Integer length;
	private Integer positives;
	private Integer gaps;
	private String fullText;
	
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

	public String getProteiName() {
		return proteiName;
	}

	public void setProteiName(String proteiName) {
		this.proteiName = proteiName;
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

	public Integer getEvalue() {
		return evalue;
	}

	public void setEvalue(Integer evalue) {
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

	public String getSucest() {
		return sucest;
	}

	public void setSucest(String sucest) {
		this.sucest = sucest;
	}

}
