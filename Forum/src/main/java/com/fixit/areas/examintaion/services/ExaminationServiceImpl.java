package com.fixit.areas.examintaion.services;

import com.fixit.areas.examintaion.enitities.Examination;
import com.fixit.areas.examintaion.models.service.ExaminationServiceModel;
import com.fixit.areas.examintaion.repositories.ExaminationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminationServiceImpl implements ExaminationService{

    @Value("${examination.status.open}")
    private String statusOpen;

    @Value("${examination.status.processed}")
    private String statusProcessed;

    private final ExaminationRepository examinationRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ExaminationServiceImpl(ExaminationRepository examinationRepository, ModelMapper modelMapper){
        this.examinationRepository = examinationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(ExaminationServiceModel examinationServiceModel) {
        Examination examination = this.modelMapper.map(examinationServiceModel, Examination.class);
        this.examinationRepository.save(examination);
    }

    @Override
    public ExaminationServiceModel findById(Long id) {

        Examination examination = this.examinationRepository.findById(id).orElse(null);
        ExaminationServiceModel examinationServiceModel = this.modelMapper.map(examination, ExaminationServiceModel.class);
        return examinationServiceModel;
    }

    @Override
    public Set<ExaminationServiceModel> findAll() {

        Set<ExaminationServiceModel> examinationServiceModels = new HashSet<>();

        this.examinationRepository.findAll().forEach(examination -> {
            ExaminationServiceModel examinationServiceModel = this.modelMapper.map(examination, ExaminationServiceModel.class);
            examinationServiceModels.add(examinationServiceModel);
        });

        return examinationServiceModels;
    }

    @Override
    public Page<ExaminationServiceModel> findAllOpen(Pageable pageable) {

        Page<Examination> examinations = this.examinationRepository.findAllByStatus(statusOpen, pageable);
        List<ExaminationServiceModel> examinationServiceModelsList = new ArrayList<>();

        examinations.forEach(examination -> {
            ExaminationServiceModel examinationServiceModel = this.modelMapper.map(examination, ExaminationServiceModel.class);
            examinationServiceModelsList.add(examinationServiceModel);
        });

        Page<ExaminationServiceModel> examinationServiceModels = new PageImpl<ExaminationServiceModel>(examinationServiceModelsList, pageable, examinations.getTotalElements());
        return examinationServiceModels;
    }

    @Override
    public void changeOpen(Long id) {
        Examination examination = this.examinationRepository.findById(id).orElse(null);
        examination.setStatus(this.statusProcessed);
        this.examinationRepository.save(examination);
    }
}
