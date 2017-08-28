package com.softserve.edu.lv251.validators;

import com.softserve.edu.lv251.customannotations.EmailAlreadyExists;
import com.softserve.edu.lv251.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Yana Martynyak on 11.08.2017.
 */
public class EmailAlreadyExistsValidator implements ConstraintValidator<EmailAlreadyExists,String> {
    @Autowired
    private UserService userService;
    @Override
    public void initialize(EmailAlreadyExists emailAlreadyExists) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return userService.findByEmail(email) == null;
    }
}
