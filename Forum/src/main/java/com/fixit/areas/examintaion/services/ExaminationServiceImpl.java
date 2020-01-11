package com.fixit.areas.examintaion.services;

import com.fixit.areas.examintaion.enitities.Examination;
import com.fixit.areas.examintaion.models.service.ExaminationServiceModel;
import com.fixit.areas.examintaion.repositories.ExaminationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class ExaminationServiceImpl implements ExaminationService{

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
    public Set<ExaminationServiceModel> findAll() {

        Set<ExaminationServiceModel> examinationServiceModels = new HashSet<>();

        this.examinationRepository.findAll().forEach(examination -> {
            ExaminationServiceModel examinationServiceModel = this.modelMapper.map(examination, ExaminationServiceModel.class);
            examinationServiceModels.add(examinationServiceModel);
        });

        return examinationServiceModels;
    }
}
