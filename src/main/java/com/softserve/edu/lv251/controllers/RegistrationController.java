package com.softserve.edu.lv251.controllers;

import com.softserve.edu.lv251.dto.pojos.DoctorDTO;
import com.softserve.edu.lv251.dto.pojos.UserDTO;
import com.softserve.edu.lv251.entity.Doctor;
import com.softserve.edu.lv251.entity.User;
import com.softserve.edu.lv251.entity.VerificationToken;
import com.softserve.edu.lv251.events.OnRegistrationCompleteEvent;
import com.softserve.edu.lv251.service.DoctorService;
import com.softserve.edu.lv251.service.UserService;
import com.softserve.edu.lv251.service.VerificationTokenService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

import static com.softserve.edu.lv251.constants.Constants.Controller.*;
import static com.softserve.edu.lv251.constants.Constants.View.*;

/**
 * Added by Pavlo Kuchereshko.
 * Updated: Brynetskyi Marian
 */

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private Logger logger;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new UserDTO());

        return REGISTRATION;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUserAccount(
            @ModelAttribute("userForm") @Valid UserDTO accountDto,
            BindingResult result,
            WebRequest request,
            RedirectAttributes model) {

        if (result.hasErrors()) {
            logger.warn(result.getAllErrors());
            return REGISTRATION;
        }

        User registered = userService.registerNewUserAccount(accountDto);

        if (registered == null) {
            result.rejectValue(EMAIL, "messages.regError");
            return REGISTRATION;
        }

        try {
            String appUrl = request.getContextPath();
            applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, LocaleContextHolder.getLocale(), appUrl));
        } catch (Exception e) {
            logger.error(e);
            Locale currentLocale = LocaleContextHolder.getLocale();
            String emailSendingError = messageSource.getMessage("messages.emailSendingError", null, currentLocale);
            model.addFlashAttribute(CLASS_CSS, "alert alert-warning");
            model.addFlashAttribute(MESSAGE, emailSendingError);
            userService.deleteUser(userService.getUserByID(registered.getId()));
            return REDIRECT + REGISTRATION;
        }

        return REDIRECT + AFTER_REGISTRATION;
    }

    /**
     * Author: Pavlo Kuchereshko
     */
    @GetMapping("/afterRegistration")
    public String afterRegistrationGET() {
        return AFTER_REGISTRATION;
    }

    @RequestMapping(value = "/registrationDoctor", method = RequestMethod.GET)
    public String registrationDoctor(Model model) {
        model.addAttribute(DOCTOR_FORM, new DoctorDTO());
        return REGISTRATION_DOCTOR;
    }

    @RequestMapping(value = "/registrationDoctor", method = RequestMethod.POST)
    public String registerDoctorAccount(
            @ModelAttribute(DOCTOR_FORM) @Valid DoctorDTO accountDto,
            BindingResult result) {

        Doctor registered = new Doctor();
        if (!result.hasErrors()) {
            registered = doctorService.registerNewDoctorAccount(accountDto);
        }
        if (registered == null) {
            result.rejectValue(EMAIL, "messages.regError");
        }
        if (result.hasErrors()) {
            return REGISTRATION_DOCTOR;
        } else {
            return REDIRECT;
        }
    }

    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public String finishRegistration(
            @RequestParam("token") String token,
            Model model) {

        Locale locale = LocaleContextHolder.getLocale();

        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            String message = messageSource.getMessage("messages.invalidToken", null, locale);
            model.addAttribute(MESSAGE, message);

            return REDIRECT + ERROR_403;
        }

        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime()) <= 0) {
            String message = messageSource.getMessage("messages.invalidToken", null, locale);
            model.addAttribute(MESSAGE, message);

            return REDIRECT + ERROR_403;
        }

        user.setEnabled(true);
        this.userService.updateUser(user);
        this.verificationTokenService.deleteVerificationToken(verificationToken);

        return SUCCESS_REGISTRATION;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute(LOGIN_FLAG, true);
            return HOME;
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }

        model.addAttribute("login", true);

        return HOME;
    }
}
