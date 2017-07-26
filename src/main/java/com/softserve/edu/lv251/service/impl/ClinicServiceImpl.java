package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.dao.ClinicsDAO;
import com.softserve.edu.lv251.service.ClinicService;
import com.softserve.edu.lv251.entity.Clinics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Taras on 14.07.2017.
 */
@Service
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    private ClinicsDAO clinicsDAO;
    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Clinics> getWithOffsetOrderedByName(int offset, int limit) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Clinics> criteriaQuery = criteriaBuilder.createQuery(Clinics.class);
        Root<Clinics> from = criteriaQuery.from(Clinics.class);
        CriteriaQuery<Clinics> select = criteriaQuery.select(from);
        TypedQuery<Clinics> typedQuery = entityManager.createQuery(select);
        typedQuery.setFirstResult(offset);
        typedQuery.setMaxResults(limit);

        return typedQuery.getResultList();
    }

    @Override
    public void addClinic(Clinics clinic) {
        this.clinicsDAO.addEntity(clinic);
    }

    @Override
    public void updateClinic(Clinics clinic) {
        this.clinicsDAO.updateEntity(clinic);
    }

    @Override
    public Clinics getClinicByID(Long clinicId) {
        return this.clinicsDAO.getEntityByID(clinicId);
    }

    @Override
    public List<Clinics> getClinicsByColumnNameAndValue(String columnName, Object value) {
        return this.clinicsDAO.getEntitiesByColumnNameAndValue(columnName, value);
    }

    @Override
    public List<Clinics> getAllClinics() {
        return this.clinicsDAO.getAllEntities();
    }

    @Override
    public void deleteClinic(Clinics clinic) {
        this.clinicsDAO.deleteEntity(clinic);
    }

    public Clinics getFirst() {
        return clinicsDAO.getEntityByID(1L);
    }

    @Override
    public int numberOfPaging() {
        int n = clinicsDAO.getAllEntities().size();
        return ((int) Math.ceil((double) n/10));
    }

    @Override
    public List<Clinics> getTenClinics(Integer i) {
        return clinicsDAO.getTenClinics(i);
    }
}
