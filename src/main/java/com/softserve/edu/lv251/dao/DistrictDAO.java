package com.softserve.edu.lv251.dao;

import com.softserve.edu.lv251.entity.District;

import java.util.List;

/**
 * Created by Admin on 29.07.2017.
 */
public interface DistrictDAO extends BaseDAO<District> {
    List<District> findByName(String name);
}
