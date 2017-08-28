package com.softserve.edu.lv251.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by Yana Martynyak on 24.08.2017.
 */
@Entity
public class Message extends BaseEntity {
    @ManyToOne
    private User from;
    private String text;
    private Date date;
    @ManyToOne
    private  User to;
    public Message() {
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
