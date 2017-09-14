package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.config.Mapper;
import com.softserve.edu.lv251.dao.TestResultDAO;
import com.softserve.edu.lv251.dto.pojos.TestResultDTO;
import com.softserve.edu.lv251.service.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Marian Brynetskyi on 07.09.2017.
 */
@Service
public class TestResultServiceImpl implements TestResultService {

    @Autowired
    private TestResultDAO testResultDAO;

    @Autowired
    private Mapper mapper;

    @Override
    public List<TestResultDTO> getUserTestResults(String email) {
        List<TestResultDTO> testResultDTOS = new LinkedList<>();
        testResultDAO.getAllEntities()
                .stream()
                .filter(p -> p.getUser().getEmail().equals(email))
                .forEach(p -> testResultDTOS.add(mapper.map(p, TestResultDTO.class)));
        return testResultDTOS;
    }
}
