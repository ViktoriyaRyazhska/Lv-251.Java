package com.softserve.edu.lv251.controllers.rest;

import com.softserve.edu.lv251.dto.pojos.ContactDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Marian Brynetskyi on 02.09.2017.
 */
@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = {"*"})
public class ContactRestController {

    @RequestMapping(value = "/contact-us", method = RequestMethod.POST)
    @ResponseBody
    public void sendMail(@RequestBody @Valid ContactDTO contactDTO) {

        System.out.println("oooooo");

    }
}
