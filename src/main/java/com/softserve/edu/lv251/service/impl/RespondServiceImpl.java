package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.dao.RespondDAO;
import com.softserve.edu.lv251.entity.Respond;
import com.softserve.edu.lv251.service.RespondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Marian Brynetskyi on 23.08.2017.
 */
@Service
public class RespondServiceImpl implements RespondService {

    @Autowired
    private RespondDAO respondDAO;

    @Override
    public List<Respond> getAllRespondsByDoctor(long doctorId) {
        return respondDAO.getAllEntities()
                .stream()
                .filter(p->p.getDoctor().getId()==doctorId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Respond> getAllRespondsByUser(long userId) {
        return respondDAO.getAllEntities()
                .stream()
                .filter(p->p.getUser().getId()==userId)
                .collect(Collectors.toList());
    }
}
