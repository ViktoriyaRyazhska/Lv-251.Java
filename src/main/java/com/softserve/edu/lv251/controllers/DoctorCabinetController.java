package com.softserve.edu.lv251.controllers;

import com.softserve.edu.lv251.config.Mapper;
import com.softserve.edu.lv251.constants.Constants;

import com.softserve.edu.lv251.service.AppointmentService;
import com.softserve.edu.lv251.service.DoctorService;
import com.softserve.edu.lv251.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

/**
 * Author: Vitaliy Kovalevskyy
 */
@org.springframework.stereotype.Controller
public class DoctorCabinetController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private Mapper mapper;

    @Autowired
    private Logger logger;

    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "/doctor/сabinet", method = RequestMethod.GET)
    public String home(ModelMap model, Principal principal, HttpServletRequest httpServletRequest) {

        model.addAttribute(Constants.Controller.DOC_APPS, appointmentService.getAllDoctorsAppointmentsAfterNow(principal.getName(), Calendar.getInstance().getTime()));

        model.addAttribute("locale", LocaleContextHolder.getLocale().getLanguage());
        return "doctor_schedule";
    }

    @RequestMapping(value = "doctor/patients", method = RequestMethod.GET)
    public String patients() {
        return "doctor_cabinet_patients2";
    }


}
