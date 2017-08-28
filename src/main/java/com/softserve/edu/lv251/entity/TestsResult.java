package com.softserve.edu.lv251.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by Taras on 11.07.2017.
 */
@Entity
public class TestsResult extends BaseEntity {

    private Date date;
    private String description;

    @ManyToOne
    private Test test;

    @ManyToOne
    private User user;

    public TestsResult() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
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
