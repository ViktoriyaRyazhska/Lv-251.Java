package com.softserve.edu.lv251.dao.impl;

import com.softserve.edu.lv251.dao.UsersDAO;
import com.softserve.edu.lv251.entity.Users;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;


/**
 * Created by kilopo on 13.07.2017.
 */
@Transactional
@Repository
public class UsersDAOImpl extends BaseDAOImpl<Users> implements UsersDAO {
}
