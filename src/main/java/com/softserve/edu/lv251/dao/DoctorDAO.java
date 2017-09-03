package com.softserve.edu.lv251.dao;

import com.softserve.edu.lv251.entity.Appointment;
import com.softserve.edu.lv251.entity.Clinic;
import com.softserve.edu.lv251.entity.Doctor;

import java.util.Date;
import java.util.List;

/**
 * Created by Taras on 16.07.2017.
 */
public interface DoctorDAO extends BaseDAO<Doctor>{
    List<Doctor> searchByLetters(String letters);
    List<Appointment> appointmentsInThisMonth(Long id, Date date);
    List<Doctor>searchByDistrict(String name);
    List<Doctor>searchBySpecialization(String name);
    List<Doctor>getWithOffsetAndLimit(int offset, int limit);
    List<Doctor>searchByNameAndSpecialisationWithOffsetAndLimit(String value, int offset, int limit);
    List<Doctor>getByClinic(Clinic clinic);
    List<Doctor>getDoctorsByUser(long id);
}
