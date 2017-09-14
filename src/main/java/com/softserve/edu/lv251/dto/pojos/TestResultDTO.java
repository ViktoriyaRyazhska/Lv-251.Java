package com.softserve.edu.lv251.dto.pojos;

import java.util.Date;

/**
 * Created by Marian Brynetskyi on 14.09.2017.
 */
public class TestResultDTO {
    private long id;
    private Date startDdate;
    private Date endDdate;
    private String description;
    private String test;

    public TestResultDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}
