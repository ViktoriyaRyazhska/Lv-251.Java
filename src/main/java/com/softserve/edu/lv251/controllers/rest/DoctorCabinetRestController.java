package com.softserve.edu.lv251.controllers.rest;


import com.softserve.edu.lv251.dto.pojos.AppointmentsDTO;
import com.softserve.edu.lv251.dto.pojos.DoctorCabinetUser;
import com.softserve.edu.lv251.entity.Appointment;
import com.softserve.edu.lv251.service.AppointmentService;
import com.softserve.edu.lv251.service.DoctorService;
import com.softserve.edu.lv251.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RestController
public class DoctorCabinetRestController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "/doctor/cabinet/getApp", method = RequestMethod.POST)
    public List<AppointmentsDTO> getApp(Principal principal) {

        return appointmentService.getAppiontmentbyDoctorsEmail(principal.getName());
    }


    @RequestMapping(value = "/doctor/cabinet/setApp/{id}", method = RequestMethod.GET)
    public void setApp(@PathVariable(value = "id") long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        appointment.setIsApproved(true);
        appointmentService.updateAppointment(appointment);
    }

    @RequestMapping(value = "/user/search")
    public List<DoctorCabinetUser> getUsers(@RequestParam String name) {
        return userService.searchByLetters(name);
    }



    @RequestMapping(value = "/user/addApp", method = RequestMethod.POST)
    public void addAppointment(HttpServletRequest request, Principal principal) throws java.text.ParseException {
        Date date;

        SimpleDateFormat isoFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
        isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        date = isoFormat.parse(request.getParameter("datatime"));


        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(date);
        appointment.setIsApproved(true);
        appointment.setUser(userService.getUserByID(Long.parseLong(request.getParameter("input"))));
        appointment.setDoctor(doctorService.findByEmail(principal.getName()));
        appointmentService.addAppointment(appointment);
    }


}
