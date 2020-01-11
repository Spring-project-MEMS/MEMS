package com.fixit.areas.result.models.binding;

import com.fixit.areas.result.services.ResultService;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Size;

public class ResultBindingModel {

    @Size(min=0,max=1000)
    private String description;

    private String date;
    private String time;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
