package com.softserve.edu.lv251.entity;

import javax.persistence.Entity;

/**
 *
 */
@Entity
public class Clinics extends BaseEntity {
    private String clinic_name;
    private long id_contact;

    public Clinics() {
    }

    public String getClinic_name() {
        return clinic_name;
    }

    public void setClinic_name(String clinic_name) {
        this.clinic_name = clinic_name;
    }

    public long getId_contact() {
        return id_contact;
    }

    public void setId_contact(long id_contact) {
        this.id_contact = id_contact;
    }
}
