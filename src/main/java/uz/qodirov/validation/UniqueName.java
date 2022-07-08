package uz.qodirov.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/5/2022 4:49 PM
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {UniqueUniverName.class})
public @interface UniqueName {

    String message() default "{NotNullIf.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
