package com.softserve.edu.lv251.controllers.rest;

import com.softserve.edu.lv251.config.Mapper;
import com.softserve.edu.lv251.dto.pojos.AppointmentsInfoDTO;
import com.softserve.edu.lv251.dto.pojos.DoctorRespondDTO;
import com.softserve.edu.lv251.dto.pojos.UserUpdate;
import com.softserve.edu.lv251.entity.Contact;
import com.softserve.edu.lv251.entity.User;
import com.softserve.edu.lv251.service.AppointmentService;
import com.softserve.edu.lv251.service.ContactsService;
import com.softserve.edu.lv251.service.DoctorService;
import com.softserve.edu.lv251.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

;

/**
 * Created by Yana Martynyak on 29.08.2017.
 */

@RestController
@RequestMapping(value = "/rest")
@CrossOrigin(origins = {"*"})
public class UserCabinetRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private Mapper mapper;
    @Autowired
    private ContactsService contactsService;

    @RequestMapping(value = "/api/editUser/", method = RequestMethod.POST)
    public void saveUser(@RequestBody UserUpdate updateUser) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        User user = userService.getUserByID(userService.findByEmail(userDetails.getUsername()).getId());
        mapper.map(updateUser, user);
        userService.updateUser(user);


        Contact contact = user.getContact();
        mapper.map(updateUser, contact);

        contactsService.updateContacts(contact);

    }


    @RequestMapping(value = "/api/getDoctorsToUser", method = RequestMethod.GET)
    public List<DoctorRespondDTO> getDoctors() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return doctorService.getDoctorsByUser(userService.findByEmail(userDetails.getUsername()).getId());

    }

    @RequestMapping(value = "/api/getAppointmentsToUser", method = RequestMethod.GET)
    public List<AppointmentsInfoDTO> getAppointments() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (appointmentService.getAppointmentsToUser(userService.findByEmail(userDetails.getUsername()).getId()));
    }

    @RequestMapping(value = "/api/getPendingAppointmentsToUser", method = RequestMethod.GET)
    public List<AppointmentsInfoDTO> getPendingAppointments() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return (appointmentService.getPendingAppointmentsToUser(userService.findByEmail(userDetails.getUsername()).getId()));
    }

    @RequestMapping(value = "/api/getUser", method = RequestMethod.GET)
    public UserUpdate getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return userService.getById(userService.findByEmail(userDetails.getUsername()).getId());

    }
}