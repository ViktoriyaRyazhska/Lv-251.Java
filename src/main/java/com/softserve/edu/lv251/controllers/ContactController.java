package com.softserve.edu.lv251.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 */

@Controller
@RequestMapping(value = "/contact", method = RequestMethod.GET)
public class ContactController {

    @RequestMapping
    public String contactUs(){
        return "contact";
    }
}
