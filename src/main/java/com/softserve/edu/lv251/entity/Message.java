package com.softserve.edu.lv251.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * Created by Yana Martynyak on 24.08.2017.
 */
@Entity
public class Message extends BaseEntity {
    @ManyToOne
    private User from;

    private String text;
    private Date date;

    @OneToMany(mappedBy = "message")
    private List<MessageRecipient> messageRecipients;

    @ManyToOne
    private User to;

    public Message(){}

    public List<MessageRecipient> getMessageRecipients() {
        return messageRecipients;
    }

    public void setMessageRecipients(List<MessageRecipient> messageRecipients) {
        this.messageRecipients = messageRecipients;
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
