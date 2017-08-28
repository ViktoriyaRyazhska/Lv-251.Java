package com.softserve.edu.lv251.dao.impl;

import com.softserve.edu.lv251.dao.RoleDAO;
import com.softserve.edu.lv251.entity.Role;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Marian Brynetskyi on 13.07.2017.
 */
@Transactional
@Repository
public class RoleDAOImpl extends BaseDAOImpl<Role> implements RoleDAO {
}
