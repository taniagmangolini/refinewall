package br.com.usp.lafieco.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DatabaseEnum {

	UNIPROTKB("uniprotkb"), UNIPROTKB_VIRIDIPLANTAE("uniprotkb_viridiplantae");

	private String database;

	DatabaseEnum(String database) {
		this.database = database;
	}

	@JsonValue
	public String getDatabase() {
		return database;
	}
}
