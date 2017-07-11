package com.softserve.edu.lv251.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    private long id;

    public BaseEntity(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
