package com.softserve.edu.lv251.controllers;

import com.softserve.edu.lv251.constants.Constants;
import com.softserve.edu.lv251.service.AppointmentService;
import com.softserve.edu.lv251.service.TestResultService;
import com.softserve.edu.lv251.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;

import static com.softserve.edu.lv251.constants.Constants.Controller.FAILED;
import static com.softserve.edu.lv251.constants.Constants.Controller.SUCCESS;
import static com.softserve.edu.lv251.constants.Constants.View.*;

/**
 * Author: Vitaliy Kovalevskyy
 */
@Controller
@RequestMapping("/doctor")
public class DoctorCabinetController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private TestResultService testResultService;

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/сabinet", method = RequestMethod.GET)
    public String home(ModelMap model, Principal principal) {
        model.addAttribute(Constants.Controller.DOC_APPS,
                appointmentService.getAllDoctorsAppointmentsAfterNow(principal.getName(), Calendar.getInstance().getTime()));
        model.addAttribute("locale", LocaleContextHolder.getLocale().getLanguage());
        return DOCTOR_SCHEDULE;
    }

    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public ModelAndView patients(ModelAndView modelAndView) {
        modelAndView.setViewName(DOCTOR_CABINET_PATIENTS2);
        return modelAndView;
    }

    @RequestMapping(value = "/patients", method = RequestMethod.POST)
    public ModelAndView addTest(ModelAndView modelAndView,
                                @RequestParam("userId") long userId,
                                @RequestParam("description") String description,
                                @RequestParam("test") String test,
                                @RequestParam("startDate") String startDate,
                                @RequestParam("endDate") String endDate) {

        if (testResultService.addTestResult(userId, description, test, startDate, endDate)) {
            modelAndView.addObject(SUCCESS, SUCCESS);
        } else {
            modelAndView.addObject(SUCCESS, FAILED);
        }
        modelAndView.setViewName(DOCTOR_CABINET_PATIENTS2);
        return modelAndView;
    }


    /**
     * Created by Marian Brynetskyi
     */
    @RequestMapping(value = "/patient/{id}", method = RequestMethod.GET)
    public ModelAndView testsGET(ModelAndView modelAndView, @PathVariable("id") long userId) {
        modelAndView.addObject("tests", testResultService.getUserTestResults(userId));
        modelAndView.addObject("date", new Date());
        modelAndView.addObject("userId", userId);
        modelAndView.addObject("testsNames", testService.getTestsNames());
        modelAndView.setViewName(DOCTOR_PATIENT);
        return modelAndView;
    }


    /**
     * Created by Marian Brynetskyi
     */
    @RequestMapping(value = "/patient/{id}", method = RequestMethod.POST)
    public ModelAndView editTest(ModelAndView modelAndView,
                                 @PathVariable("id") long userId,
                                 @RequestParam("testId") long testId,
                                 @RequestParam("description") String description,
                                 @RequestParam("test") String test,
                                 @RequestParam("startDate") String startDate,
                                 @RequestParam("endDate") String endDate) {

        if (testResultService.editTestResult(testId, description, test, startDate, endDate)) {
            modelAndView.addObject(SUCCESS, SUCCESS);
        } else {
            modelAndView.addObject(SUCCESS, FAILED);
        }

        return testsGET(modelAndView, userId);
    }
}
