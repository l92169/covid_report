package main.java.edu.au.covidreporter.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RealDateFormatValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RealDateFormat {
    String message() default "Date should be real.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
