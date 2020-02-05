package com.fixit.areas.result.services;

import com.fixit.areas.result.models.binding.ResultBloodBindingModel;
import com.fixit.areas.result.models.service.ResultBloodServiceModel;
import com.fixit.areas.users.entities.Users;
import com.fixit.areas.ward.entities.Ward;
import com.fixit.areas.ward.models.service.WardServiceModel;

import java.util.Set;

public interface ResultBloodService {

    void createResultBlood(ResultBloodBindingModel resultBloodBindingModel);
    Set<ResultBloodServiceModel> findAllResultsBlood();
    // REFACTOR THIS METHOD
    Set<ResultBloodServiceModel> findAllByPatient(Users patientName);
    // REFACTOR THIS METHOD
    Set<ResultBloodServiceModel> findAllByWard(WardServiceModel wardServiceModel);
    ResultBloodServiceModel findById(Long id);
}
