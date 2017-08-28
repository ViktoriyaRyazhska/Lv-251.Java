package com.softserve.edu.lv251.controllers;

import com.softserve.edu.lv251.dto.pojos.AppointmentsForCreationDTO;
import com.softserve.edu.lv251.entity.Doctor;
import com.softserve.edu.lv251.service.AppointmentService;
import com.softserve.edu.lv251.service.DoctorsService;
import com.softserve.edu.lv251.service.PagingSizeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Yana Martynyak on 13.08.2017.
 */
//@RunWith(MockitoJUnitRunner.class)
public class AllDoctorsControllerTest {
    private MockMvc mockMvc;
    @Mock
    private DoctorsService doctorsService;
    @Mock
    private PagingSizeService<Doctor> pagingSizeService;
    @Mock
    private AppointmentService appointmentService;
    @InjectMocks
    private AllDoctorsController doctorsController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(doctorsController)
                .build();
    }

    @Test
    public void allDoctors() throws Exception {
        List<Doctor> doctors = new ArrayList<>();
        List<AppointmentsForCreationDTO> appointments = new ArrayList<>();
        when(pagingSizeService.numberOfPaging(10)).thenReturn(10);
        when(pagingSizeService.getEntity(pagingSizeService.numberOfPaging(10), 10)).thenReturn((List) doctors);
        when(appointmentService.getAllDoctorAppointmentsAfterNow(1)).thenReturn(appointments);
        int page = pagingSizeService.numberOfPaging(10);
        doctors = pagingSizeService.getEntity(page, 10);
        appointments = appointmentService.getAllDoctorAppointmentsAfterNow(1);
        /*mockMvc.perform(get("/allDoctors/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("allDoctors"))
                .andExpect(model().attribute("getDoctor", doctors))
                .andExpect(model().attribute("numberChain", page))
                .andExpect(model().attribute("docApps", appointments));*/

    }

    @Test
    public void addAppointment() throws Exception {
    }

    @Test
    public void searchDoctors() throws Exception {
    }

    @Test
    public void doctorById() throws Exception {
    }

    @Test
    public void doctor() throws Exception {
    }

}