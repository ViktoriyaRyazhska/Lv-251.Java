package com.softserve.edu.lv251.controllers;

import com.softserve.edu.lv251.config.Base64;
import com.softserve.edu.lv251.config.Mapper;
import com.softserve.edu.lv251.constants.Constants;
import com.softserve.edu.lv251.dto.pojos.PasswordDTO;
import com.softserve.edu.lv251.dto.pojos.PersonalInfoDTO;
import com.softserve.edu.lv251.entity.Contacts;
import com.softserve.edu.lv251.entity.User;
import com.softserve.edu.lv251.entity.security.UpdatableUserDetails;
import com.softserve.edu.lv251.service.AppointmentService;
import com.softserve.edu.lv251.service.ContactsService;
import com.softserve.edu.lv251.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;


/**
 * Author: Brynetskyi Marian
 * Updated: Kovalevskyy Vitaliy
 */
@org.springframework.stereotype.Controller
public class UserCabinetController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactsService contactsService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private Mapper mapper;

    /**
     * Author: Brynetskyi Marian
     * Updated: Kovalevskyy Vitaliy
     */
    @GetMapping("/user/cabinet")
    public String userProfileGET(ModelMap model, Principal principal) {

        User user = userService.findByEmail(principal.getName());
        Contacts contacts = user.getContact();
        PersonalInfoDTO personalInfoDTO = new PersonalInfoDTO();
        PasswordDTO passwordDTO = new PasswordDTO();

        mapper.map(user, personalInfoDTO);
        mapper.map(contacts, personalInfoDTO);
        model.addAttribute(Constants.Controller.PHOTO, user.getPhoto());
        model.addAttribute(Constants.Controller.PERSONAL_INFO_DTO, personalInfoDTO);
        model.addAttribute(Constants.Controller.PASSWORD_DTO, passwordDTO);
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

        Contacts contacts = user.getContact();
        mapper.map(personalInfoDTO, user);
        mapper.map(personalInfoDTO, contacts);
        userService.updateUser(user);
        contactsService.updateContacts(contacts);
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
                               Principal principal,  ModelMap model) {
        User user = userService.findByEmail(principal.getName());
        Contacts contacts = user.getContact();

        if (bindingPasswordDTO.hasErrors()){
            personalInfoDTO.setPhoto(new Base64(user.getPhoto().getBytes()));
            mapper.map(user, personalInfoDTO);
            mapper.map(contacts, personalInfoDTO);
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
        model.addAttribute("listAppointments", appointmentService.listAppointmensWithDoctor(user.getId()));
        model.addAttribute("date", new Date().getTime());

        return "userCabinetMedicalCard";
    }
}
