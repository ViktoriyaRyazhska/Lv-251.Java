package com.softserve.edu.lv251.service;

import com.softserve.edu.lv251.entity.Doctors;

import java.util.List;

/**
 * Created by Admin on 21.07.2017.
 */
public interface DoctorsService {
    void  add(Doctors doctors);
    List<Doctors> getAll();
    void update(Doctors doctors);
    Doctors find(long id);
    void delete(Doctors doctors);
    List<Doctors>searchByLetters(String search);
}
