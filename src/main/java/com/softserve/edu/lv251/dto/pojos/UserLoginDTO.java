package com.softserve.edu.lv251.dto.pojos;

/**
 * Created by Yana Martynyak on 01.09.2017.
 */
public class UserLoginDTO {
    private String email;
    private String password;

    public UserLoginDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
