package br.com.usp.lafieco.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BlastJobStatusEnum {

	FINISHED("FINISHED"), RUNNING("RUNNING");

	private String status;

	BlastJobStatusEnum(String status) {
		this.status = status;
	}

	@JsonValue
	public String getStatus() {
		return status;
	}
}
