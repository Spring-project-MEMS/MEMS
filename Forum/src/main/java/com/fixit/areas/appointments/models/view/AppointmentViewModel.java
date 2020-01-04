package com.fixit.areas.appointments.models.view;

import com.fixit.areas.ward.models.view.WardViewModel;

import java.util.Date;

public class AppointmentViewModel {

    private Long id;
    private Date date;

    private WardViewModel ward;

    // TODO:
    // will add these field once the view models are done
    /*
    private UserViewModel patient;
    private ExaminationViewModel examinations;
    */

    public AppointmentViewModel() {
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

    public WardViewModel getWard() {
        return ward;
    }

    public void setWard(WardViewModel ward) {
        this.ward = ward;
    }
}
