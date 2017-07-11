package com.softserve.edu.lv251.dao;

/**
 * Created by ace on 07/07/2017.
 */
public class FactoryDAO {
    /*private AllEntityClasesDAO aecDAO = null;*/

    private static FactoryDAO factoryDaoInstance = null;

    FactoryDAO() {
        /*AllEntityClasesDAO = new AllEntityClasesDAO();*/
    }

    public static synchronized FactoryDAO getFDInstance() {
        if (factoryDaoInstance == null) {
            factoryDaoInstance = new FactoryDAO();
        }
        return factoryDaoInstance;
    }

    /*getters*/
}
