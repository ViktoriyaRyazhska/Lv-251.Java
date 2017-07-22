package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.dao.UsersDAO;
import com.softserve.edu.lv251.dto.pojos.UserDTO;
import com.softserve.edu.lv251.entity.Users;
import com.softserve.edu.lv251.exceptions.EmailExistsException;
import com.softserve.edu.lv251.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
//import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Taras on 14.07.2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    EntityManager entityManager;

    @Override
    public void addUser(Users user) {
        this.usersDAO.addEntity(user);
    }

    @Override
    public void updateUser(Users user) {
        this.usersDAO.updateEntity(user);
    }

    @Override
    public Users getUserByID(Long userId) {
        return this.usersDAO.getEntityByID(userId);
    }

    @Override
    public List<Users> getUsersByColumnNameAndValue(String columnName, Object value) {
        return this.usersDAO.getEntitiesByColumnNameAndValue(columnName, value);
    }

    @Override
    public List<Users> getAllUsers() {
        return this.usersDAO.getAllEntities();
    }

    @Override
    public void deleteUser(Users user) {
        this.usersDAO.deleteEntity(user);
    }

    public Users getFirst() {
        return usersDAO.getEntityByID(1L);
    }

    @Override
    public Users findByEmail(String email) {
        return getUsersByColumnNameAndValue("email", email).get(0);
    }

    public List<Users> getWithOffsetOrderedByName(int offset, int limit) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Users> criteriaQuery = criteriaBuilder.createQuery(Users.class);
        Root<Users> from = criteriaQuery.from(Users.class);
        CriteriaQuery<Users> select = criteriaQuery.select(from);
        TypedQuery<Users> typedQuery = entityManager.createQuery(select);
        typedQuery.setFirstResult(offset);
        typedQuery.setMaxResults(limit);

        return typedQuery.getResultList();
    }

    @Transactional
    @Override
    public Users registerNewUserAccount(UserDTO accountDto)
            throws EmailExistsException {

        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException("There is an account with that email address: " + accountDto.getEmail());
        }
        Users user = new Users();
        user.setFirstname(accountDto.getFirstName());
        user.setLastname(accountDto.getLastName());
        user.setMiddlename("");
        user.setPassword(accountDto.getPassword());
        user.setEmail(accountDto.getEmail());
        try {
            user.setPhoto(new String(Base64.encode(Files.readAllBytes(
                    Paths.get("web/resources/main_theme/images/User_Default.png"))), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //user.setAppointments();
        //user.setRoles();
        //user.setContact();
        //user.setMedicalCards();
        //user.setTestsResults();
        this.usersDAO.addEntity(user);

        return user;
    }
    private boolean emailExist(String email) {
        return findByEmail(email) != null;
    }
}
