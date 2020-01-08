package com.fixit.areas.appointments.services;

import com.fixit.areas.appointments.models.service.AppointmentServiceModel;

import java.util.Set;

public interface AppointmentService {
    void create(AppointmentServiceModel appointmentServiceModel);

    Set<AppointmentServiceModel> findAllByDateAndWardName(String date, String wardName);
}
