package com.softserve.edu.lv251.service;

import com.softserve.edu.lv251.dto.pojos.*;
import com.softserve.edu.lv251.entity.Appointment;
import com.softserve.edu.lv251.entity.Clinic;
import com.softserve.edu.lv251.entity.Doctor;

import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 21.07.2017.
 */
public interface DoctorService {
    void addDoctor(Doctor doctor);

    List<Doctor> getAll();

    void update(Doctor doctor);

    Doctor find(long id);

    void delete(Doctor doctor);

    List<DoctorsSearchDTO> searchByLetters(String search);

    List<Doctor> getDoctorsByColumnNameAndValue(String columnName, Object value);

    Doctor findByEmail(String email);

    Doctor registerNewDoctorAccount(UserDTO accountDto);

    List<Appointment> appointmentsInThisMonth(Long id, Date date);

    List<DoctorsSearchDTO> searchByDistrict(String name);

    List<DoctorsSearchDTO> searchBySpecialization(String name);


    List<PatientDTO> getDoctorPatients(long doctorId);

    List<Doctor> getByClinic(Long clinicId);

    Doctor addDoctorAccount(DoctorDTO accountDto, String email);

    List<SearchResultDoctorDTO> getDoctorByNameWithLimitAndOffset(String name, int offset, int limit);

    DoctorsSearchDTO findById(long id);

    void makeDoctorFromUser(UserToDoctor userToDoctor, String email);

    List<DoctorsSearchDTO> getByClinic(Clinic clinic);

    List<DoctorRespondDTO> getDoctorsByUser(long userId);

    Doctor getById(long doctorId);
}
