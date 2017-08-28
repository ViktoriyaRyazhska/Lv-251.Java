package com.softserve.edu.lv251.validators;

import com.softserve.edu.lv251.customannotations.EmailNotExists;
import com.softserve.edu.lv251.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Yana Martynyak on 11.08.2017.
 */
public class EmailNotExistsValidator implements ConstraintValidator<EmailNotExists, String>{
    @Autowired
    private UserService userService;

    @Override
    public void initialize(EmailNotExists emailNotExists) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if(userService.findByEmail(email)!=null){
            return  true;
        }else {
        return false;}
    }
}
