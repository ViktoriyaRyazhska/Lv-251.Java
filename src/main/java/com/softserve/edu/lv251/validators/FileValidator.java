package com.softserve.edu.lv251.validators;

import com.softserve.edu.lv251.customannotations.ValidFile;
import com.softserve.edu.lv251.model.FileBucket;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Yana Martynyak on 04.08.2017.
 */
public class FileValidator implements ConstraintValidator<ValidFile, Object> {
    @Override
    public void initialize(ValidFile validFile) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        FileBucket fileBucket = (FileBucket) o;
        if ((fileBucket.getMultipartFile().getContentType().equals("image/jpeg") ||

                fileBucket.getMultipartFile().getContentType().equals("image/png")) &&
                !fileBucket.getMultipartFile().isEmpty()
                ) {
            return true;
        } else {
            return false;
        }

    }
}
