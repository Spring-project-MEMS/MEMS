package com.fixit.areas.result.models.binding;


import com.fixit.constants.Constants;

import javax.validation.constraints.Size;

public class ResultBindingModel {

    @Size(max = 1000, message = Constants.RESULT_DESCRIPTION_LENGTH)
    private String description;

    private String date;
    private String time;




    public Long getWardId() {
        return wardId;
    }

    public void setWardId(Long wardId) {
        this.wardId = wardId;
    }

    private Long wardId;
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
