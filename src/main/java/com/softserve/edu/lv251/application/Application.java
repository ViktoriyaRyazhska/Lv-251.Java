package com.softserve.edu.lv251.application;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Shmidt on 11.07.2017.
 */
public class Application {
    private static final EntityManager entityManager;
    private static final EntityManagerFactory entityManagerFactory;

    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("clinics_unit");
            entityManager = entityManagerFactory.createEntityManager();
        }
        catch (Throwable ex) {
            throw  new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    private static void closeAll() {
        if (entityManager != null) entityManager.close();
        if (entityManagerFactory != null) entityManagerFactory.close();
    }

    public static void main(String[] args) {
        EntityManager entityManager = getEntityManager();
        closeAll();
    }
}
