package com.softserve.edu.lv251.dto.pojos;

/**
 * Created by Yana Martynyak on 04.09.2017.
 */
public class MessagesDTO {
    private String name;
    private String lastname;
    private String text;
    private String date;
    private String email;



    public MessagesDTO() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
