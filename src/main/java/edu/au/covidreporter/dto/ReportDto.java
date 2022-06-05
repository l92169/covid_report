package main.java.edu.au.covidreporter.dto;

import java.util.StringJoiner;

public class ReportDto {

	private Integer id;
	private String fileName;

	public ReportDto() {
	}

	public ReportDto(Integer id, String fileName) {
		this.id = id;
		this.fileName = fileName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public ReportDto setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", ReportDto.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("fileName='" + fileName + "'")
				.toString();
	}
}
