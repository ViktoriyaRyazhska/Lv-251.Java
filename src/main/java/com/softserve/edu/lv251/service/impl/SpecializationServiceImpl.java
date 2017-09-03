package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.dao.SpecializationDAO;
import com.softserve.edu.lv251.dto.pojos.SpecializationDTO;
import com.softserve.edu.lv251.entity.Specialization;
import com.softserve.edu.lv251.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Admin on 29.07.2017.
 */
@Service
public class SpecializationServiceImpl implements SpecializationService {
    @Autowired
    private SpecializationDAO specializationDAO;

    @Override
    public List<SpecializationDTO> searchByLetters(String letters) {
        List<Specialization> specializations= specializationDAO.searchByLetters(letters);
        List<SpecializationDTO> results=new LinkedList<>();
        for (Specialization specialization:specializations) {
           SpecializationDTO  result = new SpecializationDTO();
           result.setName(specialization.getName());
            results.add(result);
        }
        return results;
    }

    @Override
    public Specialization findByName(String name) {
        System.out.println(name);
        if (specializationDAO.getEntitiesByColumnNameAndValue("name", name).size() > 0) {
            return specializationDAO.getEntitiesByColumnNameAndValue("name", name).get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Specialization> findAll() {
        return specializationDAO.getAllEntities();
    }

    public void add(Specialization specialization) {
        specializationDAO.addEntity(specialization);
    }
}
