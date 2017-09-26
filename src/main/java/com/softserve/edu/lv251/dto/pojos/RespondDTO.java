package com.softserve.edu.lv251.dto.pojos;

import java.util.Date;

/**
 * Created by Marian Brynetskyi on 28.08.2017.
 */
public class RespondDTO {

    private String userFullName;

    private long doctorId;

    private short raiting;

    private String date;

    private String description;

    public RespondDTO() {
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    public short getRaiting() {
        return raiting;
    }

    public void setRaiting(short raiting) {
        this.raiting = raiting;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
