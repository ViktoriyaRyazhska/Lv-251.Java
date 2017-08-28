package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.config.Mapper;
import com.softserve.edu.lv251.dto.pojos.ClinicLatLngDTO;
import com.softserve.edu.lv251.entity.Clinic;
import com.softserve.edu.lv251.service.ClinicService;
import com.softserve.edu.lv251.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taras on 27.07.2017.
 */
@Service
public class MapServiceImpl implements MapService {

    @Autowired
    private Mapper mapper;

    @Autowired
    private ClinicService clinicService;

    @Override
    public List<ClinicLatLngDTO> getAllClinicsCoordinates() {
        List<ClinicLatLngDTO> dtoList = new ArrayList<>();
        List<Clinic> clinics = clinicService.getAllClinics();

        for (Clinic clinic : clinics) {
            ClinicLatLngDTO latlang = new ClinicLatLngDTO();
            mapper.map(clinic, latlang);

            if (latlang.getLat() != 0 && latlang.getLng() != 0) {
                dtoList.add(latlang);
            }

        }
        return dtoList;
    }
}
