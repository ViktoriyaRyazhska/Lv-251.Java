package com.softserve.edu.lv251.controllers.rest;

import com.softserve.edu.lv251.config.Mapper;
import com.softserve.edu.lv251.dto.pojos.*;
import com.softserve.edu.lv251.entity.Contact;
import com.softserve.edu.lv251.entity.User;
import com.softserve.edu.lv251.service.AppointmentService;
import com.softserve.edu.lv251.service.ContactsService;
import com.softserve.edu.lv251.service.DoctorService;
;
import com.softserve.edu.lv251.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
    public ResponseEntity<Void> saveUser(@RequestBody UserUpdate updateUser, @PathVariable("id") Long id) {
        User user = userService.getUserByID(id);
        mapper.map(updateUser,user);
        userService.updateUser(user);
        Contact contact= user.getContact();

        mapper.map(updateUser,contact);

        contactsService.updateContacts(contact);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @RequestMapping(value = "/getDoctorsToUser/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<DoctorRespondDTO>> getDoctors(@PathVariable("id") Long id, Principal principal) {
        List<DoctorRespondDTO> list=doctorService.getDoctorsByUser(id);
        return new ResponseEntity<List<DoctorRespondDTO>>(list,HttpStatus.OK);

    }
    @RequestMapping(value = "/getAppointmentsToUser/{id}", method = RequestMethod.GET)
   public  ResponseEntity<List<AppointmentsInfoDTO>> getAppointments(@PathVariable ("id") Long id){
        return new ResponseEntity<List<AppointmentsInfoDTO>>(appointmentService.getAppointmentsToUser(id),HttpStatus.OK);
   }
    @RequestMapping(value = "/getUser/{email:.+}", method = RequestMethod.GET)
  public UserUpdate getUser(@PathVariable("email") String email){
        System.out.println(email);
        System.out.println( userService.getByEmail(email));
       return userService.getByEmail(email);
  }
}