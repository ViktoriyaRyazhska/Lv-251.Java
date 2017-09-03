package com.softserve.edu.lv251.customannotations;

import com.softserve.edu.lv251.validators.EmailNotExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Yana Martynyak on 11.08.2017.
 */
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = EmailNotExistsValidator.class)
@Documented
public @interface EmailNotExists {
    String message() default "User with this email not exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
