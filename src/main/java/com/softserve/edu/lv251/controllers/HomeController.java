package com.softserve.edu.lv251.controllers;

import com.softserve.edu.lv251.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Created by Admin on 23.07.2017.
 */
@Controller
public class HomeController {
    @Autowired
    private ClinicService clinicService;
    @Autowired
    private DistrictsService districtsService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private SpecializationService specializationService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(ModelMap model, Principal principal, HttpServletRequest request) {


        if (principal != null) {
            request.getSession().setAttribute("username", userService.findByEmail(principal.getName()).getFirstname() + " " +
                    userService.findByEmail(principal.getName()).getLastname());
        }
        return "home";
    }



}
