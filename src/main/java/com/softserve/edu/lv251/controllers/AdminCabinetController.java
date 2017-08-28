package com.softserve.edu.lv251.controllers;

import com.softserve.edu.lv251.entity.Admin;
import com.softserve.edu.lv251.entity.Moderator;
import com.softserve.edu.lv251.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import java.util.ArrayList;


/**
 * Created by Kovalevskyy Vitaliy on 15.08.2017.
 */
@Controller
public class AdminCabinetController {

    @Autowired
    AdminService adminService;

    @GetMapping("/admin/cabinet")
    public String userProfileGET(ModelMap model, Principal principal) {

        Admin admin = adminService.findByEmail(principal.getName());
        model.addAttribute("moderators", new ArrayList<Moderator>());
        return "adminCabinet";
    }
}
