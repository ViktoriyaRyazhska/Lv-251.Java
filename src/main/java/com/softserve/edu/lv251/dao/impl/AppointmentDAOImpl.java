package com.softserve.edu.lv251.dao.impl;

import com.softserve.edu.lv251.dao.AppointmentsDAO;
import com.softserve.edu.lv251.entity.Appointment;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by Brynetskyi Marian on 13.07.2017.
 */
@Transactional
@Repository
public class AppointmentDAOImpl extends BaseDAOImpl<Appointment> implements AppointmentsDAO {

    @Override
    public List<Appointment> getAppointmentByDoctorsEmailAfterSomeDate(String email, Date date) {
        return entityManager
                .createQuery("select a " +
                        "from Appointment a " +
                        "where a.appointmentDate>?1 and a.doctor.email = ?2 and a.isApproved = true ").setParameter(1,date).setParameter(2,email)
                .getResultList();
    }

    @Override
    public List<Appointment> appointmentsWithDoctor(Long id) {
        return entityManager.createQuery("select a from Appointment a join a.doctor d join d.specialization s where a.user.id = :id order by a.appointmentDate").setParameter("id", id).getResultList();
    }

    public List<Appointment> getAppiontmentbyDoctorsEmail(String email) {
        return entityManager.createQuery("select a from Appointment a where a.doctor.email= ?1").setParameter(1,email).getResultList();
    }

    @Override
    public List<Appointment> getAppointmentByUserEmail(String email) {

        return entityManager.createQuery("select a " +
                "from Appointment a " +
                "where a.user.email= ?1")
                .setParameter(1,email)
                .getResultList();


    }
}

