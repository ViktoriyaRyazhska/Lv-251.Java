package com.softserve.edu.lv251.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by kilopo on 11.07.2017.
 */
@Entity
public class Tests extends BaseEntity{

    @Column
    String name;

    public Tests() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
