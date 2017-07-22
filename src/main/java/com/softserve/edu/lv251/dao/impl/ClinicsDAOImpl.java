package com.softserve.edu.lv251.dao.impl;


import com.softserve.edu.lv251.dao.ClinicsDAO;
import com.softserve.edu.lv251.entity.Clinics;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

/**
 * Created by kilopo on 13.07.2017.
 */
@Transactional
@Repository
public class ClinicsDAOImpl extends BaseDAOImpl<Clinics> implements ClinicsDAO {
}
