package com.softserve.edu.lv251.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Added by Pavlo Kuchereshko.
 */
@ControllerAdvice
public class ErrorController {

    @Autowired
    private Logger logger;

    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public String handleError(Exception e){
        logger.error(e);
        return "errorPage";
    }
}