package com.softserve.edu.lv251.service.impl;

import com.softserve.edu.lv251.config.Mapper;
import com.softserve.edu.lv251.dao.UserDAO;
import com.softserve.edu.lv251.dto.pojos.DoctorCabinetUser;
import com.softserve.edu.lv251.dto.pojos.PasswordDTO;
import com.softserve.edu.lv251.dto.pojos.UserDTO;
import com.softserve.edu.lv251.dto.pojos.UserUpdate;
import com.softserve.edu.lv251.entity.Contact;
import com.softserve.edu.lv251.entity.User;
import com.softserve.edu.lv251.entity.VerificationToken;
import com.softserve.edu.lv251.idl.WebRoles;
import com.softserve.edu.lv251.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Added by Pavlo Kuchereshko.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private ContactsService contactsService;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private Mapper mapper;

    @Autowired
    private MailService mailService;

    @Override
    public void addUser(User user) {
        this.userDAO.addEntity(user);
    }

    @Override
    public void updateUser(User user) {
        this.userDAO.updateEntity(user);
    }

    @Override
    public User getUserByID(Long userId) {
        return this.userDAO.getEntityByID(userId);
    }

    @Override
    public List<User> getUsersByColumnNameAndValue(String columnName, Object value) {
        return this.userDAO.getEntitiesByColumnNameAndValue(columnName, value);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userDAO.getAllEntities();
    }

    @Override
    public void deleteUser(User user) {
        this.userDAO.deleteEntity(user);
    }

    public User getFirst() {
        return this.userDAO.getEntityByID(1L);
    }

    @Override
    public User findByEmail(String email) {
        List<User> users = getUsersByColumnNameAndValue("email", email);
        return users.isEmpty() ? null : users.get(0);
    }

    public List<User> getWithOffsetOrderedByName(int offset, int limit) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> from = criteriaQuery.from(User.class);
        CriteriaQuery<User> select = criteriaQuery.select(from);
        TypedQuery<User> typedQuery = entityManager.createQuery(select);
        typedQuery.setFirstResult(offset);
        typedQuery.setMaxResults(limit);

        return typedQuery.getResultList();
    }

    @Transactional
    @Override
    public User registerNewUserAccount(UserDTO accountDto) {

        User user = new User();
        mapper.map(accountDto, user);
        user.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));
        user.setEnabled(false);
        user.setPhoto(StoredImagesService.getDefaultPictureBase64encoded("User_Default.png"));
        user.setRoles(Collections.singletonList(rolesService.findByName(WebRoles.ROLE_USER.name())));
        Contact contact = new Contact();
        contact.setUser(user);
        contact.setEmail(accountDto.getEmail());
        this.contactsService.addContacts(contact);
        user.setContact(contact);
        addUser(user);

        return user;
    }

    @Override
    public void sendEmail(User user, String messageText) {
        this.mailService.sendEmail(user, messageText);
    }

    @Override
    public List<DoctorCabinetUser> searchByLetters(String search) {
        List<DoctorCabinetUser> userList = new LinkedList<>();
        userDAO.searchByLetters(search).forEach((i) -> userList.add(mapper.map(i, DoctorCabinetUser.class)));
        return userList;
    }

    @Override
    public User getUserByVerificationToken(String verificationToken) {
        return this.verificationTokenService.findByVerificationToken(verificationToken).getUser();
    }

    @Override
    public void createVerificationToken(User user, String verificationToken) {
        VerificationToken myVerificationToken = new VerificationToken();
        myVerificationToken.setUser(user);
        myVerificationToken.setToken(verificationToken);
        this.verificationTokenService.addVerificationToken(myVerificationToken);
    }

    @Override
    public VerificationToken getVerificationToken(String verificationToken) {
        return this.verificationTokenService.findByVerificationToken(verificationToken);
    }

    @Transactional
    @Override
    public User changePassword(User user, PasswordDTO passwordDTO) {
        user.setPassword(bCryptPasswordEncoder.encode(passwordDTO.getPassword()));
        updateUser(user);

        return user;
    }

    @Override
    public UserUpdate getByEmail(String email) {
        User user = findByEmail(email);
        UserUpdate userUpdate = new UserUpdate();
        if (user != null) {
            mapper.map(user, userUpdate);
        }
        if (user.getContact() != null) {
            mapper.map(user.getContact(), userUpdate);
        }
        return userUpdate;
    }

    @Override
    public UserUpdate getById(long id) {
        User user = userDAO.getEntityByID(id);
        UserUpdate userUpdate = new UserUpdate();
        if (user != null) {
            mapper.map(user, userUpdate);
        }
        if (user.getContact() != null) {
            mapper.map(user.getContact(), userUpdate);
        }
        return userUpdate;
    }
}
