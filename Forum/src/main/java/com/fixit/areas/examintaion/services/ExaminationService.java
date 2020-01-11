package com.fixit.areas.examintaion.services;

import com.fixit.areas.examintaion.models.service.ExaminationServiceModel;

import java.util.Set;

public interface ExaminationService {
    void create(ExaminationServiceModel examinationServiceModel);

    Set<ExaminationServiceModel> findAll();
}
