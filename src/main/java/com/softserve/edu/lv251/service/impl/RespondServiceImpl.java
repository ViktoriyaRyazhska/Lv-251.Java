package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.config.Mapper;
import com.softserve.edu.lv251.dao.RespondDAO;
import com.softserve.edu.lv251.dto.pojos.DoctorRespondDTO;
import com.softserve.edu.lv251.dto.pojos.RespondDTO;
import com.softserve.edu.lv251.entity.Respond;
import com.softserve.edu.lv251.service.AppointmentService;
import com.softserve.edu.lv251.service.DoctorService;
import com.softserve.edu.lv251.service.RespondService;
import com.softserve.edu.lv251.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Marian Brynetskyi on 23.08.2017.
 */
@Service
public class RespondServiceImpl implements RespondService {

    @Autowired
    private Mapper mapper;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private UserService usersService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private RespondDAO respondDAO;

    @Override
    public List<RespondDTO> getAllRespondsByDoctor(long doctorId) {
        List<RespondDTO> respondDTOS = new LinkedList<>();
        respondDAO.getAllEntities()
                .stream()
                .filter(p -> p.getDoctor().getId() == doctorId)
                .forEach(p -> respondDTOS.add(mapper.map(p, RespondDTO.class)));
        return respondDTOS;
    }

    @Override
    public List<Respond> getAllRespondsByUser(long userId) {
        return respondDAO.getAllEntities()
                .stream()
                .filter(p -> p.getUser().getId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public boolean AddRespond(short raiting, String description, long userId, long doctorId) {
        Respond respond = new Respond();
        respond.setDate(new Date());
        respond.setDescription(description);
        respond.setDoctor(doctorService.getById(doctorId));
        respond.setUser(usersService.getUserByID(userId));

        if (raiting > 5 || raiting < 0 || raiting % 1 != 0) {
            return false;
        }
        respond.setRaiting(raiting);
        respondDAO.addEntity(respond);
        return true;
    }

    @Override
    public List<DoctorRespondDTO> setResponded(long userId, List<DoctorRespondDTO> doctorRespondDTOS) {
        Date date = new Date();

        doctorRespondDTOS.forEach(doctorRespondDTO -> {
            if (appointmentService.listAppointmensWithUser(userId)
                    .stream()
                    .anyMatch(p -> p.getDoctor().getId() == doctorRespondDTO.getId()
                            && p.getIsApproved()
                            && p.getAppointmentDate().before(date)
                    )
                    ) {
                doctorRespondDTO.setResponded(true);
            } else {
                doctorRespondDTO.setResponded(false);
            }
        });

        return doctorRespondDTOS;
    }
}
