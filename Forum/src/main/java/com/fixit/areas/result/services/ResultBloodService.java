package com.fixit.areas.result.services;

import com.fixit.areas.result.models.service.ResultBloodServiceModel;
import com.fixit.areas.result.models.service.ResultServiceModel;

import java.util.Set;

public interface ResultBloodService extends ResultService {

    public void createResultBlood(ResultBloodServiceModel resultBloodServiceModel);
    public Set<ResultBloodServiceModel> findAllResultsBlood();
}
