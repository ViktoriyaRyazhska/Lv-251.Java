package com.softserve.edu.lv251.dao;


import com.softserve.edu.lv251.entity.Clinic;
import com.softserve.edu.lv251.entity.Moderator;

import java.util.List;

/**
 *
 */
public interface ClinicDAO extends BaseDAO<Clinic>{

 List<Clinic>findByDistrict(String name);

 List<Clinic>searchByLetters(String letters);

 List<Clinic>getWithOffsetAndLimit(int offset, int limit);

 List<Clinic>getByNameWithOffsetAndLimit(String value, int offset, int limit);


}
