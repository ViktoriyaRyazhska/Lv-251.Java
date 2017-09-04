package com.softserve.edu.lv251.customannotations;

import com.softserve.edu.lv251.validators.PhotoValidator;

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
 * Created by Admin on 09.08.2017.
 */
@Documented
@Constraint(validatedBy = PhotoValidator.class)
@Target({ TYPE,ANNOTATION_TYPE,FIELD})
@Retention(RUNTIME)
public @interface ValidPhoto {
    String message() default "Wrong content type ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
