package com.softserve.edu.lv251.dao.impl;

import com.softserve.edu.lv251.dao.SpecializationDAO;
import com.softserve.edu.lv251.entity.Specialization;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

/**
 * Created by Admin on 21.07.2017.
 */
@Transactional
@Repository
public class SpecializationDAOImpl extends BaseDAOImpl<Specialization> implements SpecializationDAO {
}
