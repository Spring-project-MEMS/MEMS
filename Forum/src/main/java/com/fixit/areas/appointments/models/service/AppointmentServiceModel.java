package com.fixit.areas.appointments.models.service;

import com.fixit.areas.ward.models.service.WardServiceModel;

import java.util.Date;

public class AppointmentServiceModel {

    private Long id;
    private Date date;

    private WardServiceModel ward;

    // TODO:
    // will add these field once the service models are done
    /*
    private UserServiceModel patient;
    private ExaminationServiceModel examinations;
    */

    public AppointmentServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public WardServiceModel getWard() {
        return ward;
    }

    public void setWard(WardServiceModel ward) {
        this.ward = ward;
    }
}
