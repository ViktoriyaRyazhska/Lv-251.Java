package com.softserve.edu.lv251.controllers;

import com.softserve.edu.lv251.constants.Constants;
import com.softserve.edu.lv251.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Calendar;

import static com.softserve.edu.lv251.constants.Constants.View.*;

/**
 * Author: Vitaliy Kovalevskyy
 */
@Controller
@RequestMapping("/doctor")
public class DoctorCabinetController {

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping(value = "/сabinet", method = RequestMethod.GET)
    public String home(ModelMap model, Principal principal) {
        model.addAttribute(Constants.Controller.DOC_APPS,
                appointmentService.getAllDoctorsAppointmentsAfterNow(principal.getName(), Calendar.getInstance().getTime()));
        model.addAttribute("locale", LocaleContextHolder.getLocale().getLanguage());
        return DOCTOR_SCHEDULE;
    }

    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public String patients() {
        return DOCTOR_CABINET_PATIENTS2;
    }


}
