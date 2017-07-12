package com.softserve.edu.lv251.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Users extends BaseEntity {

    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private String middlename;
    @Column
    private String email;
    @Column
    private String password;

    public Users() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
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
