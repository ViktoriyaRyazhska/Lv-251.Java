package com.softserve.edu.lv251.controllers;

import com.softserve.edu.lv251.entity.Clinics;
import com.softserve.edu.lv251.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@Controller
@RequestMapping("/clinics")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;


    @RequestMapping(value = "/{current}", method = RequestMethod.GET)
    public ModelAndView getClinics(@PathVariable("current") Integer chainIndex){
        ModelAndView model = new ModelAndView("clinics");
        model.addObject("getClinics", clinicService.getClinics(chainIndex, 10));
        model.addObject("numberChain", clinicService.numberOfPaging(10));
        model.addObject("maxSize", clinicService.getAllClinics().size());
        return model;
    }




    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public String clinicDetails(@RequestParam(name = "id", required = true)long id, Model model){
        Clinics clinic = clinicService.getClinicByID(id);
        model.addAttribute("clinic", clinic);
        model.addAttribute("mappoint", clinic.getContact().getAddress() + " " + clinic.getContact().getCity());
        return "clinic_details";
    }


}
