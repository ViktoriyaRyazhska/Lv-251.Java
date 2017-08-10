package com.softserve.edu.lv251.controllers;

import com.softserve.edu.lv251.config.MailComponent;
import com.softserve.edu.lv251.dto.pojos.ContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Locale;

/**
 *
 */

@Controller
public class ContactController {

    @Autowired
    MailComponent mailComponent;

    @Autowired
    MessageSource messageSource;

    @GetMapping("/contact")
    public String contactUs(@ModelAttribute ContactDTO contactDTO){
        return "contact";
    }

    @PostMapping("/contact")
    public String processFormContact(@Valid ContactDTO contactDTO, BindingResult bindingResult, RedirectAttributes model){


        Locale currentLocale = LocaleContextHolder.getLocale();
        String messageSuccess = messageSource.getMessage("messages.contactSuccess", null, currentLocale);
        String messageError = messageSource.getMessage("messages.contactError", null, currentLocale);

        if (bindingResult.hasErrors()){

            return "contact";
        }

        if (mailComponent.sendMail(contactDTO)){
            model.addFlashAttribute("classCss", "alert alert-success");
            model.addFlashAttribute("message", messageSuccess);
        } else {
            model.addFlashAttribute("classCss", "alert alert-warning");
            model.addFlashAttribute("message", messageError);
        }
        return "redirect:/contact";
    }

}
