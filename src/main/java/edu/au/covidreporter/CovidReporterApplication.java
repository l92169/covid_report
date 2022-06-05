package main.java.edu.au.covidreporter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "edu.au.covidreporter.repository")
public class CovidReporterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidReporterApplication.class, args);
	}

}
