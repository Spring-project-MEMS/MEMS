package com.fixit.areas.ward.services;

import com.fixit.areas.ward.models.service.WardServiceModel;

import java.util.Set;

public interface WardService {

    void create(WardServiceModel wardServiceModel);

    WardServiceModel findByWardName(String wardName);

    Set<WardServiceModel> findAll();

    Set<String> findAllWardNames();

    WardServiceModel findById(Long id);

    
}
