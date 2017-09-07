package com.softserve.edu.lv251.controllers;


import com.softserve.edu.lv251.config.Mapper;
import com.softserve.edu.lv251.constants.Constants;
import com.softserve.edu.lv251.dto.pojos.ClinicInfoDTO;
import com.softserve.edu.lv251.dto.pojos.DoctorDTO;
import com.softserve.edu.lv251.dto.pojos.PhotoDTO;
import com.softserve.edu.lv251.dto.pojos.UserToDoctor;
import com.softserve.edu.lv251.entity.*;
import com.softserve.edu.lv251.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Locale;

import static com.softserve.edu.lv251.constants.Constants.View.*;

/**
 * Created by Yana Martynyak on 31.07.2017.
 */
@Controller
@RequestMapping(value = "/moderator")
public class ModeratorCabinetController {
    @Autowired
    private ModeratorService moderatorService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private Logger logger;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private Mapper mapper;

    @Autowired
    private ContactsService contactsService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private MessageService messageService;


    @GetMapping(value = "/cabinet")
    public String moderatorCabinet(Principal principal, Model model) {
        Moderator moderator = moderatorService.getByEmail(principal.getName());
        Clinic clinic = moderator.getClinic();
        Contact contact = clinic.getContact();
        ClinicInfoDTO clinicDTO = new ClinicInfoDTO();
        List<Message> messages = messageService.getAll();
        if (clinic != null) {
            List<Doctor> doctors = doctorService.getByClinic(clinic.getId());
            model.addAttribute(Constants.Controller.DOCTORS, doctors);
            mapper.map(clinic, clinicDTO);
        }

        if (contact != null) {
            mapper.map(contact, clinicDTO);
        }
        model.addAttribute(Constants.Controller.PHOTO_FORM, new PhotoDTO());
        model.addAttribute(Constants.Controller.MODERATOR, moderator);
        model.addAttribute(Constants.Controller.CLINIC_DTO, clinicDTO);
        model.addAttribute(Constants.Controller.MESSAGES, messages);
        return MODERATOR_CABINET;


    }

    @PostMapping("/cabinet")
    public String edit(@ModelAttribute("clinicDTO") @Valid ClinicInfoDTO clinicInfoDTO, BindingResult bindingResult,
                       Principal principal, RedirectAttributes model) {
        Locale currentLocale = LocaleContextHolder.getLocale();

        String messageError = messageSource.getMessage("messages.errorClinicName", null, currentLocale);
        Moderator moderator = moderatorService.getByEmail(principal.getName());

        Clinic clinic = moderator.getClinic();
        Contact contact = clinic.getContact();

        if (!bindingResult.hasErrors()) {
            mapper.map(clinicInfoDTO, clinic);
            mapper.map(clinicInfoDTO, contact);

            clinicService.updateClinic(clinic);
            contactsService.updateContacts(contact);
            return REDIRECT_MODERATOR_CABINET;
        } else {

            model.addFlashAttribute(Constants.Controller.CLASS_CSS, "alert alert-warning");
            model.addFlashAttribute(Constants.Controller.MESSAGE, messageError);

            return REDIRECT_MODERATOR_CABINET;
        }
    }

    @GetMapping(value = "/cabinet/doctors")
    public String moderatorAllDoctors(Principal principal, Model model) {
        Moderator moderator = moderatorService.getByEmail(principal.getName());
        List<Doctor> doctors = doctorService.getByClinic(moderator.getClinic().getId());
        model.addAttribute(Constants.Controller.DOCTORS, doctors);
        model.addAttribute(Constants.Controller.MODERATOR, moderator);
        return MODERATOR_CABINET_DOCTORS;

    }

    @GetMapping(value = "/cabinet/doctors/delete/{id}")
    public String deleteDoctor(@PathVariable("id") Long id) {
        doctorService.delete(doctorService.find(id));
        return REDIRECT_MODERATOR_CABINET_DOCTOR;

    }

    @GetMapping(value = "/cabinet/add/doctor")
    public String addDoctor(Model model, Principal principal) {
        model.addAttribute(Constants.Controller.DOCTOR_FORM, new DoctorDTO());
        Moderator moderator = moderatorService.getByEmail(principal.getName());

        List<Doctor> doctors = doctorService.getByClinic(moderator.getClinic().getId());
        model.addAttribute(Constants.Controller.DOCTORS, doctors);
        model.addAttribute(Constants.Controller.MODERATOR, moderator);
        return MODERATOR_ADD_DOCTOR;

    }

    @PostMapping(value = "/add/doctor")
    public String registerDoctor(@ModelAttribute("doctorForm") @Valid DoctorDTO doctorDTO, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            logger.warn("registerDoctor has errors");
            return MODERATOR_ADD_DOCTOR;
        } else {
            doctorService.addDoctorAccount(doctorDTO, principal.getName());
            return REDIRECT_MODERATOR_CABINET_DOCTOR;
        }
    }


    @PostMapping(value = "/upload/clinicPhoto")
    public String uploadPhoto(@ModelAttribute("photoForm") @Valid PhotoDTO photoDTO, BindingResult bindingResult,
                              Principal principal, RedirectAttributes model) {
        if (bindingResult.hasErrors()) {
            Locale currentLocale = LocaleContextHolder.getLocale();
            String messageError = messageSource.getMessage("messages.errorPhoto", null, currentLocale);
            model.addFlashAttribute(Constants.Controller.CLASS_CSS, "alert alert-danger");
            model.addFlashAttribute(Constants.Controller.MESSAGE, messageError);

            return REDIRECT_MODERATOR_CABINET;
        } else {
            clinicService.updatePhoto(photoDTO.getMultipartFile(), moderatorService.getByEmail(principal.getName()).getClinic());
            return REDIRECT_MODERATOR_CABINET;
        }
    }

    @GetMapping(value = "/cabinet/make/doctor")
    public String makeDoctor(Model model, Principal principal) {
        model.addAttribute(Constants.Controller.USERS_TO_DOCTOR, new UserToDoctor());
        Moderator moderator = moderatorService.getByEmail(principal.getName());
        List<Doctor> doctors = doctorService.getByClinic(moderator.getClinic().getId());


        model.addAttribute(Constants.Controller.DOCTORS, doctors);
        model.addAttribute(Constants.Controller.MODERATOR, moderator);


        return MODERATOR_MAKE_DOCTOR;
    }

    @PostMapping(value = "/cabinet/make/doctor")
    public String makeDoctor(@ModelAttribute("usersToDoctor") @Valid UserToDoctor userToDoctor, BindingResult
            bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return MODERATOR_MAKE_DOCTOR;
        } else {
            doctorService.makeDoctorFromUser(userToDoctor, principal.getName());
            return REDIRECT_MODERATOR_CABINET_DOCTOR;
        }
    }
}
