package com.softserve.edu.lv251.service;

import com.softserve.edu.lv251.entity.Appointments;

import java.util.List;

/**
 * Created by kilopo on 31.07.2017.
 */
public interface AppointmentService {

    void addAppointment(Appointments appointments);

    void updateAppointment(Appointments appointments);

    Appointments getAppointmentById(long id);


    List<Appointments> listAppointmensWithDoctor();


    List<Appointments> getAppiontmentbyDoctorsEmail(String email);

}
