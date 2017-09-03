package com.softserve.edu.lv251.dto.pojos;

public class DoctorCabinetUser {

    private long id;
    private String firstname;
    private String lastname;

    public DoctorCabinetUser() {
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
