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
import org.springframework.web.bind.annotation.*;

import java.util.List;

;

/**
 * Created by Yana Martynyak on 29.08.2017.
 */

@RestController
@RequestMapping(value = "/api")
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

    @RequestMapping(value = "/editUser/{id}", method = RequestMethod.POST)
    public void saveUser(@RequestBody UserUpdate updateUser, @PathVariable("id") Long id) {
        User user = userService.getUserByID(id);
        mapper.map(updateUser, user);
        userService.updateUser(user);


        Contact contact = user.getContact();
        mapper.map(updateUser, contact);

        contactsService.updateContacts(contact);

    }


    @RequestMapping(value = "/getDoctorsToUser/{id}", method = RequestMethod.GET)
    public List<DoctorRespondDTO> getDoctors(@PathVariable("id") Long id) {
        List<DoctorRespondDTO> list = doctorService.getDoctorsByUser(id);
        return (list);

    }

    @RequestMapping(value = "/getAppointmentsToUser/{id}", method = RequestMethod.GET)
    public List<AppointmentsInfoDTO> getAppointments(@PathVariable("id") Long id) {
        return (appointmentService.getAppointmentsToUser(id));
    }

    @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
    public UserUpdate getUser(@PathVariable("id") Long id) {
        return userService.getById(id);
    }
}