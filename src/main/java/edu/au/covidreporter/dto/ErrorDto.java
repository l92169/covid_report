package main.java.edu.au.covidreporter.dto;

import java.util.StringJoiner;

public class ErrorDto {

	private String error;

	public ErrorDto(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", ErrorDto.class.getSimpleName() + "[", "]")
				.add("error='" + error + "'")
				.toString();
	}
}
