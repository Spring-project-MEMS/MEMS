package com.fixit.areas.appointments.models.binding;

import java.util.Date;

public class AppointmentBindingModel {

    //TODO:
    // see if there are needed more field in this model
    // depends on the functionality from the FE
    private Date date;

    public AppointmentBindingModel() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
