package com.softserve.edu.lv251.controllers.rest;

import com.softserve.edu.lv251.dto.pojos.ClinicSearchDTO;
import com.softserve.edu.lv251.dto.pojos.DistrictsDTO;
import com.softserve.edu.lv251.dto.pojos.DoctorsSearchDTO;
import com.softserve.edu.lv251.dto.pojos.SpecializationDTO;
import com.softserve.edu.lv251.entity.Specialization;
import com.softserve.edu.lv251.service.ClinicService;
import com.softserve.edu.lv251.service.DistrictsService;
import com.softserve.edu.lv251.service.DoctorService;
import com.softserve.edu.lv251.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Yana Martynyak on 15.08.2017.
 */
@Controller
public class MainSearchController {
    @Autowired
    private ClinicService clinicService;
    @Autowired
    private DistrictsService districtsService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private SpecializationService specializationService;

    @ResponseBody
    @RequestMapping(value = "/rest/autocomplete/clinics/byName")
    public List<ClinicSearchDTO> autocompleteClinics(@RequestParam("name") String name) {
        return clinicService.searchByLetters(name);
    }

    @ResponseBody
    @RequestMapping(value = "/rest/search/clinic/{id}")
    public ClinicSearchDTO searchClinics(@PathVariable("id") Long id) {
        return clinicService.clinicSearchById(id);
    }


    @ResponseBody
    @RequestMapping(value = "/rest/autocomplete/districts/ByName")
    public List<DistrictsDTO> autocompleteDistricts(@RequestParam("name") String name) {
        return districtsService.findByName(name);
    }

    @ResponseBody
    @RequestMapping(value = "/rest/search/clinics/byDistrict/{name}")
    public List<ClinicSearchDTO> clinicsByDistrict(@PathVariable("name") String name) {
        return clinicService.findByDistrict(name);
    }

    @ResponseBody
    @RequestMapping(value = "/rest/search/doctors/byDistrict/{name}")
    public List<DoctorsSearchDTO> doctorsByDistrict(@PathVariable("name") String name) {
        return doctorService.searchByDistrict(name);
    }
    @ResponseBody
    @RequestMapping(value = "/rest/autocomplete/specializations/byName")
    public List<SpecializationDTO> autocompleteSpec(@RequestParam("name") String name) {
        return specializationService.searchByLetters(name);
    }
    @ResponseBody
    @RequestMapping(value = "/rest/search/doctors/bySpec/{name}")
    public List<DoctorsSearchDTO> doctorsBySpec(@PathVariable("name") String name) {
        return doctorService.searchBySpecialization(name);
    }

    @ResponseBody
    @RequestMapping(value = "/rest/autocomplete/doctors/byName")
    public List<DoctorsSearchDTO> searchDoctors(@RequestParam String name) {
        return doctorService.searchByLetters(name);

    }
    @ResponseBody
    @RequestMapping(value = "/rest/search/doctor/{id}")
    public DoctorsSearchDTO doctorById(@PathVariable Long id) {
        return doctorService.findById(id);
    }

}
