package br.com.usp.lafieco.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProgramEnum {

	BLASTP("blastp"), BLASTX("blastx"), BLASTN("blastn");

	private String program;

	ProgramEnum(String program) {
		this.program = program;
	}

	@JsonValue
	public String getProgram() {
		return program;
	}
}
