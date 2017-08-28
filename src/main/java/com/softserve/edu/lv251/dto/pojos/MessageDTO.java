package com.softserve.edu.lv251.dto.pojos;

/**
 * Created by Yana Martynyak on 26.08.2017.
 */
public class MessageDTO {
    private String from;
    private String text;
    private String date;

    public MessageDTO(String from, String text, String date) {
        this.from = from;
        this.text = text;
        this.date = date;
    }

    public MessageDTO() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
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
