package com.softserve.edu.lv251.controllers;

import com.softserve.edu.lv251.config.Base64;
import com.softserve.edu.lv251.config.Mapper;
import com.softserve.edu.lv251.dto.pojos.PasswordDTO;
import com.softserve.edu.lv251.dto.pojos.PersonalInfoDTO;
import com.softserve.edu.lv251.entity.Contact;
import com.softserve.edu.lv251.entity.Message;
import com.softserve.edu.lv251.entity.User;
import com.softserve.edu.lv251.entity.security.UpdatableUserDetails;
import com.softserve.edu.lv251.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import static com.softserve.edu.lv251.constants.Constants.Controller.*;
import static com.softserve.edu.lv251.constants.Constants.View.*;

/**
 * Author: Brynetskyi Marian
 * Updated: Kovalevskyy Vitaliy
 */
@Controller
@RequestMapping("/user")
public class UserCabinetController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserService userService;

    @Autowired
    private ContactsService contactsService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private RespondService respondService;

    @Autowired
    private Mapper mapper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private TestResultService testResultService;

    /**
     * Author: Brynetskyi Marian
     * Updated: Kovalevskyy Vitaliy
     */
    @GetMapping("/cabinet")
    public String userProfileGET(ModelMap model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        Contact contact = user.getContact();
        PersonalInfoDTO personalInfoDTO = new PersonalInfoDTO();
        PasswordDTO passwordDTO = new PasswordDTO();
        List<Message> messages= messageService.getAll();
        mapper.map(user, personalInfoDTO);

        mapper.map(contact, personalInfoDTO);
        model.addAttribute(PHOTO, user.getPhoto());
        model.addAttribute(PERSONAL_INFO_DTO, personalInfoDTO);
        model.addAttribute(PASSWORD_DTO, passwordDTO);
        model.addAttribute("messages",messages);
        return USER_CABINET;
    }

    /**
     * Author: Kovalevskyy Vitaliy
     */
    @PostMapping("/cabinet")
    public String userProfilePOST(@Valid @ModelAttribute PersonalInfoDTO personalInfoDTO, BindingResult bindingResult, Principal principal, ModelMap model) {
        User user = userService.findByEmail(principal.getName());

        if (bindingResult.hasErrors()) {
            personalInfoDTO.setPhoto(new Base64(user.getPhoto().getBytes()));
            model.addAttribute(PHOTO, user.getPhoto());
            return USER_CABINET;
        }

        Contact contact = user.getContact();
        mapper.map(personalInfoDTO, user);
        mapper.map(personalInfoDTO, contact);
        userService.updateUser(user);
        contactsService.updateContacts(contact);
        ((UpdatableUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .setUsername(personalInfoDTO.getEmail());
        return REDIRECT_USER_CABINET;
    }

    /**
     * Author: Kovalevskyy Vitaliy
     */
    @PostMapping("/changePassword")
    public String savePassword(@Valid @ModelAttribute PasswordDTO passwordDTO, BindingResult bindingPasswordDTO,
                               @ModelAttribute PersonalInfoDTO personalInfoDTO, BindingResult bindingInfoDTO,
                               Principal principal, ModelMap model) {
        User user = userService.findByEmail(principal.getName());
        Contact contact = user.getContact();

        if (bindingPasswordDTO.hasErrors()) {
            personalInfoDTO.setPhoto(new Base64(user.getPhoto().getBytes()));
            mapper.map(user, personalInfoDTO);

            mapper.map(contact, personalInfoDTO);
            model.addAttribute(PHOTO, user.getPhoto());
            model.addAttribute(PERSONAL_INFO_DTO, personalInfoDTO);

            return USER_CABINET;
        }

        userService.changePassword(user, passwordDTO);
        return REDIRECT_USER_CABINET;
    }

    /**
     * Author: Pavlo Kuchereshko
     */
    @GetMapping("/medicalcard")
    public String medicalCardGET(ModelMap model, Principal principal) {
        model.addAttribute("listAppointments", appointmentService.getAppointmentByUserEmail(principal.getName()));
        model.addAttribute("date", new Date().getTime());

        return USER_CABINET_MEDICAL_CARD;
    }

    /**
     * Author: Marian Brynetskyy
     */
    @GetMapping("/doctors")
    public String doctorsGET(ModelMap model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("listAppointments", appointmentService.getAppointmentByUserEmail(principal.getName()));
        model.addAttribute("date", new Date().getTime());
        model.addAttribute("doctors", respondService.setResponded(user.getId(), doctorService.getDoctorsByUser(user.getId())));

        return USER_CABINET_DOCTORS;
    }

    /**
     * Created by Marian Brynetskyi
     */
    @RequestMapping(value = "/addRespond", method = RequestMethod.POST)
    public String addAppointment(ModelMap modelMap,
                                @RequestParam(DOCTOR_ID) long doctorId,
                                @RequestParam("description") String description,
                                @RequestParam("raiting") String raiting,
                                Principal principal) {

        respondService.addRespond(Short.parseShort(raiting), description, userService.findByEmail(principal.getName()).getId(), doctorId);
        return doctorsGET(modelMap, principal);
    }

    /**
     * Created by Marian Brynetskyi
     */
    @RequestMapping(value = "/editRespond", method = RequestMethod.POST)
    public String editAppointment(ModelMap modelMap,
                                 @RequestParam(DOCTOR_ID) long doctorId,
                                 @RequestParam("description") String description,
                                 @RequestParam("raiting") String raiting,
                                 Principal principal) {

        respondService.editRespond(Short.parseShort(raiting),
                description,
                userService.findByEmail(principal.getName()).getId(), doctorId);
        return doctorsGET(modelMap, principal);
    }


    /**
     * Created by Marian Brynetskyi
     */
    @RequestMapping(value = "/tests", method = RequestMethod.GET)
    public String testsGET(ModelMap model, Principal principal) {
        model.addAttribute("tests", testResultService.getUserTestResults(principal.getName()));
        model.addAttribute("date", new Date());

        return USER_CABINET_TESTS;
    }


}
