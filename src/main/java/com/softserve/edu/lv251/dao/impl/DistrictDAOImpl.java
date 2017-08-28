package com.softserve.edu.lv251.dao.impl;

import com.softserve.edu.lv251.dao.DistrictDAO;
import com.softserve.edu.lv251.entity.District;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Admin on 29.07.2017.
 */
@Repository
@PersistenceContext
public class DistrictDAOImpl extends BaseDAOImpl<District> implements DistrictDAO {

    @Override
    public List<District> findByName(String name) {
        String letters = name + "%".toLowerCase();
        Query query = entityManager.createQuery("from District d where lower(d.name) like :letters").setParameter("letters", letters);
        return query.getResultList();
    }
}
