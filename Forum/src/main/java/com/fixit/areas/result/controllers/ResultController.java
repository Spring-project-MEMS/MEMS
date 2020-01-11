package com.fixit.areas.result.controllers;

import com.fixit.abstractions.controller.BaseController;
import com.fixit.areas.result.entities.ResultBlood;
import com.fixit.areas.result.models.service.ResultBloodServiceModel;
import com.fixit.areas.result.models.service.ResultIrmServiceModel;
import com.fixit.areas.result.models.service.ResultServiceModel;
import com.fixit.areas.result.models.view.ResultBloodViewModel;
import com.fixit.areas.result.models.view.ResultIrmViewModel;
import com.fixit.areas.result.models.view.ResultViewModel;
import com.fixit.areas.result.services.ResultBloodService;
import com.fixit.areas.result.services.ResultIrmService;
import com.fixit.areas.result.services.ResultService;
import com.fixit.areas.ward.models.view.WardViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    }

    @GetMapping("")
    public void addData()
    {
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
        resultBloodServiceModel.setDate("12/12/2019");
        resultBloodServiceModel.setTime("12:13");
        resultBloodService.createResultBlood(resultBloodServiceModel);

        ResultIrmServiceModel resultIrmServiceModel=new ResultIrmServiceModel();
        resultIrmServiceModel.setImg("/data/img");
        resultIrmServiceModel.setDate("12/13/2019");
        resultIrmServiceModel.setTime("15:14");
        resultIrmService.createResultIrm(resultIrmServiceModel);
    }

    @GetMapping("/results")
    public ModelAndView allResults(){
        Set<ResultBloodViewModel> resultBloodViewModels = new TreeSet<>();
        Set<ResultIrmViewModel>   resultIrmViewModels=new TreeSet<>();
        this.resultBloodService.findAllResultsBlood().forEach(resultBloodServiceModel -> {
            ResultBloodViewModel resultBloodViewModel=this.modelMapper.map(resultBloodServiceModel,ResultBloodViewModel.class);
            resultBloodViewModels.add(resultBloodViewModel);
        });
        return super.view("views/resultBlood/all",resultBloodViewModels);
    }
}
