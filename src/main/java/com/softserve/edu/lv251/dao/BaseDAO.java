package com.softserve.edu.lv251.dao;

import java.util.List;

/**
 *
 */
public interface BaseDAO<T> {

    void addEntity(T entity);

    void updateEntity(T entity);

    T getEntityByID(Long entityId);

    List<T> getEntitiesByColumnNameAndValue(String columnName, Object value);

    List<T> getAllEntities();

    void deleteEntity(T entity);

    public List<T> pagination(Integer chainIndex, Integer size);
}