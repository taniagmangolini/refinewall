package br.com.usp.lafieco.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SequenceTypeEnum {

	DNA("dna"), PROTEIN("protein");

	private String type;

	SequenceTypeEnum(String type) {
		this.type = type;
	}

	@JsonValue
	public String getType() {
		return type;
	}
}
