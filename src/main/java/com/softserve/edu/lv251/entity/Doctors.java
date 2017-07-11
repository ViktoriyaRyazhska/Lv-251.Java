package com.softserve.edu.lv251.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by User on 11.07.2017.
 */
@Entity
public class Doctors extends BaseEntity{
    private long id_user;
    @Column(length = 10000)
    private String description;

    public Doctors() {
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
