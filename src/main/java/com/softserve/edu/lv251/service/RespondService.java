package com.softserve.edu.lv251.service;

import com.softserve.edu.lv251.entity.Respond;

import java.util.List;

/**
 * Created by Marian Brynetskyi on 23.08.2017.
 */
public interface RespondService {

    List<Respond> getAllRespondsByDoctor(long doctorId);

    List<Respond> getAllRespondsByUser(long userId);
}
