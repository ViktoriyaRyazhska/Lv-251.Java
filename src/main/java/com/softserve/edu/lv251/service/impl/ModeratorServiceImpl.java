package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.dao.ModeratorDAO;
import com.softserve.edu.lv251.entity.Moderator;
import com.softserve.edu.lv251.service.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Yana Martynyak on 31.07.2017.
 */
@Service
public class ModeratorServiceImpl implements ModeratorService {

    @Autowired
    private ModeratorDAO moderatorDAO;

    @Override
    public Moderator getByEmail(String email) {
        List<Moderator> moderators = moderatorDAO.getEntitiesByColumnNameAndValue("email", email);
        return moderators.isEmpty() ? null : moderators.get(0);
    }
}
