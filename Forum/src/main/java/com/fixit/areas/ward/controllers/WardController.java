package com.fixit.areas.ward.controllers;

import com.fixit.abstractions.controller.BaseController;
import com.fixit.areas.ward.models.binding.WardBindingModel;
import com.fixit.areas.ward.models.service.WardServiceModel;
import com.fixit.areas.ward.models.view.WardNamesViewModel;
import com.fixit.areas.ward.models.view.WardViewModel;
import com.fixit.areas.ward.services.WardService;
import com.fixit.cache.DataWardCacheSingleton;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Controller
@RequestMapping("/wards")
public class WardController extends BaseController {

    private WardService wardService;

    private ModelMapper modelMapper;

    @Autowired
    public WardController(WardService wardService, ModelMapper modelMapper) {
        this.wardService = wardService;
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
    public ModelAndView getAppointmentsByWard(@PathVariable String wardName){

        WardServiceModel wardServiceModel = this.wardService.findByWardName(wardName);
        return super.view("views/wards/appointments-by-ward", wardServiceModel);
    }
}
