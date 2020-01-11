package com.fixit.areas.result.models.service;

import java.util.Date;

public class ResultServiceModel {

    private Long id;
    private String description;
    private Date date;

    public ResultServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
