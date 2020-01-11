package com.fixit.areas.examintaion.services;

import com.fixit.areas.examintaion.models.service.ExaminationServiceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface ExaminationService {
    void create(ExaminationServiceModel examinationServiceModel);

    ExaminationServiceModel findById(Long id);

    Set<ExaminationServiceModel> findAll();

    Page<ExaminationServiceModel> findAllOpen(Pageable pageable);

    void changeOpen(Long id);
}
