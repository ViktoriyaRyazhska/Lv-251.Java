package com.softserve.edu.lv251.controllers;

import com.softserve.edu.lv251.constants.Constants;
import com.softserve.edu.lv251.dto.pojos.DoctorDTO;
import com.softserve.edu.lv251.dto.pojos.UserDTO;
import com.softserve.edu.lv251.entity.Doctor;
import com.softserve.edu.lv251.entity.User;
import com.softserve.edu.lv251.entity.VerificationToken;
import com.softserve.edu.lv251.events.OnRegistrationCompleteEvent;
import com.softserve.edu.lv251.service.DoctorsService;
import com.softserve.edu.lv251.service.UserService;
import com.softserve.edu.lv251.service.VerificationTokenService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

/**
 * Added by Pavlo Kuchereshko.
 * Updated: Brynetskyi Marian
 */

@org.springframework.stereotype.Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorsService doctorsService;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private Logger logger;

    @Autowired
    private MessageSource messageSource;

    /**
     * The publisher constructs the event object and publishes it to anyone who’s listening.
     */
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new UserDTO());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUserAccount(
            @ModelAttribute("userForm") @Valid UserDTO accountDto,
            BindingResult result,
            WebRequest request,
            Errors errors) {

        if (result.hasErrors()) {
            logger.warn(result.getAllErrors());
            return "registration";
        }

        User registered = userService.registerNewUserAccount(accountDto);

        if (registered == null) {

            result.rejectValue(Constants.Controller.EMAIL, "message.regError");

        }

        try {
            String appUrl = request.getContextPath();
            applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, LocaleContextHolder.getLocale(), appUrl));
        } catch (Exception e) {
            logger.error(e);
            return "registration";
        }

        return "redirect:/afterRegistration";
    }

    /**
     * Author: Pavlo Kuchereshko
     */
    @GetMapping("/afterRegistration")
    public String afterRegistrationGET() {
        return "afterRegistration";
    }

    @RequestMapping(value = "/registrationDoctor", method = RequestMethod.GET)
    public String registrationDoctor(Model model) {

        model.addAttribute(Constants.Controller.DOCTOR_FORM, new DoctorDTO());


        return "registrationDoctor";
    }

    @RequestMapping(value = "/registrationDoctor", method = RequestMethod.POST)
    public String registerDoctorAccount(

            @ModelAttribute(Constants.Controller.DOCTOR_FORM) @Valid DoctorDTO accountDto,

            BindingResult result) {

        Doctor registered = new Doctor();
        if (!result.hasErrors()) {
            registered = doctorsService.registerNewDoctorAccount(accountDto);
        }
        if (registered == null) {

            result.rejectValue(Constants.Controller.EMAIL, "message.regError");

        }
        if (result.hasErrors()) {
            return "registrationDoctor";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public String finishRegistration(
            @RequestParam("token") String token,
            Model model,
            WebRequest request) {

        Locale locale = LocaleContextHolder.getLocale();

        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            String message = messageSource.getMessage("messages.invalidToken", null, locale);

            model.addAttribute(Constants.Controller.MESSAGE, message);

            return "redirect:/403?lang=" + locale.getLanguage();
        }

        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime()) <= 0) {
            String message = messageSource.getMessage("messages.invalidToken", null, locale);

            model.addAttribute(Constants.Controller.MESSAGE, message);

            return "redirect:/403?lang=" + locale.getLanguage();
        }

        user.setEnabled(true);
        this.userService.updateUser(user);
        this.verificationTokenService.deleteVerificationToken(verificationToken);

        return "successRegistration";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {

            model.addAttribute(Constants.Controller.LOGIN_FLAG, true);


            return "home";
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }

        model.addAttribute("login", true);

        return "home";
    }
}
