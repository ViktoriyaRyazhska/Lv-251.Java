package com.softserve.edu.lv251.dao.impl;

import com.softserve.edu.lv251.entity.Appointment;
import com.softserve.edu.lv251.entity.Doctor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by Marian Brynetskyi on 14.08.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class AppointmentDAOImplTest {
    @Mock
    private EntityManager entityManager;
    @Mock
    Query query;
    @Spy
    List<Appointment> appointmentList = new ArrayList<>();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");

    @Before
    public void beforeTest() throws ParseException {

        MockitoAnnotations.initMocks(this);
        Doctor doctor = new Doctor();
        doctor.setId(1);
        doctor.setFirstname("Petro");
        doctor.setEmail("somemail@mail.com");

        Appointment appointment = new Appointment();
        appointment.setId(1);
        appointment.setAppointmentDate(sdf.parse("12/08/2017 - 15:00"));

        Appointment appointment1 = new Appointment();
        appointment1.setId(2);
        appointment1.setAppointmentDate(sdf.parse("12/09/2017 - 15:00"));

        appointment1.setDoctor(doctor);
        appointment.setDoctor(doctor);
        appointmentList.add(appointment);
        appointmentList.add(appointment1);
    }

    @Test
    public void getAppointmentByDoctorsEmailAfterSomeDate() throws Exception {
        when(entityManager.createQuery("")).thenReturn(query);
        when(query.getResultList()).thenReturn(appointmentList);
        Assert.assertEquals(sdf.parse("12/08/2017 - 15:00"), ((Appointment) query.getResultList().get(0)).getAppointmentDate());
        Assert.assertEquals(sdf.parse("12/09/2017 - 15:00"), ((Appointment) query.getResultList().get(1)).getAppointmentDate());
    }

    @Test
    public void appointmentsWithDoctor() throws Exception {
    }

    @Test
    public void getAppiontmentbyDoctorsEmail() throws Exception {
        when(entityManager.createQuery("")).thenReturn(query);
        when(query.getResultList()).thenReturn(appointmentList);
        Assert.assertEquals(2, entityManager.createQuery("").getResultList().size());
        Assert.assertEquals("somemail@mail.com", ((Appointment) query.getResultList().get(0)).getDoctor().getEmail());
        Assert.assertEquals("somemail@mail.com", ((Appointment) query.getResultList().get(1)).getDoctor().getEmail());
    }

    @Test
    public void getAppointmentByUserEmail() throws Exception {
    }

    @Test
    public void addEntity() throws Exception {
    }

    @Test
    public void updateEntity() throws Exception {
    }

    @Test
    public void getEntityByID() throws Exception {
    }

    @Test
    public void getEntitiesByColumnNameAndValue() throws Exception {
    }

    @Test
    public void getAllEntities() throws Exception {
    }

    @Test
    public void deleteEntity() throws Exception {
    }

    @Test
    public void pagination() throws Exception {
    }

}