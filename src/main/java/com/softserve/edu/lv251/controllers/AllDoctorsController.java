package com.softserve.edu.lv251.controllers;

import com.softserve.edu.lv251.entity.Doctor;
import com.softserve.edu.lv251.service.AppointmentService;
import com.softserve.edu.lv251.service.DoctorService;
import com.softserve.edu.lv251.service.PagingSizeService;
import com.softserve.edu.lv251.service.RespondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

import static com.softserve.edu.lv251.constants.Constants.Controller.*;
import static com.softserve.edu.lv251.constants.Constants.View.ALL_DOCTORS;
import static com.softserve.edu.lv251.constants.Constants.View.DOCTOR_DETAILS;

/**
 * Created by Yana Martynyak on 23.07.2017.
 * Updated: Brynetskyi Marian
 */
@Controller
public class AllDoctorsController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private RespondService respondService;

    @Autowired
    @Qualifier("doctorService")
    private PagingSizeService<Doctor> pagingSizeService;

    @Autowired
    private AppointmentService appointmentService;


    @RequestMapping(value = "/allDoctors/{current}", method = RequestMethod.GET)
    public String allDoctors(@PathVariable("current") Integer chainIndex, Model model) {

        model.addAttribute("getDoctors", pagingSizeService.getEntities(chainIndex, 10));
        model.addAttribute(NUMBER_CHAIN, pagingSizeService.numberOfPaging(10));
        model.addAttribute(DOC_APPS, appointmentService.getAllDoctorsAppointmentsAfterNow());
        return ALL_DOCTORS;

    }

    /**
     * Created by Marian Brynetskyi
     *
     * @param modelMap   - model
     * @param localdate  - date of ppointment
     * @param doctorId   - docId with wrong date
     * @param chainIndex - id of page
     * @param principal  - user
     * @return modelAndView
     */
    @RequestMapping(value = "/user/addAppointment", method = RequestMethod.POST)
    public ModelAndView addAppointment(Model modelMap, @RequestParam("datetime") String localdate,

                                       @RequestParam(DOCTOR_ID) long doctorId,
                                       @RequestParam(CURRENT) Integer chainIndex, Principal principal) {

        ModelAndView modelAndView = new ModelAndView();
        if (!appointmentService.createAppointment(localdate, principal.getName(), doctorId)) {
            modelAndView.addObject(DATE_FLAG, true);
            modelAndView.addObject("doc", doctorId);
            modelAndView.setViewName("redirect:/" + allDoctors(chainIndex, modelMap) + "/" + chainIndex);
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/" + allDoctors(chainIndex, modelMap) + "/" + chainIndex);
        return modelAndView;
    }


    @RequestMapping(value = "/doctors/{id}", method = RequestMethod.GET)
    public String Doctor(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", doctorService.find(id));
        model.addAttribute("responds",respondService.getAllRespondsByDoctor(id));
        return DOCTOR_DETAILS;
    }

}
