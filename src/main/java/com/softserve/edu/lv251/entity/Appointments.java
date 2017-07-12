package com.softserve.edu.lv251.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 *
 */
@Entity
public class Appointments extends BaseEntity{

    @Column
    private long id_doctor;
    @Column
    private long id_patient;
    @Column
    private Date appintmentDate;
    @Column
    private String status;
    @Column
    private double duration;

    public Appointments() {
    }

    public long getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(long id_doctor) {
        this.id_doctor = id_doctor;
    }

    public long getId_patient() {
        return id_patient;
    }

    public void setId_patient(long id_patient) {
        this.id_patient = id_patient;
    }

    public Date getAppintmentDate() {
        return appintmentDate;
    }

    public void setAppintmentDate(Date appintmentDate) {
        this.appintmentDate = appintmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}