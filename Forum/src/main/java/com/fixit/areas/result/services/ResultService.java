package com.fixit.areas.result.services;


import com.fixit.areas.result.models.service.ResultServiceModel;
import com.fixit.areas.users.entities.Users;
import com.fixit.areas.ward.entities.Ward;
import org.springframework.security.core.Authentication;

import java.util.Set;

public interface ResultService {
    public Set<ResultServiceModel> findAllResults();
    Set<ResultServiceModel> findByPatient(Users patient);
    Set<ResultServiceModel> findByWard(Ward ward);

    Set<ResultServiceModel> listResults(Authentication auth);
}
