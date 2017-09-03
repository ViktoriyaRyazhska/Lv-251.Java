package com.softserve.edu.lv251.dao.impl;

import com.softserve.edu.lv251.dao.DoctorDAO;
import com.softserve.edu.lv251.entity.Appointment;
import com.softserve.edu.lv251.entity.Clinic;
import com.softserve.edu.lv251.entity.Doctor;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by Marian Brynetskyi on 13.07.2017.
 */
@Transactional
@Repository
public class DoctorDAOImpl extends BaseDAOImpl<Doctor> implements DoctorDAO {
    @Override
    public List<Doctor> searchByLetters(String letters) {
        String search = letters + "%".toLowerCase();
        Query query = entityManager.createQuery("from Doctor d where lower(d.firstname) like" +
                " :letters or lower(d.lastname) like :letters or lower(d.specialization.name) like :letters").setParameter("letters", search);
        return query.getResultList();
    }

    public List<Appointment> appointmentsInThisMonth(Long id, Date date) {
        return entityManager
                .createQuery("from Appointment a where month(date) = month(a.appointmentDate) and a.doctor.id = id" +
                        " and year(date) = year(a.appointmentDate)")
                .getResultList();
    }


    @Override
    public List<Doctor> searchBySpecialization(String name) {
        Query query = entityManager.createQuery("select d from Doctor d join d.specialization s where s.name like :name ").setParameter("name", name);
        return query.getResultList();
    }


    @Override
    public List<Doctor> searchByDistrict(String name) {
        Query query = entityManager.createQuery("select d from Doctor d join d.clinic c join c.contact cont join cont.district dist where" +
                " dist.name like :name").setParameter("name", name);
        return query.getResultList();
    }


    @Override
    public List<Doctor> getWithOffsetAndLimit(int offset, int limit) {
        Query query = entityManager.createQuery(
                "select d " +
                        "from Doctor d")
                .setFirstResult(offset)
                .setMaxResults(limit);

        return query.getResultList();

    }

    @Override
    public List<Doctor> searchByNameAndSpecialisationWithOffsetAndLimit(String value, int offset, int limit) {
        String name = "%" + value + "%";

        Query query = entityManager.createQuery(
                "select d " +
                        "from Doctor d " +
                        "join d.specialization s " +
                        "where s.name + d.firstname + d.lastname + d.middlename like :name ")
                .setParameter("name", name)
                .setFirstResult(offset)
                .setMaxResults(limit);

        return query.getResultList();
    }
    public List<Doctor>getByClinic(Clinic clinic){
        Query query=entityManager.createQuery("from Doctor d where d.clinic :name").setParameter("name",clinic);
        return query.getResultList();
    }

    @Override
    public List<Doctor> getDoctorsByUser(long id) {
        System.out.println("before dao"+id);
        Query query= entityManager.createQuery("select distinct d from Appointment a join a.doctor d where a.user.id = :id").setParameter("id",id);

        List list =  query.getResultList();
        return list;
    }
}
