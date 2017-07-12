package com.softserve.edu.lv251.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 */
@Entity
public class Tests extends BaseEntity{

    @Column
    private String name;

    public Tests() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
