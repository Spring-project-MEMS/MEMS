package com.fixit.areas.ward.services;

import com.fixit.areas.appointments.models.binding.AppointmentBindingModel;
import com.fixit.areas.ward.models.service.WardServiceModel;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.Set;

public interface WardService {

    void create(WardServiceModel wardServiceModel);

    WardServiceModel findByWardName(String wardName);

    Set<WardServiceModel> findAll();

    Set<String> findAllWardNames();

    WardServiceModel findById(Long id);

    void makeAppointment(Date date, String wardName, Authentication authentication);
}
