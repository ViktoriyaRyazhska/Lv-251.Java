package com.softserve.edu.lv251.entity;

import javax.persistence.Entity;
import java.util.Date;

/**
 *
 */
@Entity
public class TestsResults extends BaseEntity {

    private long id_patient;
    private long id_doctor;
    private long id_test;
    private Date date;
    private String description;

    public TestsResults() {
    }

    public long getId_patient() {
        return id_patient;
    }

    public void setId_patient(long id_patient) {
        this.id_patient = id_patient;
    }

    public long getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(long id_doctor) {
        this.id_doctor = id_doctor;
    }

    public long getId_test() {
        return id_test;
    }

    public void setId_test(long id_test) {
        this.id_test = id_test;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}