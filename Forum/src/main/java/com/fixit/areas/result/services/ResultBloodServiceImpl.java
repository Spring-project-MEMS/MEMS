package com.fixit.areas.result.services;

import com.fixit.areas.result.entities.ResultBlood;
import com.fixit.areas.result.models.service.ResultBloodServiceModel;
import com.fixit.areas.result.repositories.ResultBloodRepository;
import com.fixit.areas.result.repositories.ResultRepository;
import com.fixit.areas.users.entities.Users;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Primary
@Service
public class ResultBloodServiceImpl implements ResultBloodService {

    private final ModelMapper modelMapper;
    private final ResultBloodRepository resultBloodRepository;
    private final ResultRepository resultRepository;

    @Autowired
    public ResultBloodServiceImpl(ModelMapper modelMapper, ResultBloodRepository resultBloodRepository,ResultRepository resultRepository) {
        this.modelMapper = modelMapper;
        this.resultBloodRepository = resultBloodRepository;
        this.resultRepository=resultRepository;
    }

    @Override
    public void createResultBlood(ResultBloodServiceModel resultBloodServiceModel) {
        ResultBlood resultBlood=this.modelMapper.map(resultBloodServiceModel,ResultBlood.class);
        this.resultBloodRepository.save(resultBlood);
    }

    @Override
    public Set<ResultBloodServiceModel> findAllResultsBlood() {
        Set<ResultBloodServiceModel> resultBloodServiceModels=new HashSet<>();

        this.resultBloodRepository.findAll().forEach(resultBlood -> {
            ResultBloodServiceModel resultBloodServiceModel =this.modelMapper.map(resultBlood,ResultBloodServiceModel.class);
            resultBloodServiceModels.add(resultBloodServiceModel);
        });
        return resultBloodServiceModels;
    }

    @Override
    public Set<ResultBloodServiceModel> findByPatient(Users patient) {
        Set<ResultBloodServiceModel> resultBloodServiceModels=new HashSet<>();

        this.resultBloodRepository.findAllByPatient(patient).forEach(resultBlood -> {
            ResultBloodServiceModel resultBloodServiceModel =this.modelMapper.map(resultBlood,ResultBloodServiceModel.class);
            resultBloodServiceModels.add(resultBloodServiceModel);
        });
        return resultBloodServiceModels;}

    @Override
    public ResultBloodServiceModel findById(Long id) {
        ResultBlood resultBlood=this.resultBloodRepository.findById(id).orElse(null);
        ResultBloodServiceModel resultBloodServiceModel=this.modelMapper.map(resultBlood,ResultBloodServiceModel.class);

        return resultBloodServiceModel;
    }
}
