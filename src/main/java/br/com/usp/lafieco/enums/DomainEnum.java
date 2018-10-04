package br.com.usp.lafieco.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DomainEnum {
	C("Cellulose"), 
	H("HemiCellulose"), 
	P("Pectin"), 
	L("Lignin"),
	E("Expansin"), 
	M("Metabolism"), 
	O("Others"),
	NA("Not Defined");

	private String domain;

	DomainEnum(String domain) {
		this.domain = domain;
	}

	@JsonValue
	public String getDomain() {
		return domain;
	}
}
