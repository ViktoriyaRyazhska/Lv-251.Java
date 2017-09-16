package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.dao.TestDAO;
import com.softserve.edu.lv251.entity.Test;
import com.softserve.edu.lv251.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Marian Brynetskyi on 07.09.2017.
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDAO testDAO;

    @Override
    public List<String> getTestsNames() {
        List<String> testsNames = new LinkedList<>();
        testDAO.getAllEntities().forEach(p -> testsNames.add(p.getName()));
        return testsNames;
    }

    @Override
    public Test getTestByName(String test) {
        return testDAO.getEntitiesByColumnNameAndValue("name", test).stream().findFirst().get();
    }
}
