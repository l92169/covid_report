package main.java.edu.au.covidreporter.controller;

import edu.au.covidreporter.dto.CreateReportParametersDto;
import edu.au.covidreporter.dto.ErrorDto;
import edu.au.covidreporter.dto.FileDownloadDto;
import edu.au.covidreporter.dto.ReportDto;
import edu.au.covidreporter.model.ReportEntity;
import edu.au.covidreporter.service.CsvFileService;
import edu.au.covidreporter.service.DictionaryService;
import edu.au.covidreporter.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@CrossOrigin(allowCredentials = "true", originPatterns = "*")
public class CovidReporterController {

	private static final Logger LOG = LoggerFactory.getLogger(CovidReporterController.class);


	private final CsvFileService csvFileService;
	private final ReportService reportService;
	private final DictionaryService dictionaryService;

	public CovidReporterController(DictionaryService dictionaryService, ReportService reportService, CsvFileService csvFileService) {
		this.dictionaryService = dictionaryService;
		this.reportService = reportService;
		this.csvFileService = csvFileService;
	}


	/**
	 * GET endpoint for path "countries".
	 * Returns all different countries from the 'covid_data' table.
	 * @return all different countries
	 */
	@GetMapping("countries")
	public List<String> getAllCountries() {
		return dictionaryService.getAllCountries();
	}

	/**
	 * GET endpoint for path "reports".
	 * Returns all existing reports sorted by creation date descending (first is the most recent report,
	 * last is the oldest one). Data is taken from the 'report' DB table.
	 * @return all reports sorted by creation date descending
	 */

	@GetMapping("reports")
	public List<ReportDto> getAllReports() {
				return reportService.getAllReports();
	}

	/**
	 * POST endpoint for path "reports".
	 * Creates a new report for desired country and from/to dates.
	 * Report is created asynchronously so nothing is returned.
	 */

	@PostMapping("reports")
	public void createReport(
			@Valid
			@RequestBody
			CreateReportParametersDto parameters
	) {
		reportService.createReport(parameters);
	}



	/**
	 * GET endpoint for path "reports/{reportId}/download".
	 * Returns a ResponseEntity with status '200 OK' containing data for file downloading.
	 * @param reportId ID of a record in the 'report' DB table.
	 * @return ResponseEntity containing data for file downloading
	 */
	@GetMapping("reports/{reportId}/download")
	public ResponseEntity<byte[]> downloadReport(
			//TODO(**): map to the "reportId" Path Variable
			@PathVariable("reportId")
			Integer reportId
	) throws Exception {
		FileDownloadDto data = reportService.downloadReportFile(reportId);

		if (data != null) {
			return ResponseEntity.ok()  // sets status to '200 OK'
					.contentType(MediaType.valueOf("text/csv"))
					.contentLength(data.getContent().length)
					.header("Content-Disposition", "attachment; filename=\"" + data.getFileName() + "\"")
					.body(data.getContent());
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // return 404 'Not Found' status
		}
	}



	// Exception handlers =======================================================================================

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorDto> handleConstraintViolationException(ConstraintViolationException e) {
		LOG.error("Invalid parameters: {}", e.getMessage(), e);
		StringBuilder message = new StringBuilder();
		for (ConstraintViolation<?> error : e.getConstraintViolations()) {
			message.append(error.getPropertyPath()).append(": ").append(error.getMessage()).append("\n");
		}
		return ResponseEntity.badRequest()
				.contentType(MediaType.APPLICATION_JSON)
				.body(new ErrorDto(message.toString()));
	}

	@ExceptionHandler(BindException.class)
	public ResponseEntity<ErrorDto> handleBindException(BindException e) {
		LOG.error("Invalid parameters: {}", e.getMessage(), e);
		StringBuilder message = new StringBuilder();
		for (FieldError error : e.getBindingResult().getFieldErrors()) {
			message.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("\n");
		}
		for (ObjectError error : e.getBindingResult().getGlobalErrors()) {
			message.append(error.getDefaultMessage()).append("\n");
		}
		return ResponseEntity.badRequest()
				.contentType(MediaType.APPLICATION_JSON)
				.body(new ErrorDto(message.toString()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDto> handleException(Exception e) {
		LOG.error("Exception happened: {}", e.getMessage(), e);
		return ResponseEntity.internalServerError()
				.contentType(MediaType.APPLICATION_JSON)
				.body(new ErrorDto("Internal error: " + e.getMessage()));
	}

}
