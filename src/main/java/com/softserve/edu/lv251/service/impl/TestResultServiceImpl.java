package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.config.Mapper;
import com.softserve.edu.lv251.dao.TestResultDAO;
import com.softserve.edu.lv251.dto.pojos.TestResultDTO;
import com.softserve.edu.lv251.entity.TestsResult;
import com.softserve.edu.lv251.service.TestResultService;
import com.softserve.edu.lv251.service.TestService;
import com.softserve.edu.lv251.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private UserService userService;

    @Autowired
    private Mapper mapper;

    @Autowired
    private TestService testService;

    @Override
    public List<TestResultDTO> getUserTestResults(String email) {
        List<TestResultDTO> testResultDTOS = new LinkedList<>();
        testResultDAO.getAllEntities()
                .stream()
                .filter(p -> p.getUser().getEmail().equals(email))
                .forEach(p -> testResultDTOS.add(
                        mapper.map(p, TestResultDTO.class)
                ));
        return testResultDTOS;
    }

    @Override
    public List<TestResultDTO> getUserTestResults(long userId) {
        List<TestResultDTO> testResultDTOS = new LinkedList<>();
        testResultDAO.getAllEntities()
                .stream()
                .filter(p -> p.getUser().getId() == userId)
                .forEach(p -> testResultDTOS.add(
                        mapper.map(p, TestResultDTO.class)
                ));
        return testResultDTOS;
    }

    @Override
    public boolean addTestResult(long userId, String description, String test, String startDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start;
        Date finish;
        try {
            start = sdf.parse(startDate);
            finish = sdf.parse(endDate);
            if (start.after(finish)){
                return false;
            }
        } catch (ParseException e) {
            return false;
        }

        TestsResult testsResult = new TestsResult();
        testsResult.setDescription(description);
        testsResult.setEndDdate(start);
        testsResult.setStartDdate(finish);
        testsResult.setTest(testService.getTestByName(test));
        testsResult.setUser(userService.getUserByID(userId));

        if(testResultDAO.getAllEntities()
                .stream()
                .anyMatch(p ->(p.getUser().getId() == testsResult.getUser().getId()
                    && p.getStartDdate().equals(testsResult.getStartDdate())
                    && p.getEndDdate().equals(testsResult.getEndDdate())
                    && p.getDescription().equals(testsResult.getDescription())))){
            return false;
        }

        testResultDAO.addEntity(testsResult);
        return true;
    }
}
