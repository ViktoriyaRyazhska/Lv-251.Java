package com.softserve.edu.lv251.service;


import com.softserve.edu.lv251.dto.pojos.ClinicSearchDTO;
import com.softserve.edu.lv251.dto.pojos.ClinicsAngularDTO;
import com.softserve.edu.lv251.dto.pojos.SearchResultClinicDTO;
import com.softserve.edu.lv251.entity.Clinic;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Taras on 14.07.2017.
 */
public interface ClinicService {

    void addClinic(Clinic clinic);

    void updateClinic(Clinic clinic);

    Clinic getClinicByID(Long clinicId);

    ClinicSearchDTO clinicSearchById(Long clinicId);

    List<Clinic> getClinicsByColumnNameAndValue(String columnName, Object value);

    List<Clinic> getAllClinics();

    void deleteClinic(Clinic clinic);

    List<SearchResultClinicDTO> getWithOffsetOrderedByName(String name, int offset, int limit);

    Clinic getFirst();

    List<ClinicSearchDTO> findByDistrict(String name);

    List<ClinicSearchDTO> searchByLetters(String letters);

    Clinic getByName(String name);

    void updatePhoto(MultipartFile file, Clinic clinic);

    List<ClinicsAngularDTO> getAllClinicsDto();

}
