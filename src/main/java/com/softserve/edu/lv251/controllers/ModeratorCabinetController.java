package com.softserve.edu.lv251.controllers;


import com.softserve.edu.lv251.config.Mapper;
import com.softserve.edu.lv251.constants.Constants;
import com.softserve.edu.lv251.dto.pojos.ClinicInfoDTO;
import com.softserve.edu.lv251.dto.pojos.DoctorDTO;
import com.softserve.edu.lv251.dto.pojos.UserToDoctor;
import com.softserve.edu.lv251.entity.Clinic;
import com.softserve.edu.lv251.entity.Contact;
import com.softserve.edu.lv251.entity.Doctor;
import com.softserve.edu.lv251.entity.Moderator;
import com.softserve.edu.lv251.model.FileBucket;
import com.softserve.edu.lv251.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
@org.springframework.stereotype.Controller
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
        Clinic clinic = moderator.getClinic();
        System.out.println(clinic);
        Contact contact = clinic.getContact();
        System.out.println(contact);
        ClinicInfoDTO clinicDTO = new ClinicInfoDTO();
        if (clinic != null) {
            System.out.println("before");
            List<Doctor> doctors = doctorsService.getByClinic(clinic.getId());
            System.out.println(doctors.size());
            model.addAttribute(Constants.Controller.DOCTORS, doctors);
            mapper.map(clinic, clinicDTO);
        }

        if (contact != null) {
            mapper.map(contact, clinicDTO);
        }
        model.addAttribute("photoForm", new FileBucket());
        model.addAttribute(Constants.Controller.MODERATOR, moderator);
        model.addAttribute("clinicDTO", clinicDTO);
        return Constants.Controller.MODERATOR_CABINET;


    }

    @PostMapping("/cabinet")
    public String edit(@ModelAttribute("clinicDTO") @Valid ClinicInfoDTO clinicInfoDTO, BindingResult bindingResult,
                       Principal principal, RedirectAttributes model) {
        Locale currentLocale = LocaleContextHolder.getLocale();

        String messageError = messageSource.getMessage("messages.errorClinicName", null, currentLocale);
        Moderator moderator = moderatorService.getByEmail(principal.getName());

        Clinic clinic = moderator.getClinic();
        System.out.println(clinic);
        Contact contact = clinic.getContact();
        System.out.println(contact);

        if (!bindingResult.hasErrors()) {
            mapper.map(clinicInfoDTO, clinic);
            mapper.map(clinicInfoDTO, contact);

            clinicService.updateClinic(clinic);
            contactsService.updateContacts(contact);
            return "redirect:/moderator/cabinet";
        } else {

            model.addFlashAttribute(Constants.Controller.CLASS_CSS, "alert alert-warning");
            model.addFlashAttribute(Constants.Controller.MESSAGE, messageError);

            return "redirect:/moderator/cabinet";
        }
    }

    @GetMapping(value = "/cabinet/doctors")
    public String moderatorAllDoctors(Principal principal, Model model) {
        Moderator moderator = moderatorService.getByEmail(principal.getName());

        List<Doctor> doctors = doctorsService.getByClinic(moderator.getClinic().getId());

        model.addAttribute(Constants.Controller.DOCTORS, doctors);
        model.addAttribute(Constants.Controller.MODERATOR, moderator);
        return Constants.Controller.MODERATOR_CABINET_DOCTORS;

    }

    @GetMapping(value = "/cabinet/doctors/delete/{id}")
    public String deleteDoctor(@PathVariable("id") Long id) {
        doctorsService.delete(doctorsService.find(id));
        return "redirect:/moderator/cabinet/doctors";

    }

    @GetMapping(value = "/cabinet/add/doctor")
    public String addDoctor(Model model, Principal principal) {
        model.addAttribute("doctorForm", new DoctorDTO());
        Moderator moderator = moderatorService.getByEmail(principal.getName());

        List<Doctor> doctors = doctorsService.getByClinic(moderator.getClinic().getId());
        model.addAttribute(Constants.Controller.DOCTORS, doctors);
        model.addAttribute(Constants.Controller.MODERATOR, moderator);
        return Constants.Controller.MODERATOR_ADD_DOCTOR;

    }

    @PostMapping(value = "/add/doctor")
    public String registerDoctor(@ModelAttribute("doctorForm") @Valid DoctorDTO doctorDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.warn("registerDoctor has errors");
            return Constants.Controller.MODERATOR_ADD_DOCTOR;
        } else {
            doctorsService.addDoctorAccount(doctorDTO);
            return "redirect:/moderator/cabinet/doctors";
        }
    }


    @PostMapping(value = "/upload/clinicPhoto")
    public String uploadPhoto(@ModelAttribute("photoForm") @Valid FileBucket fileBucket, BindingResult bindingResult,
                              Principal principal, RedirectAttributes model) {
        if (bindingResult.hasErrors()) {
            Locale currentLocale = LocaleContextHolder.getLocale();
            String messageError = messageSource.getMessage("messages.errorPhoto", null, currentLocale);

            model.addFlashAttribute(Constants.Controller.CLASS_CSS, "alert alert-danger");
            model.addFlashAttribute(Constants.Controller.MESSAGE, messageError);

            return "redirect:/moderator/cabinet";
        } else {
            clinicService.updatePhoto(fileBucket.getMultipartFile(), moderatorService.getByEmail(principal.getName()).getClinic());

            System.out.println(fileBucket.getMultipartFile().getSize());
            return "redirect:/moderator/cabinet";
        }
    }

    @GetMapping(value = "/cabinet/make/doctor")
    public String makeDoctor(Model model, Principal principal) {

        model.addAttribute(Constants.Controller.USERS_TO_DOCTOR, new UserToDoctor());

        Moderator moderator = moderatorService.getByEmail(principal.getName());
        List<Doctor> doctors = doctorsService.getByClinic(moderator.getClinic().getId());


        model.addAttribute(Constants.Controller.DOCTORS, doctors);
        model.addAttribute(Constants.Controller.MODERATOR, moderator);


        return Constants.Controller.MODERATOR_MAKE_DOCTOR;
    }

    @PostMapping(value = "/cabinet/make/doctor")
    public String makeDoctor(@ModelAttribute("usersToDoctor") @Valid UserToDoctor userToDoctor, BindingResult
            bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return Constants.Controller.MODERATOR_MAKE_DOCTOR;
        } else {
            doctorsService.makeDoctorFromUser(userToDoctor, principal.getName());
            return "redirect:/moderator/cabinet/doctors";
        }
    }
}
