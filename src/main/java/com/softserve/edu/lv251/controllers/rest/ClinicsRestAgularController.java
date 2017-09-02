package com.softserve.edu.lv251.controllers.rest;

import com.softserve.edu.lv251.dto.pojos.ClinicsAngularDTO;
import com.softserve.edu.lv251.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin({"*"})
public class ClinicsRestAgularController {
    @Autowired
    ClinicService clinicService;

    @RequestMapping(value = "api/getAllClinics", method = RequestMethod.GET)
    List<ClinicsAngularDTO> getAllClinics() {
        return clinicService.getAllClinicsDto();
    }
}
