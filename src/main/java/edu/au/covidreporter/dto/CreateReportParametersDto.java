package main.java.edu.au.covidreporter.dto;

import edu.au.covidreporter.validator.DateRange;
import edu.au.covidreporter.validator.RealDateFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.StringJoiner;

/**
 * DTO for incoming parameters for report creation.
 */
@DateRange()
public class CreateReportParametersDto {

	@NotBlank
	private String country;

	@NotBlank
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
	@RealDateFormat
	private String fromDate;

	@NotBlank
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
	@RealDateFormat
	private String toDate;

	public CreateReportParametersDto(String country,String fromDate,String toDate){
		this.country = country;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", CreateReportParametersDto.class.getSimpleName() + "[", "]")
				.add("country='" + country + "'")
				.add("fromDate='" + fromDate + "'")
				.add("toDate='" + toDate + "'")
				.toString();
	}
}
