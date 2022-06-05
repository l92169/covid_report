package main.java.edu.au.covidreporter.service;

import edu.au.covidreporter.repository.CovidDataRepository;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//TODO: declare as a Service
@Service("DictionaryService")
public class DictionaryService{

	private final CovidDataRepository covidDataRepository;
	@Autowired
	public DictionaryService(CovidDataRepository covidDataRepository) {
		this.covidDataRepository = covidDataRepository;
	}

	/**
	 * Returns all different countries from the 'covid_data' table sorted ascending.
	 * @return all different countries sorted ascending
	 */

	public List<String> getAllCountries() {

		List<String> countries = covidDataRepository.getAllCountry();
		return countries;
	}
}
