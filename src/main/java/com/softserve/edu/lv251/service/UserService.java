package com.softserve.edu.lv251.service;


import com.softserve.edu.lv251.dto.pojos.UserDTO;
import com.softserve.edu.lv251.entity.Users;
import com.softserve.edu.lv251.exceptions.EmailExistsException;

import java.util.List;

/**
 * Created by Taras on 14.07.2017.
 */
public interface UserService {

    void addUser (Users user);

    void updateUser(Users user);

    Users getUserByID(Long entityId);

    List<Users> getUsersByColumnNameAndValue(String columnName, Object value);

    List<Users> getAllUsers();

    void deleteUser(Users user);

    List<Users> getWithOffsetOrderedByName(int offset, int limit);

    Users getFirst();

    Users findByEmail(String email);

    Users registerNewUserAccount(UserDTO accountDto) throws EmailExistsException;

}
