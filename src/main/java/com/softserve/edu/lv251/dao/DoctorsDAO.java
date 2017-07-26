package com.softserve.edu.lv251.dao;

import com.softserve.edu.lv251.entity.Doctors;

import java.util.List;

/**
 * Created by Taras on 16.07.2017.
 */
public interface DoctorsDAO extends BaseDAO<Doctors>{
    List searchByLetters(String letters);
}
