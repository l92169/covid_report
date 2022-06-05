package main.java.edu.au.covidreporter.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class RealDateFormatValidator implements ConstraintValidator<RealDateFormat, String> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public void initialize(RealDateFormat format) {
    }
    @Override
    public boolean isValid(String dateString, ConstraintValidatorContext cxt) {
        if (dateString == null || dateString.trim().length() == 0) {
            return true;
        }
        try {
            LocalDate.parse(dateString,
                    DateTimeFormatter.ofPattern("uuuu-M-d")
                            .withResolverStyle(ResolverStyle.STRICT)
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}