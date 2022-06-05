package main.java.edu.au.covidreporter.validator;

import edu.au.covidreporter.dto.CreateReportParametersDto;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class DateRangeValidator implements ConstraintValidator<DateRange, CreateReportParametersDto> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public void initialize(DateRange annotation) {
    }

    @Override
    public boolean isValid(CreateReportParametersDto value, ConstraintValidatorContext cxt) {
        return value.getToDate().compareTo(value.getFromDate()) > 0;
    }
}