package com.fixit.areas.result.services;

import com.fixit.areas.result.models.service.ResultIrmServiceModel;
import com.fixit.areas.users.entities.Users;
import com.fixit.areas.ward.entities.Ward;

import java.util.Set;

public interface ResultIrmService extends ResultService {
    void createResultIrm(ResultIrmServiceModel resultIrmServiceModel);
    Set<ResultIrmServiceModel> findByPatient(Users patient);
    Set<ResultIrmServiceModel> findAllResultsIrm();
    ResultIrmServiceModel findById(Long id);

}
