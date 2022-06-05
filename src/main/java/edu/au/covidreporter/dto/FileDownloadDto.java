package main.java.edu.au.covidreporter.dto;

public class FileDownloadDto {

	/**
	 * File name (without path)
	 */
	private String fileName;

	/**
	 * File content as an array of bytes
	 */
	private byte[] content;

	public FileDownloadDto() {
	}

	public FileDownloadDto(String fileName, byte[] content) {
		this.fileName = fileName;
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
}
