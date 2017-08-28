package com.softserve.edu.lv251.controllers.rest;

import com.softserve.edu.lv251.entity.User;
import com.softserve.edu.lv251.service.DoctorsService;
import com.softserve.edu.lv251.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by ace on 08/25/2017.
 */
@RestController
public class HiRestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/api/hi")
    public String hi(Principal principal) {
        String firsName = "unlogined";
        String lastName = "user";
        if (principal != null) {
            User user = userService.findByEmail(principal.getName());
            firsName = user.getFirstname();
            lastName = user.getLastname();
        }
        return "Hello World from Restful API for " + firsName + " " + lastName;
    }
}
