package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.dao.ContactDAO;
import com.softserve.edu.lv251.entity.Contact;
import com.softserve.edu.lv251.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by : Kovalevskyy Vitaliy
 */
@Service
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    private ContactDAO contactDAO;

    @Override
    public void addContacts(Contact contact) {
        this.contactDAO.addEntity(contact);
    }

    public void updateContacts(Contact contact) {
        this.contactDAO.updateEntity(contact);
    }

}
