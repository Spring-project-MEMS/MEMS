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
    Page<ExaminationServiceModel> findAllProcessed(Pageable pageable);
    Page<ExaminationServiceModel> findAllPending(Pageable pageable);
    Page<ExaminationServiceModel> findAllClosed(Pageable pageable);

    void changeOpen(Long id);
    void changeProcessed(Long id);
    void changePending(Long id);

    void viewClosed(Long id);
}
