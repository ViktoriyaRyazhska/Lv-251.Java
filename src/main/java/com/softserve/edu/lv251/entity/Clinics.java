package com.softserve.edu.lv251.entity;

import javax.persistence.Entity;

/**
 * Created by ace on 07/11/2017.
 */
@Entity
public class Clinics extends BaseEntity {
    private long id_clinic;
    private String clinic_name;
    private long id_contact;

    public Clinics() {
    }

    public long getId_clinic() {
        return id_clinic;
    }

    public void setId_clinic(long id_clinic) {
        this.id_clinic = id_clinic;
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
