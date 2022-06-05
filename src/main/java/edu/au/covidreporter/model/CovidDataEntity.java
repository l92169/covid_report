package main.java.edu.au.covidreporter.model;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

/**
 * JPA Entity for 'covid_data' database table.
 */
@Entity
@Table(name = "covid_data")
public class CovidDataEntity {
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "date_year", nullable = false)
	private Integer date_year;

	@Column(name = "date_month", nullable = false)
	private Integer date_month;

	@Column(name = "date_day", nullable = false)
	private Integer date_day;

	@Column(name = "country_region", nullable = false)
	private String country_region;

	@Column(name = "last_update", nullable = false)
	private Instant last_update;

	@Column(name = "latitude", nullable = false)
	private Double latitude;

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Column(name = "longitude", nullable = false)
	private Double longitude;

	@Column(name = "confirmed", nullable = false)
	private Integer confirmed;

	@Column(name = "deaths", nullable = false)
	private Integer deaths;

	@Column(name = "recovered", nullable = false)
	private Integer recovered;

	@Column(name = "active", nullable = false)
	private Integer active;

	@Column(name = "combined_key", nullable = false)
	private String combined_key;

	@Column(name = "incident_rate", nullable = false)
	private Double incident_rate;

	@Column(name = "case_fatality_ratio", nullable = false)
	private Double case_fatality_ratio;


	@Column(name = "date", nullable = false)
	private LocalDate date;

	@Column(name = "province_state", nullable = false)
	private String province_state;

	@Column(name = "confirmed_diff", nullable = false)
	private String confirmed_diff;

	@Column(name = "deaths_diff", nullable = false)
	private Integer deaths_diff;

	@Column(name = "recovered_diff", nullable = false)
	private Integer recovered_diff;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDate_year() {
		return date_year;
	}

	public void setDate_year(Integer date_year) {
		this.date_year = date_year;
	}

	public Integer getDate_month() {
		return date_month;
	}

	public void setDate_month(Integer date_month) {
		this.date_month = date_month;
	}

	public Integer getDate_day() {
		return date_day;
	}

	public void setDate_day(Integer date_day) {
		this.date_day = date_day;
	}

	public String getCountry_region() {
		return country_region;
	}

	public void setCountry_region(String country_region) {
		this.country_region = country_region;
	}

	public Instant getLast_update() {
		return last_update;
	}

	public void setLast_update(Instant last_update) {
		this.last_update = last_update;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Integer getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Integer confirmed) {
		this.confirmed = confirmed;
	}

	public Integer getDeaths() {
		return deaths;
	}

	public void setDeaths(Integer deaths) {
		this.deaths = deaths;
	}

	public Integer getRecovered() {
		return recovered;
	}

	public void setRecovered(Integer recovered) {
		this.recovered = recovered;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getCombined_key() {
		return combined_key;
	}

	public void setCombined_key(String combined_key) {
		this.combined_key = combined_key;
	}

	public Double getIncident_rate() {
		return incident_rate;
	}

	public void setIncident_rate(Double incident_rate) {
		this.incident_rate = incident_rate;
	}

	public Double getCase_fatality_ratio() {
		return case_fatality_ratio;
	}

	public void setCase_fatality_ratio(Double case_fatality_ratio) {
		this.case_fatality_ratio = case_fatality_ratio;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getProvince_state() {
		return province_state;
	}

	public void setProvince_state(String province_state) {
		this.province_state = province_state;
	}

	public String getConfirmed_diff() {
		return confirmed_diff;
	}

	public void setConfirmed_diff(String confirmed_diff) {
		this.confirmed_diff = confirmed_diff;
	}

	public Integer getDeaths_diff() {
		return deaths_diff;
	}

	public void setDeaths_diff(Integer deaths_diff) {
		this.deaths_diff = deaths_diff;
	}

	public Integer getRecovered_diff() {
		return recovered_diff;
	}

	public void setRecovered_diff(Integer recovered_diff) {
		this.recovered_diff = recovered_diff;
	}
}
