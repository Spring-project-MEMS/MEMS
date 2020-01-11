package com.fixit.areas.result.services;

import com.fixit.areas.result.models.service.ResultIrmServiceModel;

import java.util.List;
import java.util.Set;

public interface ResultIrmService extends ResultService {
    void createResultIrm(ResultIrmServiceModel resultIrmServiceModel);
    //List<ResultIrmServiceModel> findByWard(String wardName);
    //ResultIrmServiceModel findByPatient(String patientName);
    Set<ResultIrmServiceModel> findAllResultsIrm();

}
