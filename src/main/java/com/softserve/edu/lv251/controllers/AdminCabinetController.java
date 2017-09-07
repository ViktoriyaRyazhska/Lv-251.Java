package com.softserve.edu.lv251.controllers;

import com.softserve.edu.lv251.dto.pojos.ModeratorDTO;
import com.softserve.edu.lv251.entity.Admin;
import com.softserve.edu.lv251.entity.Moderator;
import com.softserve.edu.lv251.service.AdminService;
import com.softserve.edu.lv251.service.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

import static com.softserve.edu.lv251.constants.Constants.View.ADMIN_CABINET;
import static com.softserve.edu.lv251.constants.Constants.View.REDIRECT_ADMIN_CABINET;


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
        return ADMIN_CABINET;
    }

    @PostMapping("/admin/cabinet/edit/{id}")
    public String editModerator(@PathVariable("id") Long id, @ModelAttribute Moderator editModerator ) {
        moderatorService.editModerator(id, editModerator);
        return REDIRECT_ADMIN_CABINET;
    }

    @GetMapping(value = "/admin/cabinet/delete/{id}")
    public String deleteModerator(@PathVariable("id") Long id) {
        moderatorService.delete(moderatorService.getById(id));
        return REDIRECT_ADMIN_CABINET;
    }

    @PostMapping(value = "/admin/cabinet/add")
    public String addModerator(@ModelAttribute ModeratorDTO addModerator, Principal principal) {
        moderatorService.addModeratorAccount(addModerator, principal.getName());
        return REDIRECT_ADMIN_CABINET;
    }
}
