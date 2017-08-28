package com.softserve.edu.lv251.service;

import com.softserve.edu.lv251.entity.Specialization;

import java.util.List;

/**
 * Created by Admin on 29.07.2017.
 */

public interface SpecializationService {
    List<Specialization> searchByLetters(String letters);
    List<Specialization> findAll();
    Specialization findByName(String name);
    public  void  add(Specialization specialization);
}
