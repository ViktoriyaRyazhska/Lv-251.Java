package com.softserve.edu.lv251.controllers;

import com.softserve.edu.lv251.constants.Constants;
import com.softserve.edu.lv251.entity.Doctor;
import com.softserve.edu.lv251.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by Yana Martynyak on 23.07.2017.
 * Updated: Brynetskyi Marian
 */
@org.springframework.stereotype.Controller
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

        model.addAttribute("getDoctors", pagingSizeService.getEntity(chainIndex, 10));
        model.addAttribute(Constants.Controller.NUMBER_CHAIN, pagingSizeService.numberOfPaging(10));
        model.addAttribute(Constants.Controller.DOC_APPS, appointmentService.getAllDoctorsAppointmentsAfterNow());
        return Constants.Controller.ALL_DOCTORS;

    }

    /**
     * Created by Marian Brynetskyi
     *
     * @param modelMap   - model
     * @param localdate  - date of ppointment
     * @param doctorId   - docId with wrong date
     * @param chainIndex - id of page
     * @param principal  - user
     * @return
     */
    @RequestMapping(value = "/user/addAppointment", method = RequestMethod.POST)
    public ModelAndView addAppointment(Model modelMap, @RequestParam("datetime") String localdate,

                                       @RequestParam(Constants.Controller.DOCTOR_ID) long doctorId,
                                       @RequestParam(Constants.Controller.CURRENT) Integer chainIndex, Principal principal) {

        ModelAndView modelAndView = new ModelAndView();
        if (!appointmentService.createAppointment(localdate, principal.getName(), doctorId)) {
            modelAndView.addObject(Constants.Controller.DATE_FLAG, true);

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
        return "doctor_details";
    }

}
