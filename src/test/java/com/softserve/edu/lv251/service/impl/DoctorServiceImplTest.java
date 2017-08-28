package com.softserve.edu.lv251.service.impl;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;




import com.softserve.edu.lv251.dao.DoctorDAO;
import com.softserve.edu.lv251.entity.Doctor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Yana Martynyak on 13.08.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class DoctorServiceImplTest {

    @Spy
    private List<Doctor> doctors = new LinkedList<>();
    @Captor
    private ArgumentCaptor<Doctor> captor;
    @Mock
    private DoctorDAO doctorDAO;
    @InjectMocks
    private DoctorServiceImpl doctorService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        doctors.add(new Doctor());
        doctors.add(new Doctor());

    }

    @Test
    public void addDoctor() throws Exception {
        doNothing().when(doctorDAO).addEntity(any(Doctor.class));
        Doctor doc=new Doctor();
        doc.setFirstname("Petro");
        doctorService.addDoctor(doc);
        verify(doctorDAO, times(1)).addEntity(captor.capture());
        Assert.assertEquals(captor.getValue().getFirstname(), "Petro");

    }

    @Test
    public void getAll() throws Exception {


        when(doctorDAO.getAllEntities()).thenReturn(doctors);
        assertEquals(2,doctorService.getAll().size());
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void find() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void searchByLetters() throws Exception {
    }

    @Test
    public void getDoctorsByColumnNameAndValue() throws Exception {
    }

    @Test
    public void findByEmail() throws Exception {
    }

    @Test
    public void registerNewDoctorAccount() throws Exception {
    }

    @Test
    public void appointmentsInThisMonth() throws Exception {
    }

    @Test
    public void searchByDistrict() throws Exception {
    }

    @Test
    public void searchBySpecialization() throws Exception {
    }

    @Test
    public void getDoctorPatients() throws Exception {
    }

    @Test
    public void getDao() throws Exception {
    }

    @Test
    public void getByClinic() throws Exception {
    }

    @Test
    public void addDoctorAccount() throws Exception {
    }

    @Test
    public void getDoctorByNameWithLimitAndOffset() throws Exception {
    }

    @Test
    public void findById() throws Exception {
    }

    @Test
    public void makeDoctorFromUser() throws Exception {
    }

}