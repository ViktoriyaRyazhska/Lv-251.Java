package com.softserve.edu.lv251.dao.impl;


import com.softserve.edu.lv251.dao.ClinicsDAO;
import com.softserve.edu.lv251.entity.Clinics;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 *
 */
@Transactional
@Repository
public class ClinicsDAOImpl extends BaseDAOImpl<Clinics> implements ClinicsDAO {

    @Override
    public List<Clinics> getTenClinics(Integer i) {
        Query query = entityManager.createQuery("FROM Clinics clinics order by clinics.clinic_name");
        query.setFirstResult(i*10-10);
        query.setMaxResults(10);
        return query.getResultList();
    }
}
