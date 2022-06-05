package main.java.edu.au.covidreporter.service;

import edu.au.covidreporter.dto.CreateReportParametersDto;
import edu.au.covidreporter.model.CovidDataEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service("csvFileService")
public class CsvFileService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CsvFileService.class);

	private static final List<String> HEADER = List.of(
			"date_year",
			"date_month",
			"date_day",
			"latitude",
			"longitude",
			"country_region",
			"province_state",
			"confirmed_diff",
			"deaths_diff",
			"recovered_diff"
	);

	@Value("${edu.au.report.base-path}")
	private String basePath;

	private String createFileName(CreateReportParametersDto parameters) {
		return String.format("Covid data for %s from %s to %s (%s).csv",
				parameters.getCountry(),
				parameters.getFromDate(),
				parameters.getToDate(),
				System.currentTimeMillis()  // current time in milliseconds (to avoid duplicates)
				);
	}

	/**
	 * Writes data to a CSV file.
	 * @param parameters parameters for report
	 * @param data report data selected from DB
	 * @return new file's name
	 */
	public String saveReportToFile(CreateReportParametersDto parameters, List<CovidDataEntity> data) {
		String fileName = createFileName(parameters);
		// Combine basic directory path and file name to get full file path:
		String filePath = Paths.get(basePath, fileName).toString();

		try (FileWriter fileWriter = new FileWriter(filePath)) {
			// Create a CSVPrinter which prints to this file:
			try (CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT)) {

				// Print header:
				csvPrinter.printRecord(HEADER);

				// Print rows:
				for (CovidDataEntity covidDataEntity : data) {
					csvPrinter.printRecord(
							covidDataEntity.getDate_year(),
							covidDataEntity.getDate_month(),
							covidDataEntity.getDate_day(),
							covidDataEntity.getLatitude(),
							covidDataEntity.getLongitude(),
							covidDataEntity.getCountry_region(),
							covidDataEntity.getProvince_state(),
							covidDataEntity.getConfirmed_diff(),
							covidDataEntity.getDeaths_diff(),
							covidDataEntity.getRecovered_diff()
					);
				}
			} catch (IOException e) {
				LOGGER.error("Error While writing CSV", e);
			}
		} catch (IOException e) {
			LOGGER.error("Error While creating file writer", e);
		}
		return fileName;  // return new file's name
	}

	/**
	 * Returns contents of the file as byte[]
	 * @return contents of the file as byte[]
	 */
	public byte[] getFileContents(String fileName) {

		String path = basePath + "/" + fileName;
		File file = new File(path);

		FileInputStream fis = null;
		byte[] bArray = new byte[(int) file.length()];
		try{
			fis = new FileInputStream(file);
			fis.read(bArray);
			fis.close();
		}catch(IOException ioExp){
			ioExp.printStackTrace();
		}
		return bArray;
	}

}
