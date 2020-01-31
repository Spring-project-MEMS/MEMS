package com.fixit.areas.result.controllers;

import com.fixit.abstractions.controller.BaseController;
import com.fixit.areas.result.models.binding.ResultBloodBindingModel;
import com.fixit.areas.result.models.binding.ResultIrmBindingModel;
import com.fixit.areas.result.models.service.ResultBloodServiceModel;
import com.fixit.areas.result.models.service.ResultIrmServiceModel;
import com.fixit.areas.result.models.view.ResultBloodViewModel;
import com.fixit.areas.result.models.view.ResultIrmViewModel;
import com.fixit.areas.result.models.view.ResultViewModel;
import com.fixit.areas.result.services.ResultBloodService;
import com.fixit.areas.result.services.ResultIrmService;
import com.fixit.areas.result.services.ResultService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;


@Controller
@RequestMapping("/results")
public class ResultController extends BaseController {

    public ResultIrmService resultIrmService;
    public ResultBloodService resultBloodService;
    public ResultService resultService;
    public ModelMapper modelMapper;


    @Autowired
    public ResultController( ResultService resultService,ResultIrmService resultIrmService,ResultBloodService resultBloodService, ModelMapper modelMapper) {
        this.resultIrmService = resultIrmService;
        this.resultBloodService = resultBloodService;
        this.resultService=resultService;
        this.modelMapper = modelMapper;


    }


    @GetMapping("/create-irm-result")
    public ModelAndView register(@ModelAttribute ResultIrmBindingModel resultIrmBindingModel) {
        return super.view("views/results/createirmresult");
    }

    @PostMapping("/create-irm-result")
    public ModelAndView registerConfirm(@Valid @ModelAttribute ResultIrmBindingModel resultIrmBindingModel,
                                        BindingResult bindingResult,
                                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return super.view("views/results/createirmresult");
        }


        ResultIrmServiceModel resultIrmServiceModel = this.modelMapper.map(resultIrmBindingModel, ResultIrmServiceModel.class);
        this.resultIrmService.createResultIrm(resultIrmServiceModel);
        return super.redirect("/examinations/pending");
    }


    @GetMapping("/create-blood-result")
    public ModelAndView register(@ModelAttribute ResultBloodBindingModel resultBloodBindingModel) {
        return super.view("views/results/createbloodresult");
    }

    @PostMapping("/create-blood-result")
    public ModelAndView registerConfirm(@Valid @ModelAttribute ResultBloodBindingModel resultBloodBindingModel,
                                        BindingResult bindingResult,
                                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return super.view("views/results/createbloodresult");
        }

        ResultBloodServiceModel resultBloodServiceModel = this.modelMapper.map(resultBloodBindingModel, ResultBloodServiceModel.class);
        this.resultBloodService.createResultBlood(resultBloodServiceModel);
        return super.redirect("/examinations/pending");
    }


    @GetMapping("/resultsBlood")
    public ModelAndView resultsBlood(){
        Set<ResultBloodViewModel> resultBloodViewModels = new TreeSet<>(Comparator.comparing(ResultBloodViewModel::getId));
        this.resultBloodService.findAllResultsBlood().forEach(resultBloodServiceModel -> {
            ResultBloodViewModel resultBloodViewModel=this.modelMapper.map(resultBloodServiceModel,ResultBloodViewModel.class);
            resultBloodViewModels.add(resultBloodViewModel);
        });
        return super.view("views/results/blood-results",resultBloodViewModels);
    }

    @GetMapping("/IRM")
    public ModelAndView resultIrm(@RequestParam Long id){

        ResultIrmServiceModel resultIrmServiceModel=this.resultIrmService.findById(id);
        ResultIrmViewModel resultIrmViewModel=this.modelMapper.map(resultIrmServiceModel,ResultIrmViewModel.class);

        return super.view("views/results/irm-result", resultIrmViewModel);
    }

    @GetMapping("/BLOOD")
    public ModelAndView resultBlood(@RequestParam Long id){

        ResultBloodServiceModel resultBloodServiceModel=this.resultBloodService.findById(id);
        ResultBloodViewModel resultBloodViewModel=this.modelMapper.map(resultBloodServiceModel,ResultBloodViewModel.class);

        return super.view("views/results/blood-result", resultBloodViewModel);
    }

    @GetMapping("")
    public ModelAndView resultsAll(Authentication auth){
        Set<ResultViewModel> resultViewModels = new TreeSet<>(Comparator.comparing(ResultViewModel::getId));

        this.resultService.listResults(auth).forEach(resultServiceModel -> {
            ResultViewModel resultViewModel = this.modelMapper.
                    map(resultServiceModel, (resultServiceModel instanceof ResultIrmServiceModel)?ResultIrmViewModel.class:ResultBloodViewModel.class);
            resultViewModels.add(resultViewModel);
        });

        return super.view("views/results/all-results",resultViewModels);
    }

}
