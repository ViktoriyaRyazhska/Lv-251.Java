package com.softserve.edu.lv251.dao.impl;


import com.softserve.edu.lv251.dao.ClinicsDAO;
import com.softserve.edu.lv251.entity.Clinics;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 *
 */
@Transactional
@Repository
public class ClinicsDAOImpl extends BaseDAOImpl<Clinics> implements ClinicsDAO {


}
