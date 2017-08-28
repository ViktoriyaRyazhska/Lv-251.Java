package com.softserve.edu.lv251.service;

import com.softserve.edu.lv251.dto.pojos.DistrictsDTO;

import java.util.List;

/**
 * Created by Admin on 29.07.2017.
 */
public interface DistrictsService {
    List<DistrictsDTO> findByName(String name);
}
