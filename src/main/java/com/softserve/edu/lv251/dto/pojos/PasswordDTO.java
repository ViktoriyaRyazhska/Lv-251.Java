package com.softserve.edu.lv251.dto.pojos;

import com.softserve.edu.lv251.customannotations.PasswordMatches;

/**
 * Created by Kovalevskyy Vitaliy on 10.08.2017.
 */
@PasswordMatches
public class PasswordDTO extends UserDTO{
    public PasswordDTO(){
        super.setEmail("test@mail.com");
        super.setFirstName("name");
        super.setLastName("lastname");
    }
}
