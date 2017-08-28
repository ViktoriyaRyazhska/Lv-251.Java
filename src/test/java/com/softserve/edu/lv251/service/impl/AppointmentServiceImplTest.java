package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.dao.AppointmentsDAO;
import com.softserve.edu.lv251.entity.Appointment;
import com.softserve.edu.lv251.entity.Doctor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Marian Brynetskyi on 14.08.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class AppointmentServiceImplTest {

    @Spy
    List<Appointment> appointments = new LinkedList<>();
    @Captor
    ArgumentCaptor<Appointment> captor;

    @Mock
    private AppointmentsDAO appointmentsDAO;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");

    @Before
    public void setUp() throws ParseException {
        MockitoAnnotations.initMocks(this);

        Appointment appointment2 = new Appointment();
        Appointment appointment1 = new Appointment();

        appointment1.setAppointmentDate(sdf.parse("12/09/2017 - 15:00"));
        appointment2.setAppointmentDate(sdf.parse("12/08/2017 - 15:00"));

        appointment1.setIsApproved(true);
        appointment2.setIsApproved(true);

        appointments.add(appointment1);
        appointments.add(appointment2);
    }

    @Test
    public void addAppointment() throws Exception {
        doNothing().when(appointmentsDAO).addEntity(any(Appointment.class));
        Appointment appointment = new Appointment();
        appointment.setId(1);

        Doctor doc = new Doctor();
        doc.setFirstname("Petro");
        appointment.setDoctor(doc);
        appointmentService.addAppointment(appointment);

        verify(appointmentsDAO, times(1)).addEntity(captor.capture());
        Assert.assertEquals(1, captor.getValue().getId());
        Assert.assertEquals("Petro", captor.getValue().getDoctor().getFirstname());


    }

    @Test
    public void updateAppointment() throws Exception {
    }

    @Test
    public void getAppointmentById() throws Exception {

    }

    @Test
    public void getAllDoctorAppointmentsAfterNow() throws Exception {
    }

    @Test
    public void getAllDoctorsAppointmentsAfterNow() throws Exception {

    }

    @Test
    public void getAllDoctorsAppointmentsAfterNow1() throws Exception {
    }

    @Test
    public void listAppointmensWithDoctor() throws Exception {
    }

    @Test
    public void getAppiontmentbyDoctorsEmail() throws Exception {

        when(appointmentsDAO.getAppointmentByUserEmail("kilopo@ex.ua")).thenReturn(appointments);

        when(appointmentService.getAppiontmentbyDoctorsEmail("kilopo@ex.ua")).thenReturn(appointments);

        List<Appointment> forTest = appointmentService.getAppiontmentbyDoctorsEmail("kilopo@ex.ua");
        Assert.assertEquals(2,forTest.size());
    }

    @Test
    public void getAppointmentByUserEmail() throws Exception {
    }

}