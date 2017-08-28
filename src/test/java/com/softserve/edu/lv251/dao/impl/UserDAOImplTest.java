package com.softserve.edu.lv251.dao.impl;

import com.softserve.edu.lv251.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Kovalevskyy Vitaliy on 14.08.2017.
 */

public class UserDAOImplTest {
    @Mock
    private EntityManager entityManager;
    @Mock
    Query query;
    @Spy
    List<User> userList = new ArrayList<>();

    @Before
    public void beforeTest() {
        MockitoAnnotations.initMocks(this);
        User user = new User();
        user.setId(2);
        user.setFirstname("Pasha");
        user.setLastname("Pashenko");
        userList.add(user);
    }

    @Test
    public void searchByLetters() throws Exception {
        when(entityManager.createQuery("")).thenReturn(query);
        when(query.getResultList()).thenReturn(userList);
        assertEquals(true, ((User) query.getResultList().get(0)).getFirstname().contains("P"));
    }

    @Test
    public void addEntity() throws Exception {
    }

    @Test
    public void updateEntity() throws Exception {
    }

    @Test
    public void getEntityByID() throws Exception {
        when(entityManager.createQuery("")).thenReturn(query);
        when(query.getResultList()).thenReturn(userList);
        assertEquals(2, ((User) query.getResultList().get(0)).getId());
    }

    @Test
    public void getEntitiesByColumnNameAndValue() throws Exception {
    }

    @Test
    public void getAllEntities() throws Exception {
    }

    @Test
    public void deleteEntity() throws Exception {
    }

    @Test
    public void pagination() throws Exception {
    }

}