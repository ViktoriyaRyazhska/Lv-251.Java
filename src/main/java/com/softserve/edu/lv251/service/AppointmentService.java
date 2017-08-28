package com.softserve.edu.lv251.service;

import com.softserve.edu.lv251.dto.pojos.AppointmentsForCreationDTO;
import com.softserve.edu.lv251.dto.pojos.AppointmentDTO;
import com.softserve.edu.lv251.dto.pojos.AppointmentsForDateTimePickerInDocDTO;
import com.softserve.edu.lv251.entity.Appointment;

import java.util.Date;
import java.util.List;

/**
 * Created by kilopo on 31.07.2017.
 */
public interface AppointmentService {

    void addAppointment(Appointment appointment);

    void updateAppointment(Appointment appointment);

    Appointment getAppointmentById(Long id);

    List<AppointmentsForCreationDTO> getAllDoctorAppointmentsAfterNow(long doctorId);

    List<AppointmentsForCreationDTO> getAllDoctorsAppointmentsAfterNow();

    List<AppointmentsForDateTimePickerInDocDTO> getAllDoctorsAppointmentsAfterNow(String email, Date date);

    List<Appointment> listAppointmensWithDoctor(Long id);

    List<Appointment> getAppiontmentbyDoctorsEmail(String email);

    List<AppointmentDTO> getAppointmentDtoByUserEmail(String email);

    List<Appointment> getAppointmentByUserEmail(String email);

    boolean createAppointment (String date, String userEmail, long doctorId);
}
