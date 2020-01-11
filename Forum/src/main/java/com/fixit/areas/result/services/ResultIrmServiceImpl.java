package com.fixit.areas.result.services;

import com.fixit.areas.result.entities.Result;
import com.fixit.areas.result.entities.ResultIrm;
import com.fixit.areas.result.models.service.ResultIrmServiceModel;
import com.fixit.areas.result.models.service.ResultServiceModel;
import com.fixit.areas.result.repositories.ResultIrmRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Set<ResultIrmServiceModel> findAllResultsIrm() {
        return null;
    }


}
