package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.dao.DoctorsDAO;
import com.softserve.edu.lv251.entity.Doctors;
import com.softserve.edu.lv251.service.DoctorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Admin on 21.07.2017.
 */
@Service
public class DoctorServiceImpl implements DoctorsService {
    @Autowired
 private DoctorsDAO doctorsDAO;
    @Override
    public void add(Doctors doctors) {
        doctorsDAO.addEntity(doctors);

    }

    @Override
    public List<Doctors> getAll() {
        return doctorsDAO.getAllEntities();
    }

    @Override
    public void update(Doctors doctors) {
        doctorsDAO.updateEntity(doctors);

    }

    @Override
    public Doctors find(long id) {
        return doctorsDAO.getEntityByID(id);
    }

    @Override
    public void delete(Doctors doctors) {
     doctorsDAO.deleteEntity(doctors);
    }

    @Override
    public List<Doctors> searchByLetters(String letters) {
        return doctorsDAO.searchByLetters(letters);
    }
}
