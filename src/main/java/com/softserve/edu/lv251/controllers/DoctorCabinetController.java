package com.softserve.edu.lv251.controllers;

import com.softserve.edu.lv251.config.Mapper;
import com.softserve.edu.lv251.constants.Constants;
import com.softserve.edu.lv251.dto.pojos.AppointmentsDTO;

import com.softserve.edu.lv251.entity.Appointment;
import com.softserve.edu.lv251.entity.User;

import com.softserve.edu.lv251.service.AppointmentService;
import com.softserve.edu.lv251.service.DoctorsService;
import com.softserve.edu.lv251.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Author: Vitaliy Kovalevskyy
 */
@org.springframework.stereotype.Controller
public class DoctorCabinetController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private Mapper mapper;

    @Autowired
    private Logger logger;

    @Autowired
    private DoctorsService doctorsService;

    @RequestMapping(value = "/doctor/сabinet",method = RequestMethod.GET)
    public String home(ModelMap model,Principal principal,HttpServletRequest httpServletRequest){

        model.addAttribute(Constants.Controller.DOC_APPS,appointmentService.getAllDoctorsAppointmentsAfterNow(principal.getName(), Calendar.getInstance().getTime()));

        model.addAttribute("locale", LocaleContextHolder.getLocale().getLanguage());
        return "doctor_schedule";
    }

    @RequestMapping(value = "/doctor/cabinet/getApp", method = RequestMethod.POST)
    @ResponseBody
    public List<AppointmentsDTO> getApp(Principal principal) {
        List<AppointmentsDTO> appointmentsDTOs = new LinkedList<>();
        for (Appointment appo : appointmentService.getAppiontmentbyDoctorsEmail(principal.getName())) {
            AppointmentsDTO appointmentsDTO = new AppointmentsDTO();
            mapper.map(appo, appointmentsDTO);
            appointmentsDTOs.add(appointmentsDTO);
        }
        return appointmentsDTOs;
    }


    @RequestMapping(value = "/doctor/cabinet/setApp/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void setApp(@PathVariable(value = "id") long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        appointment.setIsApproved(true);
        appointmentService.updateAppointment(appointment);
    }

    @RequestMapping(value = "/user/search")
    @ResponseBody
    public List<User> getUsers(@RequestParam String name) {
        List<User> usersses = userService.searchByLetters(name);
        return usersses;
    }

    @RequestMapping(value = "doctor/patients", method = RequestMethod.GET)
    public String patients() {
        return "doctor_cabinet_patients";
    }


    @RequestMapping(value = "/user/addApp", method = RequestMethod.POST)
    @ResponseBody
    public void addAppointment(HttpServletRequest request, Principal principal)
    {
        Date date;
        try {

            SimpleDateFormat isoFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
            isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            date = isoFormat.parse(request.getParameter("datatime"));

            if(date.before(new Date())){
                throw new Exception();
            }

            Appointment appointment = new Appointment();
            appointment.setAppointmentDate(date);
            appointment.setIsApproved(true);
            appointment.setUser(userService.getUserByID(Long.parseLong(request.getParameter("input"))));
            appointment.setDoctor(doctorsService.findByEmail(principal.getName()));
            appointmentService.addAppointment(appointment);
        } catch (Exception e) {
            logger.error("Some Errors");
        }
    }


}
