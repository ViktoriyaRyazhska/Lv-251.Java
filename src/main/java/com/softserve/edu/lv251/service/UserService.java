package com.softserve.edu.lv251.service;


import com.softserve.edu.lv251.controllers.DoctorCabinetController;
import com.softserve.edu.lv251.dto.pojos.DoctorCabinetUser;
import com.softserve.edu.lv251.dto.pojos.PasswordDTO;
import com.softserve.edu.lv251.dto.pojos.UserDTO;
import com.softserve.edu.lv251.dto.pojos.UserUpdate;
import com.softserve.edu.lv251.entity.User;
import com.softserve.edu.lv251.entity.VerificationToken;
import com.softserve.edu.lv251.exceptions.EmailExistsException;

import java.util.List;

/**
 * Created by Taras on 14.07.2017.
 */
public interface UserService {

    void addUser(User user);

    void updateUser(User user);

    User getUserByID(Long userId);

    List<User> getUsersByColumnNameAndValue(String columnName, Object value);

    List<User> getAllUsers();

    void deleteUser(User user);

    List<User> getWithOffsetOrderedByName(int offset, int limit);

    User getFirst();

    User findByEmail(String email);

    User getUserByVerificationToken(String verificationToken);

    void createVerificationToken(User user, String verificationToken);

    VerificationToken getVerificationToken(String verificationToken);

    User registerNewUserAccount(UserDTO accountDto);

    void sendEmail(User user, String messageText);

    List<DoctorCabinetUser> searchByLetters(String search);

    User changePassword(User user, PasswordDTO passwordDTO);
    UserUpdate getByEmail(String email);
}