package com.fixit.areas.examintaion.models.view;

import com.fixit.areas.appointments.models.view.AppointmentViewModel;
import com.fixit.areas.users.models.view.UsersViewModel;
import com.fixit.areas.ward.models.view.WardViewModel;

public class ExaminationViewModel {
    private Long id;
    private String status;
    private WardViewModel ward;
    private UsersViewModel patient;

    // probably this field is redundant
    private AppointmentViewModel appointment;

    //TODO:
    //add this field once view model for result is ready
    //private ResultViewModel result;

    private String date;
    private String time;
}
