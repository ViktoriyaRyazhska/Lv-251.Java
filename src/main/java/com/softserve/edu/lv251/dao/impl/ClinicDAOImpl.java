package com.softserve.edu.lv251.dao.impl;


import com.softserve.edu.lv251.dao.ClinicDAO;
import com.softserve.edu.lv251.entity.Clinic;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 *
 */
@Transactional
@Repository
public class ClinicDAOImpl extends BaseDAOImpl<Clinic> implements ClinicDAO {


    @Override
    public List<Clinic> findByDistrict(String name) {
        Query query = entityManager.createQuery("select c from Clinic c join c.contact cont join cont.district d where d.name like :name")
                .setParameter("name", name);

        return query.getResultList();
    }

    @Override
    public List<Clinic> searchByLetters(String letters) {
        String name = letters + "%".toLowerCase();
        Query query = entityManager.createQuery("select c from Clinic c where lower(c.clinic_name) like :name").setParameter("name", name);

        return query.getResultList();
    }

    @Override
    public List<Clinic> getWithOffsetAndLimit(int offset, int limit) {
        Query query = entityManager.createQuery(
                "select c " +
                        "from Clinic c")
                .setFirstResult(offset)
                .setMaxResults(limit);

        return query.getResultList();
    }

    @Override
    public List<Clinic> getByNameWithOffsetAndLimit(String value, int offset, int limit) {
        String name = "%" + value + "%";

        Query query = entityManager.createQuery(
                "select c " +
                        "from Clinic c " +
                        "where c.clinic_name like :name ")
                .setParameter("name", name)
                .setFirstResult(offset)
                .setMaxResults(limit);
        return query.getResultList();
    }


}
