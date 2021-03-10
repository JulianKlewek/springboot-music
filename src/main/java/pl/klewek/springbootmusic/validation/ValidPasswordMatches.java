package pl.klewek.springbootmusic.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface ValidPasswordMatches {
    String message() default "{pl.klewek.springbootmusic.validation.ValidPasswordMatches.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
