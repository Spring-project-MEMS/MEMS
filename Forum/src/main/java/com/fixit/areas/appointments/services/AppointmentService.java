package com.fixit.areas.appointments.services;

import com.fixit.areas.appointments.models.service.AppointmentServiceModel;
import com.fixit.areas.ward.entities.Ward;

import java.util.Set;

public interface AppointmentService {
    void create(AppointmentServiceModel appointmentServiceModel);

    Set<AppointmentServiceModel> findAllByDateAndWard(String date, Ward ward);
}
