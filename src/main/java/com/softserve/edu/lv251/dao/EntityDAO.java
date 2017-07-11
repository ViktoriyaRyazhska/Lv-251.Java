package com.softserve.edu.lv251.dao;

import java.util.List;

/**
 * Created by ace on 07/06/2017.
 */
public interface EntityDAO<T> {
    void addEntity(T entity);

    void updateEntity(T entity);

    T getEntityByID(Integer entityId);

    List<T> getEntitiesByColumnNameAndValue(String columnName, Object value);

    List<T> getAllEntities();

    void deleteEntity(T entity);
}
