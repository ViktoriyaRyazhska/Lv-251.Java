package com.softserve.edu.lv251.service;

import com.softserve.edu.lv251.entity.Admin;

/**
 * Created by Shmidt on 15.08.2017.
 */
public interface AdminService {
    Admin findByEmail(String email);
}
