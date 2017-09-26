package com.softserve.edu.lv251.dao;

import com.softserve.edu.lv251.entity.User;

import java.util.List;

/**
 * Created by Taras on 16.07.2017.
 */
public interface UserDAO extends BaseDAO<User>{
    List<User> searchByLetters(String letters);


}
