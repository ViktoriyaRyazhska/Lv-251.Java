package com.softserve.edu.lv251.controllers.rest;

import com.softserve.edu.lv251.config.MailComponent;
import com.softserve.edu.lv251.dto.pojos.ContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Marian Brynetskyi on 02.09.2017.
 */
@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = {"*"})
public class ContactRestController {

    @Autowired
    private MailComponent mailComponent;

    @RequestMapping(value = "/contact-us", method = RequestMethod.POST)
    @ResponseBody
    public boolean sendMail(@RequestBody @Valid ContactDTO contactDTO) {


        mailComponent.sendMail(contactDTO);
        System.out.println("asdasd");
        return true;
    }
}
