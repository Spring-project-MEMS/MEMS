package com.fixit.areas.result.controllers;

import com.fixit.abstractions.controller.BaseController;
import com.fixit.areas.result.models.service.ResultBloodServiceModel;
import com.fixit.areas.result.models.service.ResultIrmServiceModel;
import com.fixit.areas.result.models.view.ResultBloodViewModel;
import com.fixit.areas.result.models.view.ResultIrmViewModel;
import com.fixit.areas.result.models.view.ResultViewModel;
import com.fixit.areas.result.services.ResultBloodService;
import com.fixit.areas.result.services.ResultIrmService;
import com.fixit.areas.role.entities.Role;
import com.fixit.areas.users.entities.Users;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;


@Controller
@RequestMapping("/results")
public class ResultController extends BaseController {

    public ResultIrmService resultIrmService;
    public ResultBloodService resultBloodService;
    public ModelMapper modelMapper;

    @Autowired
    public ResultController( ResultIrmService resultIrmService,ResultBloodService resultBloodService, ModelMapper modelMapper) {
        this.resultIrmService = resultIrmService;
        this.resultBloodService = resultBloodService;
        this.modelMapper = modelMapper;

        ResultBloodServiceModel resultBloodServiceModel=new ResultBloodServiceModel();
        resultBloodServiceModel.setAlt(23.3);
        resultBloodServiceModel.setAst(45.2);
        resultBloodServiceModel.setHct(98.3);
        resultBloodServiceModel.setHb(94.3);
        resultBloodServiceModel.setMcv(33.33);
        resultBloodServiceModel.setPlt(222.2);
        resultBloodServiceModel.setSue(5);
        resultBloodServiceModel.setWbc(853.3);
        resultBloodServiceModel.setRbc(54);
        resultBloodServiceModel.setDate("13/10/2019");
        resultBloodServiceModel.setTime("20:00");
        resultBloodServiceModel.setDescription("some description");
        resultBloodService.createResultBlood(resultBloodServiceModel);

        ResultIrmServiceModel resultIrmServiceModel=new ResultIrmServiceModel();
        resultIrmServiceModel.setImg("/data/img");
        resultIrmServiceModel.setDate("12/13/2019");
        resultIrmServiceModel.setTime("15:14");
        resultBloodServiceModel.setDescription("this is description");
        resultIrmService.createResultIrm(resultIrmServiceModel);

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
        //if is not logged in
        if(auth==null ){
                this.resultIrmService.findAllResultsIrm().forEach(resultIrmServiceModel -> {
                    ResultIrmViewModel resultIrmViewModel = this.modelMapper.map(resultIrmServiceModel, ResultIrmViewModel.class);
                    resultViewModels.add(resultIrmViewModel);
                });

                this.resultBloodService.findAllResultsBlood().forEach(resultBloodServiceModel -> {
                    ResultBloodViewModel resultBloodViewModel = this.modelMapper.map(resultBloodServiceModel, ResultBloodViewModel.class);
                    resultViewModels.add(resultBloodViewModel);
                });
                return super.view("views/results/all-results",resultViewModels);


        }
        else {
            Users user = (Users) auth.getPrincipal();

            Set<Role> authorities = user.getAuthorities();
            Role role = authorities.iterator().next();


            if (role.getAuthority().equals("PATIENT")) {
                this.resultIrmService.findByPatient(user).forEach(resultIrmServiceModel -> {
                    ResultIrmViewModel resultIrmViewModel = this.modelMapper.map(resultIrmServiceModel, ResultIrmViewModel.class);
                    resultViewModels.add(resultIrmViewModel);
                });

                this.resultBloodService.findByPatient(user).forEach(resultBloodServiceModel -> {
                    ResultBloodViewModel resultBloodViewModel = this.modelMapper.map(resultBloodServiceModel, ResultBloodViewModel.class);
                    resultViewModels.add(resultBloodViewModel);
                });
            } else if (role.getAuthority().equals("DOCTOR")) {
                if (user.getWard().getWardName().equals("Blood")) {
                    this.resultBloodService.findAllResultsBlood().forEach(resultBloodServiceModel -> {
                        ResultBloodViewModel resultBloodViewModel = this.modelMapper.map(resultBloodServiceModel, ResultBloodViewModel.class);
                        resultViewModels.add(resultBloodViewModel);
                    });

                } else if (user.getWard().getWardName().equals("Irm")) {
                    this.resultIrmService.findAllResultsIrm().forEach(resultIrmServiceModel -> {
                        ResultIrmViewModel resultIrmViewModel = this.modelMapper.map(resultIrmServiceModel, ResultIrmViewModel.class);
                        resultViewModels.add(resultIrmViewModel);
                    });
                }
            } else {
                this.resultIrmService.findAllResultsIrm().forEach(resultIrmServiceModel -> {
                    ResultIrmViewModel resultIrmViewModel = this.modelMapper.map(resultIrmServiceModel, ResultIrmViewModel.class);
                    resultViewModels.add(resultIrmViewModel);
                });

                this.resultBloodService.findAllResultsBlood().forEach(resultBloodServiceModel -> {
                    ResultBloodViewModel resultBloodViewModel = this.modelMapper.map(resultBloodServiceModel, ResultBloodViewModel.class);
                    resultViewModels.add(resultBloodViewModel);
                });

            }
        }
        return super.view("views/results/all-results",resultViewModels);
    }

}
