package com.softserve.edu.lv251.dto.pojos;

import java.util.Date;

/**
 * Created by Yana Martynyak on 01.09.2017.
 */
public class AppointmentsInfoDTO {
    private long id;
    private Date date;
    private String doctorName;
    private String doctorLastName;
    private String specialization;
    private boolean status;
    private String description;
    private String doctorPhoto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public AppointmentsInfoDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDoctorPhoto() {
        return doctorPhoto;
    }

    public void setDoctorPhoto(String doctorPhoto) {
        this.doctorPhoto = doctorPhoto;
    }

    public AppointmentsInfoDTO(long id, Date date, String doctorName, String doctorLastName, String specialization, boolean status) {
        this.id = id;
        this.date = date;
        this.doctorName = doctorName;
        this.doctorLastName = doctorLastName;
        this.specialization = specialization;
        this.status = status;
    }
}
