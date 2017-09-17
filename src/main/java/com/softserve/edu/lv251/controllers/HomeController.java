package com.softserve.edu.lv251.controllers;

import com.softserve.edu.lv251.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

import static com.softserve.edu.lv251.constants.Constants.View.HOME;

/**
 * Created by Admin on 23.07.2017.
 */
@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Principal principal, HttpServletRequest request) {
        if (principal != null) {
            request.getSession().setAttribute("username", userService.findByEmail(principal.getName()).getFirstname()
                    + " " + userService.findByEmail(principal.getName()).getLastname());
        }
        return HOME;
    }



}
