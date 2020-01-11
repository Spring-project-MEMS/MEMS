package com.fixit.areas.result.services;

import com.fixit.areas.result.entities.Result;
import com.fixit.areas.result.entities.ResultIrm;
import com.fixit.areas.result.models.service.ResultBloodServiceModel;
import com.fixit.areas.result.models.service.ResultIrmServiceModel;
import com.fixit.areas.result.models.service.ResultServiceModel;
import com.fixit.areas.result.repositories.ResultIrmRepository;
import com.fixit.areas.result.repositories.ResultRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final ModelMapper modelMapper;
    private final ResultIrmRepository resultIrmRepository;

    @Autowired
    public ResultServiceImpl(ResultRepository resultRepository, ModelMapper modelMapper,ResultIrmRepository resultIrmRepository)
    {
        this.resultRepository=resultRepository;
        this.resultIrmRepository=resultIrmRepository;
        this.modelMapper=modelMapper;

    }

    @Override
    public void create(ResultServiceModel resultServiceModel)
    {
        Result result=this.modelMapper.map(resultServiceModel,Result.class);
        this.resultRepository.save(result);
    }


    @Override
    public void createResultBlood(ResultBloodServiceModel resultBloodServiceModel) {

    }

    @Override
    public void createResultIrm(ResultIrmServiceModel resultIrmServiceModel) {
        ResultIrm resultIrm=this.modelMapper.map(resultIrmServiceModel,ResultIrm.class);
        this.resultIrmRepository.save(resultIrm);
    }

    @Override
    public List<ResultServiceModel> findByWard(String wardName) {
        return null;
    }

    @Override
    public ResultServiceModel findByPatient(String patientName) {
        return null;
    }
}
