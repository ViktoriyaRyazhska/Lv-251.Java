package com.softserve.edu.lv251.dao;

import com.softserve.edu.lv251.application.Application;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by ace on 07/06/2017.
 */
public class EntityDAOImpl<T> implements EntityDAO<T> {
    private Class<T> entityClass;

    EntityDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void addEntity(T entity) {
        EntityManager entityManager = Application.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateEntity(T entity) {
        EntityManager entityManager = Application.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public T getEntityByID(Integer entityId) {
        return Application.getEntityManager().find(entityClass, entityId);
    }

    @Override
    public List<T> getEntitiesByColumnNameAndValue(String columnName, Object value) {
        CriteriaBuilder criteriaBuilder = Application.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery((entityClass));
        Root<T> tRoot = criteriaQuery.from(entityClass);
        criteriaQuery.select(tRoot).where(criteriaBuilder.equal(tRoot.get(columnName), value));
        return Application.getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<T> getAllEntities() {
        CriteriaQuery<T> criteriaQuery = Application.getEntityManager().getCriteriaBuilder().createQuery((entityClass));
        Root<T> tRoot = criteriaQuery.from(entityClass);
        criteriaQuery.select(tRoot);
        return Application.getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void deleteEntity(T entity) {
        EntityManager entityManager = Application.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }
}
