package com.softserve.edu.lv251.controllers.rest;

import com.softserve.edu.lv251.dto.pojos.AppointmentDTO;
import com.softserve.edu.lv251.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Taras on 05.08.2017.
 */
@RestController
@RequestMapping(value = "/rest/api/user")
public class UserRestController {

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping(value = "/appointments")
    List<AppointmentDTO> getAppointments() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return appointmentService.getAppointmentDtoByUserEmail(userDetails.getUsername());
    }


}
