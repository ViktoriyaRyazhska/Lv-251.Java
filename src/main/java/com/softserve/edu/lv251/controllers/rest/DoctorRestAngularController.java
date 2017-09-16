package com.softserve.edu.lv251.controllers.rest;


import com.softserve.edu.lv251.dto.pojos.ClinicsAngularDTO;
import com.softserve.edu.lv251.dto.pojos.DoctorSearchAngularDTO;
import com.softserve.edu.lv251.dto.pojos.DoctorsSearchDTO;
import com.softserve.edu.lv251.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin({"*"})
public class DoctorRestAngularController {
    @Autowired
    DoctorService doctorService;

    @RequestMapping(value = "api/getAllDoctors", method = RequestMethod.GET)
    List<DoctorSearchAngularDTO> getAllDoctors() {
        return doctorService.getAllDoctors();
    }
}
