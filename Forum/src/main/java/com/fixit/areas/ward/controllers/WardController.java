package com.fixit.areas.ward.controllers;

import com.fixit.abstractions.controller.BaseController;
import com.fixit.areas.appointments.models.binding.AppointmentBindingModel;
import com.fixit.areas.appointments.models.service.AppointmentServiceModel;
import com.fixit.areas.appointments.services.AppointmentService;
import com.fixit.areas.users.models.service.UsersServiceModel;
import com.fixit.areas.users.services.UsersService;
import com.fixit.areas.ward.models.binding.WardBindingModel;
import com.fixit.areas.ward.models.service.WardServiceModel;
import com.fixit.areas.ward.models.view.WardNamesViewModel;
import com.fixit.areas.ward.models.view.WardViewModel;
import com.fixit.areas.ward.services.WardService;
import com.fixit.cache.DataWardCacheSingleton;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.DateUtils;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

@Controller
@RequestMapping("/wards")
public class WardController extends BaseController {

    private final WardService wardService;

    private final AppointmentService appointmentService;

    private ModelMapper modelMapper;

    private UsersService usersService;

    @Autowired
    public WardController(WardService wardService, AppointmentService appointmentService, UsersService usersService, ModelMapper modelMapper) {
        this.wardService = wardService;
        this.appointmentService = appointmentService;
        this.usersService = usersService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/create")
    public ModelAndView createWard(@ModelAttribute WardBindingModel wardBindingModel){
        return super.view("/views/wards/create", "Create Ward");
    }

    @PostMapping("/create")
    public ModelAndView persistWard(@Valid @ModelAttribute WardBindingModel wardBindingModel, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return super.view("/views/wards/create", "Create Ward");
        }

        WardServiceModel wardServiceModel = this.modelMapper.map(wardBindingModel, WardServiceModel.class);
        this.wardService.create(wardServiceModel);

        DataWardCacheSingleton.getInstance().addWard(this.modelMapper.map(wardServiceModel,WardNamesViewModel.class));

        return super.redirect("/wards");
    }

    @GetMapping("")
    public ModelAndView allWards(){

        Set<WardViewModel> wardViewModels = new TreeSet<WardViewModel>(Comparator.comparing(WardViewModel::getId));

        this.wardService.findAll().forEach(wardServiceModel -> {
            WardViewModel wardViewModel = this.modelMapper.map(wardServiceModel, WardViewModel.class);
            wardViewModels.add(wardViewModel);
        });

        return super.view("views/wards/all", wardViewModels);
    }

    @GetMapping("/{wardName}")
    public ModelAndView getAppointmentsByWard(@PathVariable String wardName, @ModelAttribute AppointmentBindingModel appointmentBindingModel){

        WardServiceModel wardServiceModel = this.wardService.findByWardName(wardName);
        WardViewModel wardViewModel = this.modelMapper.map(wardServiceModel, WardViewModel.class);

        return super.view("views/wards/appointments-by-ward", wardViewModel);
    }

    @PostMapping("/{wardName}")
    public ModelAndView storeAppointment(@ModelAttribute AppointmentBindingModel appointmentBindingModel, BindingResult bindingResult, Authentication authentication,
                                         @PathVariable(value = "wardName", required = true) String wardName){
        if (bindingResult.hasErrors()){

            //TODO:
            // make this one smarter (if there are any errors)
            // can make field wardViewModel, which to sent to the template (initialize it in the @GetMapping method)

            return super.redirect("/wards/" + wardName);

            /*
            WardServiceModel wardServiceModel = this.wardService.findByWardName(wardName);
            WardViewModel wardViewModel = this.modelMapper.map(wardServiceModel, WardViewModel.class);
            return super.view("views/wards/appointments-by-ward", wardViewModel);
            */
        }

        // TODO:
        // problem with parsing the data from template to BE fields
        // PROBABLY NEED TO CHANGE THE ENTITY TO HAVE TWO STRING FIELDS FOR DATE AND TIME
        // DOES NOT WORK NOW

        String date = appointmentBindingModel.getDate();
        String time = appointmentBindingModel.getTime();

        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder localDateTimeString = stringBuilder.append(date).append("T").append(time);

        LocalDateTime localDateTime = LocalDateTime.parse(localDateTimeString.toString());

        //this.wardService.makeAppointment(localDateTime., wardName, authentication);
        return super.redirect("/wards/" + wardName);
    }
}
