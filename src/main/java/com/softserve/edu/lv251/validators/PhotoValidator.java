package com.softserve.edu.lv251.validators;

import com.softserve.edu.lv251.customannotations.ValidPhoto;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Yana Martynyak on 09.08.2017.
 */
public class PhotoValidator implements ConstraintValidator<ValidPhoto,Object> {

    @Override
    public void initialize(ValidPhoto validPhoto) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        MultipartFile multipartFile = (MultipartFile) o;
        return ((multipartFile.getContentType().equals("image/jpeg") || multipartFile.isEmpty()||
                multipartFile.getContentType().equals("image/png"))) && multipartFile.getSize()<=100000;

    }
}
