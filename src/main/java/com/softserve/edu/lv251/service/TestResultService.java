package com.softserve.edu.lv251.service;

import com.softserve.edu.lv251.dto.pojos.TestResultDTO;

import java.util.List;

/**
 * Created by Marian Brynetskyi on 07.09.2017.
 */
public interface TestResultService {

    List<TestResultDTO> getUserTestResults(String email);

    boolean addTestResult(long userId, String description, String test, String startDate, String endDate);
}
