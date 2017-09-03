package com.softserve.edu.lv251.controllers.rest;

import com.softserve.edu.lv251.dto.pojos.PatientDTO;
import com.softserve.edu.lv251.entity.User;
import com.softserve.edu.lv251.service.DoctorService;
import com.softserve.edu.lv251.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * Created by Taras on 01.08.2017.
 */
@RestController
@RequestMapping("/rest")
public class DoctorRestController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserService userService;

    @RequestMapping("/doctor/patients")
    public List<PatientDTO> getPatients(Principal principal) {
        User user = userService.findByEmail(principal.getName());
        return doctorService.getDoctorPatients(user.getId());
    }
}
