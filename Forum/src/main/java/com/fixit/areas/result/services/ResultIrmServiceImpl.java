package com.fixit.areas.result.services;

import com.fixit.areas.result.entities.ResultIrm;
import com.fixit.areas.result.models.service.ResultIrmServiceModel;
import com.fixit.areas.result.models.service.ResultServiceModel;
import com.fixit.areas.result.repositories.ResultIrmRepository;
import com.fixit.areas.users.entities.Users;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

//@Primary
@Service
public class ResultIrmServiceImpl implements ResultIrmService {

    private final ModelMapper modelMapper;
    private final ResultIrmRepository resultIrmRepository;

    @Autowired
    public ResultIrmServiceImpl(ModelMapper modelMapper, ResultIrmRepository resultIrmRepository) {
        this.modelMapper = modelMapper;
        this.resultIrmRepository = resultIrmRepository;
    }

    @Override
    public void createResultIrm(ResultIrmServiceModel resultIrmServiceModel) {
        ResultIrm result=this.modelMapper.map(resultIrmServiceModel,ResultIrm.class);
        this.resultIrmRepository.save(result);
    }



    @Override
    public Set<ResultIrmServiceModel> findByPatient(Users patient) {
        Set<ResultIrmServiceModel> resultIrmServiceModels=new HashSet<>();

        this.resultIrmRepository.findAllByPatient(patient).forEach(resultBlood -> {
            ResultIrmServiceModel resultBloodServiceModel =this.modelMapper.map(resultBlood,ResultIrmServiceModel.class);
            resultIrmServiceModels.add(resultBloodServiceModel);
        });
        return resultIrmServiceModels;
    }

    @Override
    public Set<ResultIrmServiceModel> findAllResultsIrm() {
        Set<ResultIrmServiceModel> resultIrmServiceModels=new HashSet<>();

        this.resultIrmRepository.findAll().forEach(resultBlood -> {
            ResultIrmServiceModel resultBloodServiceModel =this.modelMapper.map(resultBlood,ResultIrmServiceModel.class);
            resultIrmServiceModels.add(resultBloodServiceModel);
        });
        return resultIrmServiceModels;
    }

    @Override
    public ResultIrmServiceModel findById(Long id) {
        ResultIrm resultIrm=this.resultIrmRepository.findById(id).orElse(null);
        ResultIrmServiceModel resultIrmServiceModel=this.modelMapper.map(resultIrm,ResultIrmServiceModel.class);

        return resultIrmServiceModel;
    }



}
