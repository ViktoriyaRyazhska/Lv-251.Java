package com.softserve.edu.lv251.service;


import com.softserve.edu.lv251.entity.Clinics;
import java.util.List;

/**
 * Created by Taras on 14.07.2017.
 */
public interface ClinicService {

    void addClinic(Clinics clinic);

    void updateClinic(Clinics clinic);

    Clinics getClinicByID(Long clinicId);

    List<Clinics> getClinicsByColumnNameAndValue(String columnName, Object value);

    List<Clinics> getAllClinics();

    void deleteClinic(Clinics clinic);

    List<Clinics> getWithOffsetOrderedByName(int offset, int limit);

    Clinics getFirst();

    int numberOfPaging(Integer size);

    List<Clinics> getClinics(Integer chainIndex, Integer size);
}
