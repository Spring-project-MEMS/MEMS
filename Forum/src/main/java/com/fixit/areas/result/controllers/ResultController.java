package com.fixit.areas.result.controllers;

import com.fixit.abstractions.controller.BaseController;
import com.fixit.areas.result.models.service.ResultServiceModel;
import com.fixit.areas.result.services.ResultService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/results")
public class ResultController extends BaseController {
    public ResultService resultService;
    public ModelMapper modelMapper;


    @Autowired
    public ResultController(ResultService resultService,ModelMapper modelMapper){
        this.resultService=resultService;
        this.modelMapper=modelMapper;
    }

    @GetMapping("/{patientName}")
    public ModelAndView getResultsByPatient(@PathVariable String patientName){

        ResultServiceModel resultServiceModel=this.resultService.findByPatient(patientName);
        return super.view("views/results/results-by-ward",resultServiceModel);
    }
}
