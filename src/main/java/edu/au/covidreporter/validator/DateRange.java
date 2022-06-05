package main.java.edu.au.covidreporter.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.time.LocalDate;

@Documented
@Constraint(validatedBy = DateRangeValidator.class)
@Target( { ElementType.TYPE  })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateRange {
    String message()  default"toDate should be after fromDate.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}