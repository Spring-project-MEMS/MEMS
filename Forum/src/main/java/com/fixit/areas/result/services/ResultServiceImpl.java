package com.fixit.areas.result.services;

import com.fixit.areas.result.repositories.ResultRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
