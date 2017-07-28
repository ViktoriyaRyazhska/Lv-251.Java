package com.softserve.edu.lv251.dao.impl;

import com.softserve.edu.lv251.dao.BaseDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 *
 */
public class BaseDAOImpl<T> implements BaseDAO<T> {

    private Class<T> entityClass;

    @PersistenceContext
    EntityManager entityManager;

    public BaseDAOImpl() {
        entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Transactional
    public void addEntity(T entity) {
        entityManager.persist(entity);
    }

    @Transactional
    public void updateEntity(T entity) {
        entityManager.merge(entity);
    }


    public T getEntityByID(Long entityId) {
        return entityManager.find(entityClass, entityId);
    }

    public List<T> getEntitiesByColumnNameAndValue(String columnName, Object value) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery((entityClass));
        Root<T> tRoot = criteriaQuery.from(entityClass);
        criteriaQuery.select(tRoot).where(criteriaBuilder.equal(tRoot.get(columnName), value));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<T> getAllEntities() {
        CriteriaQuery<T> criteriaQuery = entityManager.getCriteriaBuilder().createQuery((entityClass));
        Root<T> tRoot = criteriaQuery.from(entityClass);
        criteriaQuery.select(tRoot);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Transactional
    public void deleteEntity(T entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    @Override
    public List<T> pagination(Integer chainIndex, Integer size) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> from = criteriaQuery.from(entityClass);
        CriteriaQuery<T> select = criteriaQuery.select(from);
        TypedQuery<T> typedQuery = entityManager.createQuery(select);
        typedQuery.setFirstResult(chainIndex*size-size);
        typedQuery.setMaxResults(size);
        List<T> list = typedQuery.getResultList();
        return list;
    }
}