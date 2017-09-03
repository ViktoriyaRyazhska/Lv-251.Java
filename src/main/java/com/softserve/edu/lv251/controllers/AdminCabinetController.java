package com.softserve.edu.lv251.controllers;

import com.softserve.edu.lv251.dto.pojos.ModeratorDTO;
import com.softserve.edu.lv251.entity.Admin;
import com.softserve.edu.lv251.entity.Clinic;
import com.softserve.edu.lv251.entity.Contact;
import com.softserve.edu.lv251.entity.Moderator;
import com.softserve.edu.lv251.idl.WebRoles;
import com.softserve.edu.lv251.service.*;
import com.softserve.edu.lv251.service.impl.StoredImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Kovalevskyy Vitaliy on 15.08.2017.
 */
@Controller
public class AdminCabinetController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ModeratorService moderatorService;

    @GetMapping("/admin/cabinet")
    public String adminProfileGET(ModelMap model, Principal principal) {

        Admin admin = adminService.findByEmail(principal.getName());
        List<Moderator>  list= moderatorService.getListByAdminId(admin.getId());
        model.addAttribute("moderators", list);
        model.addAttribute("editModerator", new Moderator());
        model.addAttribute("addModerator", new ModeratorDTO());
        return "adminCabinet";
    }

    @PostMapping("/admin/cabinet/edit/{id}")
    public String editModerator(@PathVariable("id") Long id, @ModelAttribute Moderator editModerator ) {
        moderatorService.editModerator(id, editModerator);
        return "redirect:/admin/cabinet";
    }

    @GetMapping(value = "/admin/cabinet/delete/{id}")
    public String deleteModerator(@PathVariable("id") Long id) {
        moderatorService.delete(moderatorService.getById(id));
        return "redirect:/admin/cabinet";
    }

    @PostMapping(value = "/admin/cabinet/add")
    public String addModerator(@ModelAttribute ModeratorDTO addModerator, Principal principal) {
        moderatorService.addModeratorAccount(addModerator, principal.getName());
        return "redirect:/admin/cabinet";
    }
}
