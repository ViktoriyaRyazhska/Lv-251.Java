package com.softserve.edu.lv251.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by Taras on 11.07.2017.
 */
@Entity
public class TestsResult extends BaseEntity {

    private long id;
    private Date startDdate;
    private Date endDdate;
    private String description;

    @ManyToOne
    private Test test;

    @ManyToOne
    private User user;

    public TestsResult() {
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
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

    public Date getStartDdate() {
        return startDdate;
    }

    public void setStartDdate(Date startDdate) {
        this.startDdate = startDdate;
    }

    public Date getEndDdate() {
        return endDdate;
    }

    public void setEndDdate(Date endDdate) {
        this.endDdate = endDdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
