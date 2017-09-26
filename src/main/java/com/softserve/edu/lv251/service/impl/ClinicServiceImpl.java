package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.config.Mapper;
import com.softserve.edu.lv251.dao.BaseDAO;
import com.softserve.edu.lv251.dao.ClinicDAO;
import com.softserve.edu.lv251.dto.pojos.ClinicSearchDTO;
import com.softserve.edu.lv251.dto.pojos.ClinicsAngularDTO;
import com.softserve.edu.lv251.dto.pojos.SearchResultClinicDTO;
import com.softserve.edu.lv251.entity.Clinic;
import com.softserve.edu.lv251.service.ClinicService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Taras on 14.07.2017.
 */
@Service("clinicService")
public class ClinicServiceImpl extends PagingSizeServiceImpl<Clinic> implements ClinicService {

    @Autowired
    private Mapper mapper;

    @Autowired
    private Logger logger;

    @Autowired
    private ClinicDAO clinicDAO;

    @Override
    public List<SearchResultClinicDTO> getWithOffsetOrderedByName(String name, int offset, int limit) {
        List<Clinic> clinics;
        if (name == null || name.isEmpty()) {
            clinics = clinicDAO.getWithOffsetAndLimit(offset, limit);
        } else {
            clinics = clinicDAO.getByNameWithOffsetAndLimit(name, offset, limit);
        }
        List<SearchResultClinicDTO> results = new ArrayList<>();

        for (Clinic clinic : clinics) {
            SearchResultClinicDTO result = new SearchResultClinicDTO();
            mapper.map(clinic, result);
            results.add(result);
        }

        return results;
    }

    @Override
    public void addClinic(Clinic clinic) {
        this.clinicDAO.addEntity(clinic);
    }

    @Override
    public void updateClinic(Clinic clinic) {
        this.clinicDAO.updateEntity(clinic);
    }

    @Override
    public Clinic getClinicByID(Long clinicId) {
        return clinicDAO.getEntityByID(clinicId);
    }

    @Override
    public ClinicSearchDTO clinicSearchById(Long clinicId) {
        Clinic clinic = clinicDAO.getEntityByID(clinicId);
        ClinicSearchDTO clinicSearchDTO = new ClinicSearchDTO();
        mapper.map(clinic, clinicSearchDTO);
        return clinicSearchDTO;
    }

    @Override
    public List<Clinic> getClinicsByColumnNameAndValue(String columnName, Object value) {
        return this.clinicDAO.getEntitiesByColumnNameAndValue(columnName, value);
    }

    @Override
    public List<Clinic> getAllClinics() {
        return this.clinicDAO.getAllEntities();
    }

    @Override
    public void deleteClinic(Clinic clinic) {
        this.clinicDAO.deleteEntity(clinic);
    }

    public Clinic getFirst() {
        return clinicDAO.getEntityByID(1L);
    }

    @Override
    public BaseDAO<Clinic> getDao() {
        return clinicDAO;
    }

    public List<ClinicSearchDTO> findByDistrict(String name)

    {
        List<Clinic> clinics = clinicDAO.findByDistrict(name);
        List<ClinicSearchDTO> results = new ArrayList<>();

        for (Clinic clinic : clinics) {
            ClinicSearchDTO result = new ClinicSearchDTO();
            mapper.map(clinic, result);
            results.add(result);
        }
        return results;
    }

    @Override
    public List<ClinicSearchDTO> searchByLetters(String letters) {
        List<Clinic> clinics = clinicDAO.searchByLetters(letters);
        List<ClinicSearchDTO> results = new ArrayList<>();

        for (Clinic clinic : clinics) {
            ClinicSearchDTO result = new ClinicSearchDTO();
            mapper.map(clinic, result);
            results.add(result);
        }
        return results;
    }

    @Override
    public Clinic getByName(String name) {
        List<Clinic> clinic = clinicDAO.getEntitiesByColumnNameAndValue("clinic_name", name);
        return clinic.isEmpty() ? null : clinic.get(0);
    }

    @Override
    public void updatePhoto(MultipartFile file, Clinic clinic) {
        if (!file.isEmpty()) {
            String photo = StoredImagesService.getBase64encodedMultipartFile(file);
            clinic.setPhoto(photo);
            clinicDAO.updateEntity(clinic);
        }
    }

    @Override
    public List<ClinicsAngularDTO> getAllClinicsDto() {
        List<ClinicsAngularDTO> clinicsAngularDTOS = new LinkedList<>();
        clinicDAO.getAllEntities().forEach((clinic) -> clinicsAngularDTOS.add(mapper.map(clinic, ClinicsAngularDTO.class)));
        return clinicsAngularDTOS;
    }
}
