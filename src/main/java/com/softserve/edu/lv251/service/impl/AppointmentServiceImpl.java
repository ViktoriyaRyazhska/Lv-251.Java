package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.config.Mapper;
import com.softserve.edu.lv251.dao.AppointmentDAO;
import com.softserve.edu.lv251.dto.pojos.*;
import com.softserve.edu.lv251.entity.Appointment;
import com.softserve.edu.lv251.service.AppointmentService;
import com.softserve.edu.lv251.service.DoctorService;
import com.softserve.edu.lv251.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by kilopo on 31.07.2017.
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private UserService userService;

    @Autowired
    private Mapper mapper;

    @Autowired
    Logger logger;

    @Override
    public void addAppointment(Appointment appointment) {
        appointmentDAO.addEntity(appointment);
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        appointmentDAO.updateEntity(appointment);
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentDAO.getEntityByID(id);
    }

    @Override
    public List<AppointmentsForCreationDTO> getAllDoctorAppointmentsAfterNow(long doctorId) {
        List<AppointmentsForCreationDTO> appointmentsForCreationDTOS = new ArrayList<>();
        appointmentDAO.getAllEntities()
                .stream()
                .filter(p -> p.getDoctor().getId() == doctorId)
                .filter(p -> p.getIsApproved())
                .filter(p -> p.getAppointmentDate().after(new Date()))
                .forEach(p -> appointmentsForCreationDTOS.add(mapper.map(p, AppointmentsForCreationDTO.class)));

        return appointmentsForCreationDTOS;

    }

    @Override
    public List<AppointmentsForCreationDTO> getAllDoctorsAppointmentsAfterNow() {
        List<AppointmentsForCreationDTO> appointmentsForCreationDTOS = new ArrayList<>();
        appointmentDAO.getAllEntities()
                .stream()
                .filter(Appointment::getIsApproved)
                .filter(p -> p.getAppointmentDate().after(new Date()))
                .forEach(p -> appointmentsForCreationDTOS.add(mapper.map(p, AppointmentsForCreationDTO.class)));
        return appointmentsForCreationDTOS;
    }

    @Override
    public List<AppointmentsForDateTimePickerInDocDTO> getAllDoctorsAppointmentsAfterNow(String email, Date date) {
        List<AppointmentsForDateTimePickerInDocDTO> appointmentsForDateTimePickerInDocDTOS = new LinkedList<>();
        for (Appointment a :
                appointmentDAO.getAppointmentByDoctorsEmailAfterSomeDate(email, date)) {
            AppointmentsForDateTimePickerInDocDTO appointmentsForDateTimePickerInDocDTO = new AppointmentsForDateTimePickerInDocDTO();
            mapper.map(a, appointmentsForDateTimePickerInDocDTO);
            appointmentsForDateTimePickerInDocDTOS.add(appointmentsForDateTimePickerInDocDTO);
        }
        return appointmentsForDateTimePickerInDocDTOS;
    }

    public List<Appointment> listAppointmensWithDoctor(Long id) {
        return appointmentDAO.appointmentsWithDoctor(id);
    }

    public List<AppointmentsDTO> getAppiontmentbyDoctorsEmail(String email) {
        List<AppointmentsDTO> appointmentsDTOs = new LinkedList<>();
        for (Appointment appointment : appointmentDAO.getAppiontmentbyDoctorsEmail(email)) {
            AppointmentsDTO appointmentsDTO = new AppointmentsDTO();
            mapper.map(appointment, appointmentsDTO);
            appointmentsDTOs.add(appointmentsDTO);
        }
        return appointmentsDTOs;
    }

    @Override
    public List<AppointmentDTO> getAppointmentDtoByUserEmail(String email) {
        List<AppointmentDTO> results = new ArrayList<>();

        for (Appointment appointment : appointmentDAO.getAppointmentByUserEmail(email)) {
            AppointmentDTO res = new AppointmentDTO();
            mapper.map(appointment, res);
            results.add(res);
        }
        return results;
    }

    @Override
    public List<Appointment> getAppointmentByUserEmail(String email) {
        return appointmentDAO.getAppointmentByUserEmail(email);
    }

    public boolean createAppointment (String localdate, String userEmail, long doctorId){
        Date date;

        SimpleDateFormat isoFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
        isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            date = isoFormat.parse(localdate);
            if (date.before(new Date())) {
                return false;
            }

            boolean appointments1 = listAppointmensWithDoctor(doctorId)
                    .stream()
                    .anyMatch(p -> p.getIsApproved() && p.getAppointmentDate().getTime() == date.getTime());

            if(appointments1){
                return false;
            }

            Appointment appointment = new Appointment();
            appointment.setAppointmentDate(date);
            appointment.setIsApproved(false);
            appointment.setUser(userService.findByEmail(userEmail));
            appointment.setDoctor(doctorService.find(doctorId));
            addAppointment(appointment);
        } catch (ParseException e) {
            logger.info(e);
            return false;
        }
        return true;

    }

    @Override
    public List<Appointment> listAppointmensWithUser(Long id) {
        return appointmentDAO.getAllEntities().stream().filter(p->p.getUser().getId() == id).collect(Collectors.toList());
    }
@Override
    public List<AppointmentsInfoDTO> getAppointmentsToUser(long id){
        List<Appointment> appointments= new LinkedList<>();
        appointments=listAppointmensWithUser(id);

        List<AppointmentsInfoDTO> appointmentsInfoDTOS = new LinkedList<>();
        Date date = new Date();
        listAppointmensWithUser(id)
                .stream()
                .filter(p->p.getAppointmentDate().before(date))
                .forEach(p->appointmentsInfoDTOS.add(mapper.map(p, AppointmentsInfoDTO.class)));

        return appointmentsInfoDTOS;

    }


@Override
    public List<AppointmentsInfoDTO> getPendingAppointmentsToUser(long id){
        List<Appointment> appointments= new LinkedList<>();
        appointments=listAppointmensWithUser(id);

        List<AppointmentsInfoDTO> appointmentsInfoDTOS = new LinkedList<>();
        Date date = new Date();
        listAppointmensWithUser(id)
                .stream()
                .filter(p->p.getAppointmentDate().after(date))
                .forEach(p->appointmentsInfoDTOS.add(mapper.map(p, AppointmentsInfoDTO.class)));

        return appointmentsInfoDTOS;

    }


}
