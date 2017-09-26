package com.softserve.edu.lv251.dao;

import com.softserve.edu.lv251.entity.Appointment;

import java.util.Date;
import java.util.List;

/**
 * Created by Taras on 16.07.2017.
 */
public interface AppointmentDAO extends BaseDAO<Appointment>{
    List<Appointment> getAppointmentByDoctorsEmailAfterSomeDate(String email, Date date);

    List<Appointment> appointmentsWithDoctor (Long id);

    List<Appointment> getAppiontmentbyDoctorsEmail(String email);

    List<Appointment> getAppointmentByUserEmail(String email);
}
