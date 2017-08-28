package com.softserve.edu.lv251.controllers;

import com.softserve.edu.lv251.dto.pojos.AppointmentDTO;
import com.softserve.edu.lv251.entity.User;
import com.softserve.edu.lv251.service.AppointmentService;
import com.softserve.edu.lv251.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Marian Brynetskyi on 14.08.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserCabinetControllerTest {

    private MockMvc mockMvc;
    @Mock
    private UserService userService;
    @Mock
    private AppointmentService appointmentService;
    @InjectMocks
    private UserCabinetController userCabinetController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userCabinetController)
                .build();
    }

    @Test
    public void userProfileGET() throws Exception {
    }

    @Test
    public void userProfilePOST() throws Exception {
    }

    @Test
    public void savePassword() throws Exception {
    }

    @Test
    public void medicalCardGET() throws Exception {

        List<AppointmentDTO> appointments = new ArrayList<>();
        appointments.add(new AppointmentDTO());

        User user = new User();
        user.setEmail("kilopo@ex.ua");
        user.setId(1);

        when(userService.findByEmail("")).thenReturn(user);
        when(appointmentService.getAppointmentDtoByUserEmail("")).thenReturn(appointments);


        /*mockMvc.perform(get("/user/medicalcard"))
                .andExpect(status().isOk())
                //.andExpect(view().name("userCabinetMedicalCard"))
                //.andExpect(model().attribute("listAppointments", appointments))
                .andExpect(model().attribute("date", new Date().getTime()));*/

    }

}