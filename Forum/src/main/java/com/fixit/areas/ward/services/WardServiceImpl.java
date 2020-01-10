package com.fixit.areas.ward.services;

import com.fixit.areas.appointments.entities.Appointment;
import com.fixit.areas.appointments.models.binding.AppointmentBindingModel;
import com.fixit.areas.appointments.models.service.AppointmentServiceModel;
import com.fixit.areas.appointments.repositories.AppointmentRepository;
import com.fixit.areas.appointments.services.AppointmentService;
import com.fixit.areas.users.models.service.UsersServiceModel;
import com.fixit.areas.users.services.UsersService;
import com.fixit.areas.ward.entities.Ward;
import com.fixit.areas.ward.models.service.WardFreeAppointmentsServiceModel;
import com.fixit.areas.ward.models.service.WardServiceModel;
import com.fixit.areas.ward.repository.WardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class WardServiceImpl implements WardService{

    private final WardRepository wardRepository;

    private final AppointmentService appointmentService;

    private final UsersService usersService;

    private final ModelMapper modelMapper;

    @Autowired
    public WardServiceImpl(WardRepository wardRepository, AppointmentService appointmentService, @Lazy UsersService usersService, ModelMapper modelMapper) {

        this.wardRepository = wardRepository;
        this.appointmentService = appointmentService;
        this.usersService = usersService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(WardServiceModel wardServiceModel) {

        Ward ward = this.modelMapper.map(wardServiceModel, Ward.class);
        this.wardRepository.save(ward);
    }

    @Override
    public WardServiceModel findByWardName(String wardName) {

        Ward ward = this.wardRepository.findByWardName(wardName);
        WardServiceModel wardServiceModel = this.modelMapper.map(ward, WardServiceModel.class);
        return wardServiceModel;
    }

    @Override
    public WardFreeAppointmentsServiceModel findByAppointmentDate(String wardName, String date) {

        String currentDateString = LocalDate.now().toString();

        Ward ward = this.wardRepository.findByWardName(wardName);
        Set<AppointmentServiceModel> appointmentServiceModels = this.appointmentService.findAllByDateAndWard(date, ward);

        //Set<Appointment> appointments = ward.getAppointments();
        Set<String> appointmentsForDate = new HashSet<>();

        /*
        for(Appointment appointment : appointments){
            if(appointment.getDate().equals(date)){
                appointmentsForDate.add(appointment.getTime());
            }
        }
        */
        for(AppointmentServiceModel appointmentServiceModel : appointmentServiceModels){
            appointmentsForDate.add(appointmentServiceModel.getTime());
        }

        List<String> freeAppointments = new ArrayList<String>();

        for(int counter = 6; counter <= 22; counter++){
            String currentTime = counter < 10 ? "0" + counter + ":00" : counter + ":00";
            if(!appointmentsForDate.contains(currentTime)){
                freeAppointments.add(currentTime);
            }
        }

        Collections.sort(freeAppointments);

        WardFreeAppointmentsServiceModel wardFreeAppointmentsServiceModel = this.modelMapper.map(ward, WardFreeAppointmentsServiceModel.class);
        wardFreeAppointmentsServiceModel.setAppointments(freeAppointments);
        wardFreeAppointmentsServiceModel.setDate(date);

        return wardFreeAppointmentsServiceModel;
    }

    @Override
    public Set<WardServiceModel> findAll() {

        Set<WardServiceModel> wardServiceModels = new HashSet<>();

        this.wardRepository.findAll().forEach(ward -> {
            WardServiceModel wardServiceModel = this.modelMapper.map(ward, WardServiceModel.class);
            wardServiceModels.add(wardServiceModel);
        });

        return wardServiceModels;
    }

    @Override
    public Set<String> findAllWardNames() {

        return this.wardRepository.findAllWardNames();
    }

    @Override
    public WardServiceModel findById(Long id) {

        Ward ward = this.wardRepository.findById(id).orElse(null);
        WardServiceModel wardServiceModel = this.modelMapper.map(ward, WardServiceModel.class);

        return wardServiceModel;
    }

    @Override
    public void makeAppointment(AppointmentBindingModel appointmentBindingModel, String wardName, Authentication authentication) {

        AppointmentServiceModel appointmentServiceModel = this.modelMapper.map(appointmentBindingModel, AppointmentServiceModel.class);
        WardServiceModel wardServiceModel = this.findByWardName(wardName);
        appointmentServiceModel.setWard(wardServiceModel);
        UsersServiceModel usersServiceModel = this.usersService.findByUsername(authentication.getName());
        appointmentServiceModel.setPatient(usersServiceModel);

        this.appointmentService.create(appointmentServiceModel);
    }
}
