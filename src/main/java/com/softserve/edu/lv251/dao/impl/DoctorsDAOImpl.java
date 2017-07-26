package com.softserve.edu.lv251.dao.impl;

import com.softserve.edu.lv251.dao.DoctorsDAO;
import com.softserve.edu.lv251.entity.Doctors;
import org.springframework.stereotype.Repository;


import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by kilopo on 13.07.2017.
 */
@Transactional
@Repository
public class DoctorsDAOImpl extends BaseDAOImpl<Doctors> implements DoctorsDAO {
    @Override
    public List<Doctors> searchByLetters(String letters) {
        String search=letters+"%".toLowerCase();
       Query query= entityManager.createQuery("from Doctors d where lower(d.firstname) like" +
               " :letters or lower(d.lastname) like :letters or lower(d.specialization.name) like :letters").setParameter("letters",search);
        return query.getResultList();
    }
}
