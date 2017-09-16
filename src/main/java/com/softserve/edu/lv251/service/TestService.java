package com.softserve.edu.lv251.service;

import com.softserve.edu.lv251.entity.Test;

import java.util.List;

/**
 * Created by Marian Brynetskyi on 07.09.2017.
 */
public interface TestService {

    List<String> getTestsNames();

    Test getTestByName(String test);
}
