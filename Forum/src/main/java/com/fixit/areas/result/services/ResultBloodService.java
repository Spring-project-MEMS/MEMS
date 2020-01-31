package com.fixit.areas.result.services;

import com.fixit.areas.result.models.service.ResultBloodServiceModel;
import com.fixit.areas.users.entities.Users;

import java.util.Set;

public interface ResultBloodService {

    public void createResultBlood(ResultBloodServiceModel resultBloodServiceModel);
    public Set<ResultBloodServiceModel> findAllResultsBlood();
    Set<ResultBloodServiceModel> findByPatient(Users patientName);
    ResultBloodServiceModel findById(Long id);
}
