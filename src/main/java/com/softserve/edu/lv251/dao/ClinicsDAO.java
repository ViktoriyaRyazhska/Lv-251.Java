package com.softserve.edu.lv251.dao;


import com.softserve.edu.lv251.entity.Clinics;

import java.util.List;

/**
 *
 */
public interface ClinicsDAO extends BaseDAO<Clinics>{

    public List<Clinics> getTenClinics(Integer i);
}
