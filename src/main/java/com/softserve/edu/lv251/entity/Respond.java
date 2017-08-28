package com.softserve.edu.lv251.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by Marian Brynetskyi on 23.08.2017.
 */
@Entity
public class Respond extends BaseEntity{

    @ManyToOne
    private User user;

    @ManyToOne
    private Doctor doctor;

    private short raiting;

    private Date date;

    public Respond() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public short getRaiting() {
        return raiting;
    }

    public void setRaiting(short raiting) {
        this.raiting = raiting;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
