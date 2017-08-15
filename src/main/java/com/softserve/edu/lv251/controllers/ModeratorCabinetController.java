package com.softserve.edu.lv251.controllers;


import com.softserve.edu.lv251.config.Mapper;
import com.softserve.edu.lv251.constants.Constants;
import com.softserve.edu.lv251.dto.pojos.ClinicInfoDTO;
import com.softserve.edu.lv251.dto.pojos.DoctorDTO;

import com.softserve.edu.lv251.dto.pojos.UserToDoctor;
import com.softserve.edu.lv251.entity.*;

import com.softserve.edu.lv251.model.FileBucket;
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

/**
 * Created by Yana Martynyak on 31.07.2017.
 */
@Controller
@RequestMapping(value = "/moderator")
public class ModeratorCabinetController {
    @Autowired
    private ModeratorService moderatorService;

    @Autowired
    private DoctorsService doctorsService;

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


    @GetMapping(value = "/cabinet")
    public String moderatorCabinet(Principal principal, Model model) {
        Moderator moderator = moderatorService.getByEmail(principal.getName());
        List<Doctors> doctors = doctorsService.getByClinic(moderator.getClinics().getId());
        Clinics clinics = moderator.getClinics();
        System.out.println(doctors);
        Contacts contacts = clinics.getContact();
        ClinicInfoDTO clinicDTO = new ClinicInfoDTO();
     if(clinics!=null){
        mapper.map(clinics, clinicDTO);}
     if(contacts!=null){
        mapper.map(contacts, clinicDTO);}
        model.addAttribute(Constants.ControllersConstants.PHOTO_FORM, new FileBucket());
        model.addAttribute(Constants.ControllersConstants.DOCTORS, doctors);
        model.addAttribute(Constants.ControllersConstants.MODERATOR, moderator);
        model.addAttribute(Constants.ControllersConstants.CLINIC_DTO, clinicDTO);
        return Constants.ControllersReturn.MODERATOR_CABINET;


     }

    @PostMapping("/cabinet")
    public String edit(@ModelAttribute("clinicDTO") @Valid ClinicInfoDTO clinicInfoDTO, BindingResult bindingResult, Principal principal, RedirectAttributes model) {
        Locale currentLocale = LocaleContextHolder.getLocale();

        String messageError = messageSource.getMessage("messages.errorClinicName", null, currentLocale);
        Moderator moderator = moderatorService.getByEmail(principal.getName());
        Clinics clinics = moderator.getClinics();
        Contacts contacts = clinics.getContact();
        if (!bindingResult.hasErrors()) {
            mapper.map(clinicInfoDTO, clinics);
            mapper.map(clinicInfoDTO, contacts);

            clinicService.updateClinic(clinics);
            contactsService.updateContacts(contacts);
            return "redirect:"+Constants.ControllersReturn.MODERATOR_CABINET_URL;
        } else {
            model.addFlashAttribute(Constants.ControllersConstants.CLASS_CSS, "alert alert-warning");
            model.addFlashAttribute(Constants.ControllersConstants.MODERATOR, messageError);
            return "redirect:"+Constants.ControllersReturn.MODERATOR_CABINET_URL;
        }
    }

    @GetMapping(value = "/cabinet/doctors")
    public String moderatorAllDoctors(Principal principal, Model model) {
        Moderator moderator = moderatorService.getByEmail(principal.getName());
        List<Doctors> doctors = doctorsService.getByClinic(moderator.getClinics().getId());


        model.addAttribute(Constants.ControllersConstants.DOCTORS, doctors);
        model.addAttribute(Constants.ControllersConstants.MODERATOR, moderator);
        return Constants.ControllersReturn.MODERATOR_CABINET_DOCTORS;
    }

    @GetMapping(value = "/cabinet/doctors/delete/{id}")
    public String deleteDoctor(@PathVariable("id") Long id) {
        doctorsService.delete(doctorsService.find(id));
        return "redirect"+Constants.ControllersReturn.MODERATOR_CABINET_DOCTORS_URL;

}

    @GetMapping(value = "/cabinet/add/doctor")
    public String addDoctor(Model model, Principal principal) {
        model.addAttribute("doctorForm", new DoctorDTO());
        Moderator moderator = moderatorService.getByEmail(principal.getName());
        List<Doctors> doctors = doctorsService.getByClinic(moderator.getClinics().getId());
        model.addAttribute(Constants.ControllersConstants.DOCTORS, doctors);
        model.addAttribute(Constants.ControllersConstants.MODERATOR, moderator);
        return Constants.ControllersReturn.MODERATOR_ADD_DOCTOR;
    }

    @PostMapping(value = "/add/doctor")
    public String registerDoctor(@ModelAttribute("doctorForm") @Valid DoctorDTO doctorDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.warn("registerDoctor has errors");
            return Constants.ControllersReturn.MODERATOR_ADD_DOCTOR;
        } else {
            doctorsService.addDoctorAccount(doctorDTO);
            return "redirect:"+Constants.ControllersReturn.MODERATOR_CABINET_DOCTORS_URL;
        }
    }


    @PostMapping(value = "/upload/clinicPhoto")
    public String uploadPhoto(@ModelAttribute("photoForm")@Valid FileBucket fileBucket, BindingResult bindingResult, Principal principal, RedirectAttributes model) {
        if (bindingResult.hasErrors()) {
            Locale currentLocale = LocaleContextHolder.getLocale();
            String messageError = messageSource.getMessage("messages.errorPhoto", null, currentLocale);
            model.addFlashAttribute(Constants.ControllersConstants.CLASS_CSS, "alert alert-danger");
            model.addFlashAttribute(Constants.ControllersConstants.MESSAGE, messageError);
            return "redirect:"+Constants.ControllersReturn.MODERATOR_CABINET_URL;
        } else {
            clinicService.updatePhoto(fileBucket.getMultipartFile(), moderatorService.getByEmail(principal.getName()).getClinics());

            return "redirect:"+Constants.ControllersReturn.MODERATOR_CABINET_URL;
        }
    }

    @GetMapping(value = "/cabinet/make/doctor")
    public String makeDoctor(Model model, Principal principal) {
        model.addAttribute(Constants.ControllersConstants.USERS_TO_DOCTOR, new UserToDoctor());

        Moderator moderator = moderatorService.getByEmail(principal.getName());
        List<Doctors> doctors = doctorsService.getByClinic(moderator.getClinics().getId());
        List<Users> users = userService.getAllUsers();

        model.addAttribute(Constants.ControllersConstants.DOCTORS, doctors);
        model.addAttribute(Constants.ControllersConstants.MODERATOR, moderator);

            return Constants.ControllersReturn.MODERATOR_MAKE_DOCTOR;
        }

        @PostMapping(value = "/cabinet/make/doctor")
        public String makeDoctor (@ModelAttribute("usersToDoctor") @Valid UserToDoctor userToDoctor, BindingResult
        bindingResult, Principal principal){
            if (bindingResult.hasErrors()) {
                return Constants.ControllersReturn.MODERATOR_MAKE_DOCTOR;
            } else {
                doctorsService.makeDoctorFromUser(userToDoctor, principal.getName());
                return "redirect:"+Constants.ControllersReturn.MODERATOR_CABINET_DOCTORS_URL;
            }
        }
}
