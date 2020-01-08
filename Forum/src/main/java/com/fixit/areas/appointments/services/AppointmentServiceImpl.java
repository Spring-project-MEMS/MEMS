package com.fixit.areas.appointments.services;

import com.fixit.areas.appointments.entities.Appointment;
import com.fixit.areas.appointments.models.service.AppointmentServiceModel;
import com.fixit.areas.appointments.repositories.AppointmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    private final AppointmentRepository appointmentRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, ModelMapper modelMapper) {

        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(AppointmentServiceModel appointmentServiceModel) {
        Appointment appointment = this.modelMapper.map(appointmentServiceModel, Appointment.class);
        this.appointmentRepository.save(appointment);
    }

    @Override
    public Set<AppointmentServiceModel> findAllByDateAndWardName(String date, String wardName) {

        Set<AppointmentServiceModel> appointmentServiceModels = new HashSet<>();
        this.appointmentRepository.findAllByDateAndWardContains(date, wardName).forEach(appointment -> {
            AppointmentServiceModel appointmentServiceModel = this.modelMapper.map(appointment, AppointmentServiceModel.class);
            appointmentServiceModels.add(appointmentServiceModel);
        });

        return appointmentServiceModels;
    }
}
