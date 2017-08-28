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
public interface DoctorsService {
    void  addDoctor(Doctor doctor);
    List<Doctor> getAll();
    void update(Doctor doctor);
    Doctor find(long id);
    void delete(Doctor doctor);
    List<DoctorsSearchDTO> searchByLetters(String search);

    List<Doctor> getDoctorsByColumnNameAndValue(String columnName, Object value);
    public Doctor findByEmail(String email);
    public Doctor registerNewDoctorAccount(UserDTO accountDto);
    List<Appointment> appointmentsInThisMonth(Long id, Date date);
    List<DoctorsSearchDTO>searchByDistrict(String name);
    List<DoctorsSearchDTO>searchBySpecialization(String name);


    List<PatientDTO> getDoctorPatients(long doctorId);

    public List<Doctor> getByClinic(Long clinicId);
    public Doctor addDoctorAccount(DoctorDTO accountDto,String email);

    public List<SearchResultDoctorDTO> getDoctorByNameWithLimitAndOffset(String name, int offset, int limit);
    public  DoctorsSearchDTO findById(long id);
    public void makeDoctorFromUser(UserToDoctor userToDoctor,String email);
    public List<DoctorsSearchDTO>getByClinic(Clinic clinic);

}
