package com.softserve.edu.lv251.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 */
@Entity
public class Roles extends BaseEntity {

    @Column
    private String name;

    public Roles() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
