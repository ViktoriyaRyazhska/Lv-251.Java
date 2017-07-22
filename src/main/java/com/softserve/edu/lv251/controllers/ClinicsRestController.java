package com.softserve.edu.lv251.controllers;

import com.softserve.edu.lv251.entity.Clinics;
import com.softserve.edu.lv251.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by Taras on 14.07.2017.
 */
//@RestController
    @Controller
public class ClinicsRestController {

    @Autowired
    private ClinicService clinicService;


    @RequestMapping(value = "/clinics", method = RequestMethod.GET)
    public String greeting(@RequestParam(value="offset", required=false, defaultValue="0") String offset,
                                  @RequestParam(value="limit", required=false, defaultValue="10") String limit,
                                  Model model) {
        int offsetInt = 0;
        int limitInt = 10;

        try {
            offsetInt = Integer.parseInt(offset);
            limitInt = Integer.parseInt(limit);
        }catch (NumberFormatException e){
            // TODO return error json maybe?
        }

        model.addAttribute("clinic", new Clinics());
        model.addAttribute("listClinics", this.clinicService.getWithOffsetOrderedByName(offsetInt, limitInt));

        /*Clinics clinics = new Clinics();
        clinics.setClinic_name("lol");
        clinics.setDescription("lololol");
        try {
            clinics.setPhoto(Files.readAllBytes(Paths.get("C:\\Images\\logo-victoria-center.jpg")));
        } catch (IOException e) {
            System.out.println("error reading image");
        }
        clinics.setContact(new Contacts());

        clinicService.addClinic(clinics);*/
        return "clinics";
    }

    /*@RequestMapping("/firstclinic")
    public Clinics getFirst(){
        return clinicService.getFirst();
    }*/


}
