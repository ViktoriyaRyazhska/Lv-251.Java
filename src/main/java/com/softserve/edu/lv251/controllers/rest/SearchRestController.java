package com.softserve.edu.lv251.controllers.rest;

import com.softserve.edu.lv251.dto.pojos.SearchResultClinicDTO;
import com.softserve.edu.lv251.dto.pojos.SearchResultDoctorDTO;
import com.softserve.edu.lv251.service.ClinicService;
import com.softserve.edu.lv251.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Taras on 05.08.2017.
 */

@RestController
@RequestMapping("rest/search")
public class SearchRestController {


    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ClinicService clinicService;

    @RequestMapping("/doctors")
    public List<SearchResultDoctorDTO> searchForDoctors(@RequestParam(name = "value", required = false) String value,
                                                        @RequestParam(name = "offset") int offset,
                                                        @RequestParam(name = "limit") int limit) {
        return doctorService.getDoctorByNameWithLimitAndOffset(value, offset, limit);
    }

    @RequestMapping("/clinics")
    public List<SearchResultClinicDTO> searchForClinics(@RequestParam(name = "value", required = false) String value,
                                                        @RequestParam(name = "offset") int offset,
                                                        @RequestParam(name = "limit") int limit) {
        return clinicService.getWithOffsetOrderedByName(value, offset, limit);
    }

}
