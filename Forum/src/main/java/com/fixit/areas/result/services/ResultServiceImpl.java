package com.fixit.areas.result.services;

import com.fixit.areas.result.entities.Result;
import com.fixit.areas.result.entities.ResultBlood;
import com.fixit.areas.result.entities.ResultIrm;
import com.fixit.areas.result.models.service.ResultBloodServiceModel;
import com.fixit.areas.result.models.service.ResultIrmServiceModel;
import com.fixit.areas.result.models.service.ResultServiceModel;
import com.fixit.areas.result.repositories.ResultRepository;
import com.fixit.areas.role.entities.Role;
import com.fixit.areas.users.entities.Users;
import com.fixit.areas.ward.entities.Ward;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ResultServiceImpl(ResultRepository resultRepository, ModelMapper modelMapper) {
        this.resultRepository = resultRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Set<ResultServiceModel> findAllResults() {

        Set<ResultServiceModel> resultServiceModels = new HashSet<>();

        this.resultRepository.findAll().forEach(result -> {
            ResultServiceModel resultServiceModel=this.modelMapper
                    .map(result,(result instanceof  ResultIrm)?ResultIrmServiceModel.class:ResultBloodServiceModel.class);
                    resultServiceModels.add(resultServiceModel);
        });
        return resultServiceModels;
    }


    @Override
    public Set<ResultServiceModel> findByPatient(Users patient) {
        Set<ResultServiceModel> resultServiceModels=new HashSet<>();

        this.resultRepository.findAllByPatient(patient).forEach(result -> {
            ResultServiceModel resultServiceModel =this.modelMapper
                    .map(result,(result instanceof ResultIrm)? ResultIrmServiceModel.class :ResultBloodServiceModel.class);
            resultServiceModels.add(resultServiceModel);
        });
        return resultServiceModels;
    }

    @Override
    public Set<ResultServiceModel> findByWard(Ward ward) {
        Set<ResultServiceModel> resultServiceModels = new HashSet<>();

        this.resultRepository.findAllByWard(ward).forEach(result -> {
            ResultServiceModel resultServiceModel = this.modelMapper
                    .map(result, (result instanceof ResultIrm) ? ResultIrmServiceModel.class : ResultBloodServiceModel.class);
            resultServiceModels.add(resultServiceModel);
        });
        return resultServiceModels;
    }

    @Override
    public Set<ResultServiceModel> listResults(Authentication auth) {
        Users user = (Users) auth.getPrincipal();
        Set<Role> authorities = user.getAuthorities();
        Role role = authorities.iterator().next();

        if (role.getAuthority().equals("PATIENT")) {
            return  findByPatient(user);
        }
        else if(role.getAuthority().equals("DOCTOR")){
            return findByWard(user.getWard());
        }
        else
            return findAllResults();

    }

}

