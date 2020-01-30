package com.fixit.areas.examintaion.controllers;

import com.fixit.abstractions.controller.BaseController;
import com.fixit.areas.examintaion.enitities.Examination;
import com.fixit.areas.examintaion.models.service.ExaminationServiceModel;
import com.fixit.areas.examintaion.models.view.ExaminationViewModel;
import com.fixit.areas.examintaion.services.ExaminationService;
import com.fixit.areas.users.services.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/examinations")
public class ExaminationController extends BaseController {

    private final ExaminationService examinationService;

    private final ModelMapper modelMapper;

    @Autowired
    public ExaminationController(ExaminationService examinationService, ModelMapper modelMapper) {
        this.examinationService = examinationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    public ModelAndView allStatus(){

        return super.view("views/examinations/all-status");
    }

    @GetMapping("/open")
    public ModelAndView allOpenExamination(@PageableDefault(size = 10) Pageable pageable){

        Page<ExaminationServiceModel> examinationServiceModels = this.examinationService.findAllOpen(pageable);

        List<ExaminationViewModel> examinationViewModelList = new ArrayList<>();

        examinationServiceModels.forEach(examinationServiceModel -> {
            ExaminationViewModel examinationViewModel = this.modelMapper.map(examinationServiceModel, ExaminationViewModel.class);
            examinationViewModelList.add(examinationViewModel);
        });

        Page<ExaminationViewModel> examinationViewModelPages = new PageImpl<>(examinationViewModelList, pageable, examinationServiceModels.getTotalElements());

        return super.view("views/examinations/open", examinationViewModelPages);
    }

    @PostMapping("/open/{id}")
    public ModelAndView changeStatusOpen (@PathVariable Long id){
        this.examinationService.changeOpen(id);
        return super.redirect("/examinations/open");
    }

    @GetMapping("/processed")
    public ModelAndView allProcessedExamination(@PageableDefault(size = 10) Pageable pageable){

        Page<ExaminationServiceModel> examinationServiceModels = this.examinationService.findAllProcessed(pageable);

        List<ExaminationViewModel> examinationViewModelList = new ArrayList<>();

        examinationServiceModels.forEach(examinationServiceModel -> {
            ExaminationViewModel examinationViewModel = this.modelMapper.map(examinationServiceModel, ExaminationViewModel.class);
            examinationViewModelList.add(examinationViewModel);
        });

        Page<ExaminationViewModel> examinationViewModelPages = new PageImpl<>(examinationViewModelList, pageable, examinationServiceModels.getTotalElements());

        return super.view("views/examinations/processed", examinationViewModelPages);
    }

    @PostMapping("/processed/{id}")
    public ModelAndView changeStatusProcessed (@PathVariable Long id){
        this.examinationService.changeProcessed(id);
        return super.redirect("/examinations/processed");
    }

    @GetMapping("/pending")
    public ModelAndView allPendingExamination(@PageableDefault(size = 10) Pageable pageable){

        Page<ExaminationServiceModel> examinationServiceModels = this.examinationService.findAllPending(pageable);

        List<ExaminationViewModel> examinationViewModelList = new ArrayList<>();

        examinationServiceModels.forEach(examinationServiceModel -> {
            ExaminationViewModel examinationViewModel = this.modelMapper.map(examinationServiceModel, ExaminationViewModel.class);
            examinationViewModelList.add(examinationViewModel);
        });

        Page<ExaminationViewModel> examinationViewModelPages = new PageImpl<>(examinationViewModelList, pageable, examinationServiceModels.getTotalElements());

        return super.view("views/examinations/pending", examinationViewModelPages);
    }

    @PostMapping("/pending/{id}")
    public ModelAndView changeStatusPending (@PathVariable Long id){
        this.examinationService.changePending(id);
        return super.redirect("/examinations/pending");
    }

    @GetMapping("/closed")
    public ModelAndView allClosedExamination(@PageableDefault(size = 10) Pageable pageable){

        Page<ExaminationServiceModel> examinationServiceModels = this.examinationService.findAllClosed(pageable);

        List<ExaminationViewModel> examinationViewModelList = new ArrayList<>();

        examinationServiceModels.forEach(examinationServiceModel -> {
            ExaminationViewModel examinationViewModel = this.modelMapper.map(examinationServiceModel, ExaminationViewModel.class);
            examinationViewModelList.add(examinationViewModel);
        });

        Page<ExaminationViewModel> examinationViewModelPages = new PageImpl<>(examinationViewModelList, pageable, examinationServiceModels.getTotalElements());

        return super.view("views/examinations/closed", examinationViewModelPages);
    }

    @PostMapping("/closed/{id}")
    public ModelAndView viewClosed (@PathVariable Long id){
        this.examinationService.viewClosed(id);
        return super.redirect("/examinations/closed");
    }
}
