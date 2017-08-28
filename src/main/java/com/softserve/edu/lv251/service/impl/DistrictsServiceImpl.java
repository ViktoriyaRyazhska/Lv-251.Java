package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.config.Mapper;
import com.softserve.edu.lv251.dao.DistrictDAO;
import com.softserve.edu.lv251.dto.pojos.DistrictsDTO;
import com.softserve.edu.lv251.entity.District;
import com.softserve.edu.lv251.service.DistrictsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 29.07.2017.
 */
@Service
public class DistrictsServiceImpl implements DistrictsService {
    @Autowired
    private DistrictDAO districtDAO;
    @Autowired
    private Mapper mapper;

    @Override
    public List<DistrictsDTO> findByName(String name) {
        List<District> districts = districtDAO.findByName(name);
        List<DistrictsDTO> results = new ArrayList<>();

        for (District district : districts) {
            DistrictsDTO result = new DistrictsDTO();
            mapper.map(district, result);
            results.add(result);
        }
        return results;

    }
}
