package com.softserve.edu.lv251.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Created by Admin on 31.07.2017.
 */
@Entity
public class Moderator extends User {

    @OneToOne(cascade = {CascadeType.ALL})
    private Clinic clinic;

    @ManyToOne
    Admin admin;

    public Moderator() {
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;

    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
