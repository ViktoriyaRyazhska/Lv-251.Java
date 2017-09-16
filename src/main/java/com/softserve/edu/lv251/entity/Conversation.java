package com.softserve.edu.lv251.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 *
 */

@Entity
public class Conversation extends BaseEntity{

    private String name;
    private Date date;
    @OneToMany(mappedBy = "conversation")
    private List<MessageRecipient> messageRecipients;

    @ManyToMany
    @JoinTable(
            name = "conversation_user",
            inverseJoinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            joinColumns = @JoinColumn(
                    name = "conversation_id", referencedColumnName = "id"),
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private List<User> users;

    public Conversation() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
