package br.com.usp.lafieco.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FormatEnum {

	TXT("txt"), TAB("tab"), FASTA("fasta"), XML("xml");

	private String format;

	FormatEnum(String format) {
		this.format = format;
	}

	@JsonValue
	public String getFormat() {
		return format;
	}
}
