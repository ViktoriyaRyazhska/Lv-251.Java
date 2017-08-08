package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.dao.AppointmentsDAO;
import com.softserve.edu.lv251.entity.Appointments;
import com.softserve.edu.lv251.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by kilopo on 31.07.2017.
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentsDAO appointmentsDAO;

    @Override
    public void addAppointment(Appointments appointments) {
        appointmentsDAO.addEntity(appointments);
    }

    @Override
    public void updateAppointment(Appointments appointments) {
        appointmentsDAO.updateEntity(appointments);
    }

    @Override
    public Appointments getAppointmentById(Long id) {
        return appointmentsDAO.getEntityByID(id);
    }

    @Override
    public List<Appointments> listAppointmensWithDoctor(Long id) {
        Date date = new Date();
        List<Appointments> list = appointmentsDAO.appointmentsWithDoctor(id);
        for (Appointments appointments : list) {
            if (appointments.getAppointmentDate().before(date) && !appointments.getIsApproved()){
                appointments.setStatus(false);
                appointmentsDAO.updateEntity(appointments);
            }
        }
        return list;
    }

    public List<Appointments> getAppiontmentbyDoctorsEmail(String email) {
        return appointmentsDAO.getAppiontmentbyDoctorsEmail(email);
    }
}
