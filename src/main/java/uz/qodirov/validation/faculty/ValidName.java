package uz.qodirov.validation.faculty;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/6/2022 7:17 PM
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {FacultyValidName.class})
public @interface ValidName {
    String message() default "{NotNullIf.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
