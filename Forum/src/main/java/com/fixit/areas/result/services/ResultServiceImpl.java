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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ResultServiceImpl(ResultRepository resultRepository, ModelMapper modelMapper)
    {
        this.resultRepository=resultRepository;
        this.modelMapper=modelMapper;

    }

}
