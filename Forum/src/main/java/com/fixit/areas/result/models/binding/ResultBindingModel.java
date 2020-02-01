package com.fixit.areas.result.models.binding;


import com.fixit.areas.users.entities.Users;
import com.fixit.areas.ward.entities.Ward;
import com.fixit.constants.Constants;

import javax.validation.constraints.Size;

public class ResultBindingModel {

    @Size(max = 1000, message = Constants.RESULT_DESCRIPTION_LENGTH)
    private String description;

    private String date;
    private String time;

    private Users patient;
    private Ward ward;

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public Users getPatient() {
        return patient;
    }

    public void setPatient(Users patient) {
        this.patient = patient;
    }

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
