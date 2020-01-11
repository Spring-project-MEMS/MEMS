package com.fixit.areas.result.services;

import com.fixit.areas.result.models.service.ResultBloodServiceModel;
import com.fixit.areas.result.models.service.ResultIrmServiceModel;
import com.fixit.areas.result.models.service.ResultServiceModel;

import java.util.List;

public interface ResultService {

    void create(ResultServiceModel resultServiceModel);
    void createResultBlood(ResultBloodServiceModel resultBloodServiceModel);
    void createResultIrm(ResultIrmServiceModel resultIrmServiceModel);

    List<ResultServiceModel> findByWard(String wardName);
    ResultServiceModel findByPatient(String patientName);


}
