package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.dao.ModeratorDAO;
import com.softserve.edu.lv251.dto.pojos.ModeratorDTO;
import com.softserve.edu.lv251.entity.Clinic;
import com.softserve.edu.lv251.entity.Contact;
import com.softserve.edu.lv251.entity.Moderator;
import com.softserve.edu.lv251.idl.WebRoles;
import com.softserve.edu.lv251.service.*;
import org.hibernate.annotations.OnDelete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Yana Martynyak on 31.07.2017.
 */
@Service
public class ModeratorServiceImpl implements ModeratorService {

    @Autowired
    private ModeratorDAO moderatorDAO;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private ContactsService contactsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private AdminService adminService;


    @Override
    public Moderator getByEmail(String email) {
        List<Moderator> moderators = moderatorDAO.getEntitiesByColumnNameAndValue("email", email);
        return moderators.isEmpty() ? null : moderators.get(0);
    }

    @Override
    public List<Moderator> getListByAdminId(Long id) {
        List<Moderator> moderators = moderatorDAO.getEntitiesByColumnNameAndValue("admin", id);
        return moderators;
    }

    @Override
    public Moderator getById(Long id) {
        List<Moderator> moderators = moderatorDAO.getEntitiesByColumnNameAndValue("id", id);
        return moderators.isEmpty() ? null : moderators.get(0);
    }

    @Override
    public void updateModerator(Moderator moderator) {
        this.moderatorDAO.updateEntity(moderator);
    }

    @Override
    public void delete(Moderator moderator) {
        moderatorDAO.deleteEntity(moderator);
    }

    @Override
    public void add(Moderator moderator) {
        moderatorDAO.addEntity(moderator);
    }

    @Transactional
    @Override
    public void addModeratorAccount(ModeratorDTO addModerator, String adminEmail) {

        Clinic clinic = new Clinic();
        Moderator moderator = new Moderator();
        Contact contactClinic = new Contact();
        Contact contactModerator = new Contact();

        contactClinic.setEmail(addModerator.getClinicEmail());

        clinic.setClinic_name(addModerator.getClinicName());
        clinic.setPhoto(StoredImagesService.getDefaultPictureBase64encoded("User_Default.png"));
        clinic.setContact(contactClinic);

        contactModerator.setEmail(addModerator.getModeratorEmail());
//        contactsService.addContacts(contactModerator);

        moderator.setFirstname(addModerator.getFirstName());
        moderator.setLastname(addModerator.getLastName());
        moderator.setEmail(addModerator.getModeratorEmail());
        moderator.setAdmin( adminService.findByEmail(adminEmail));
        moderator.setPhoto(StoredImagesService.getDefaultPictureBase64encoded("User_Default.png"));
        moderator.setPassword(bCryptPasswordEncoder.encode(addModerator.getPassword()));
        moderator.setRoles(Arrays.asList(
                rolesService.findByName(WebRoles.ROLE_MODERATOR.name())));
        moderator.setClinic(clinic);
        moderator.setContact(contactModerator);
        add(moderator);
    }

    @Override
    public void editModerator(Long id, Moderator editModerator) {
        Moderator moderator = getById(id);
        Contact contact = moderator.getContact();
        moderator.setFirstname(editModerator.getFirstname());
        moderator.setLastname(editModerator.getLastname());
        moderator.setEmail(editModerator.getEmail());
        contact.setEmail(editModerator.getEmail());
        updateModerator(moderator);
    }
}
