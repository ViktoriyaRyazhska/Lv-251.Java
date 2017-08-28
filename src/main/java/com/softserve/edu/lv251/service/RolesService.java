package com.softserve.edu.lv251.service;


import com.softserve.edu.lv251.entity.Role;

import java.util.List;

/**
 * Added by Pavlo Kuchereshko.
 *
 */
public interface RolesService {

    void addRole(Role role);

    void updateRole(Role role);

    Role findByName(String name);

    Role findById(Long roleId);

    List<Role> getAllRoles();
}
