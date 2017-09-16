package com.softserve.edu.lv251.controllers;

import com.softserve.edu.lv251.config.Base64;
import com.softserve.edu.lv251.config.Mapper;
import com.softserve.edu.lv251.constants.Constants;
import com.softserve.edu.lv251.dto.pojos.PasswordDTO;
import com.softserve.edu.lv251.dto.pojos.PersonalInfoDTO;
import com.softserve.edu.lv251.entity.Contact;

import com.softserve.edu.lv251.entity.Message;
import com.softserve.edu.lv251.entity.User;
import com.softserve.edu.lv251.entity.security.UpdatableUserDetails;
import com.softserve.edu.lv251.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.List;


/**
 * Author: Brynetskyi Marian
 * Updated: Kovalevskyy Vitaliy
 */
@org.springframework.stereotype.Controller
public class UserCabinetController {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private UserService userService;

    @Autowired
    private ContactsService contactsService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    RespondService respondService;

    @Autowired
    private Mapper mapper;
    @Autowired
    private MessageService messageService;

    /**
     * Author: Brynetskyi Marian
     * Updated: Kovalevskyy Vitaliy
     */
    @GetMapping("/user/cabinet")
    public String userProfileGET(ModelMap model, Principal principal) {

        User user = userService.findByEmail(principal.getName());
        Contact contact = user.getContact();
        PersonalInfoDTO personalInfoDTO = new PersonalInfoDTO();
        PasswordDTO passwordDTO = new PasswordDTO();
        List<Message> messages= messageService.getAll();
        mapper.map(user, personalInfoDTO);

        mapper.map(contact, personalInfoDTO);
        model.addAttribute(Constants.Controller.PHOTO, user.getPhoto());
        model.addAttribute(Constants.Controller.PERSONAL_INFO_DTO, personalInfoDTO);
        model.addAttribute(Constants.Controller.PASSWORD_DTO, passwordDTO);
        model.addAttribute("messages",messages);
        return "userCabinet";
    }

    /**
     * Author: Kovalevskyy Vitaliy
     */
    @PostMapping("/user/cabinet")
    public String userProfilePOST(@Valid @ModelAttribute PersonalInfoDTO personalInfoDTO, BindingResult bindingResult, Principal principal, ModelMap model) {
        User user = userService.findByEmail(principal.getName());

        if (bindingResult.hasErrors()) {
            personalInfoDTO.setPhoto(new Base64(user.getPhoto().getBytes()));
            model.addAttribute(Constants.Controller.PHOTO, user.getPhoto());
            return "userCabinet";
        }

        Contact contact = user.getContact();
        mapper.map(personalInfoDTO, user);
        mapper.map(personalInfoDTO, contact);
        userService.updateUser(user);
        contactsService.updateContacts(contact);
        ((UpdatableUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .setUsername(personalInfoDTO.getEmail());
        return "redirect:/user/cabinet";
    }

    /**
     * Author: Kovalevskyy Vitaliy
     */
    @PostMapping("/user/changePassword")
    public String savePassword(@Valid @ModelAttribute PasswordDTO passwordDTO, BindingResult bindingPasswordDTO,
                               @ModelAttribute PersonalInfoDTO personalInfoDTO, BindingResult bindingInfoDTO,
                               Principal principal, ModelMap model) {
        User user = userService.findByEmail(principal.getName());
        Contact contact = user.getContact();

        if (bindingPasswordDTO.hasErrors()) {
            personalInfoDTO.setPhoto(new Base64(user.getPhoto().getBytes()));
            mapper.map(user, personalInfoDTO);

            mapper.map(contact, personalInfoDTO);
            model.addAttribute(Constants.Controller.PHOTO, user.getPhoto());
            model.addAttribute(Constants.Controller.PERSONAL_INFO_DTO, personalInfoDTO);

            return "userCabinet";
        }

        userService.changePassword(user, passwordDTO);
        return "redirect:/user/cabinet";
    }

    /**
     * Author: Pavlo Kuchereshko
     */
    @GetMapping("/user/medicalcard")
    public String medicalCardGET(ModelMap model, Principal principal, HttpServletRequest request) {

        User user = userService.findByEmail(principal.getName());
        //model.addAttribute("listAppointments", appointmentService.listAppointmensWithDoctor(user.getId()));
        model.addAttribute("listAppointments", appointmentService.getAppointmentByUserEmail(principal.getName()));
        model.addAttribute("date", new Date().getTime());

        return "userCabinetMedicalCard";
    }

    /**
     * Author: Marian Brynetskyy
     */
    @GetMapping("/user/doctors")
    public String doctorsGET(ModelMap model, Principal principal) {

        User user = userService.findByEmail(principal.getName());
        model.addAttribute("listAppointments", appointmentService.getAppointmentByUserEmail(principal.getName()));
        model.addAttribute("date", new Date().getTime());
        model.addAttribute("doctors", respondService.setResponded(user.getId(), doctorService.getDoctorsByUser(user.getId())));

        return "userCabinetDoctors";
    }

    /**
     * Created by Marian Brynetskyi
     */
    @RequestMapping(value = "/user/addRespond", method = RequestMethod.POST)
    public String addAppointment(ModelMap modelMap,
                                @RequestParam(Constants.Controller.DOCTOR_ID) long doctorId,
                                @RequestParam("description") String description,
                                @RequestParam("raiting") String raiting,
                                Principal principal) {

        respondService.AddRespond(Short.parseShort(raiting), description, userService.findByEmail(principal.getName()).getId(), doctorId);
        return doctorsGET(modelMap, principal);
    }

    @GetMapping("/user/messages")
    public String getMessages(){
        return "userCabinetMessages";
    }


}
