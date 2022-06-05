package main.java.edu.au.covidreporter.service;

import edu.au.covidreporter.dto.CreateReportParametersDto;
import edu.au.covidreporter.dto.FileDownloadDto;
import edu.au.covidreporter.dto.ReportDto;
import edu.au.covidreporter.model.CovidDataEntity;
import edu.au.covidreporter.model.ReportEntity;
import edu.au.covidreporter.repository.CovidDataRepository;
import edu.au.covidreporter.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service("reportService")
public class ReportService {

	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Value("${edu.au.report.base-path}")
	private String basePath;

	private final ReportRepository reportRepository;
	private final CovidDataRepository covidDataRepository;
	private final CsvFileService csvFileService;

	@Autowired
	public ReportService(ReportRepository reportRepository, CovidDataRepository covidDataRepository, CsvFileService csvFileService) {
		this.reportRepository = reportRepository;
		this.covidDataRepository = covidDataRepository;
		this.csvFileService = csvFileService;
	}

	/**
	 * Returns all existing reports sorted by creation date descending (first is the most recent report,
	 * last is the oldest one). Data is taken from the 'report' DB table.
	 * @return all reports sorted by creation date descending
	 */
	public List<ReportDto> getAllReports() {

		List<ReportEntity> reports = reportRepository.getAllReports();
		List<ReportDto> dtos = ReportUtils.toDtoList(reports);
		return dtos;
	}

	/**
	 * Creates a new report. Runs asynchronously.
	 * @param parameters report creation parameters: country and from/to dates
	 */

	@Async("covidReporterAsyncExecutor")
	public void createReport(CreateReportParametersDto parameters) {
		// Request parameters:
		String country = parameters.getCountry();
		LocalDate from = LocalDate.parse(parameters.getFromDate(), dateFormatter);  // converting 'fromDate' to LocalDate
		LocalDate to = LocalDate.parse(parameters.getToDate(), dateFormatter);  // converting 'toDate' to LocalDate

		List<CovidDataEntity> covidData = covidDataRepository.getCovidDataByCountryFromTo(country, from, to);
		String filename = csvFileService.saveReportToFile(parameters, covidData);

		ReportEntity report = new ReportEntity();
		report.setFile_name(filename);
		reportRepository.save(report);
	}

	/**
	 * Returns a DTO with file name and contents (as byte[])
	 * @param reportId report ID
	 * @return file name + file contents
	 */
	public FileDownloadDto downloadReportFile(Integer reportId) {

		ReportEntity report = reportRepository.getReportbyId(reportId);
		if (report.getFile_name().length()==0){
			return null;
		}

		else {
			byte[] file_data = csvFileService.getFileContents(report.getFile_name());
			FileDownloadDto fileDownloadDto = new FileDownloadDto();
			fileDownloadDto.setFileName(report.getFile_name());
			fileDownloadDto.setContent(file_data);
			return fileDownloadDto;
		}
	}

}
