package com.fixit.config;
import com.fixit.areas.role.models.service.RoleServiceModel;
import com.fixit.areas.role.services.RoleService;
import com.fixit.areas.ward.models.view.WardNamesViewModel;
import com.fixit.areas.ward.services.WardService;
import com.fixit.cache.DataWardCacheSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private final RoleService roleService;

    private final WardService wardService;

    @Autowired
    public DataLoader(RoleService roleService, WardService wardService) {
        this.roleService = roleService;
        this.wardService = wardService;
    }
    public void run(ApplicationArguments args) {
        RoleServiceModel adminRole = this.roleService.findByAuthority("ADMIN");
        RoleServiceModel patientRole = this.roleService.findByAuthority("PATIENT");
        RoleServiceModel doctorRole = this.roleService.findByAuthority("DOCTOR");

        if (adminRole == null) {
            RoleServiceModel roleServiceModel = new RoleServiceModel();
            roleServiceModel.setAuthority("ADMIN");
            this.roleService.addRole(roleServiceModel);
        }

        if (patientRole == null) {
            RoleServiceModel roleServiceModel = new RoleServiceModel();
            roleServiceModel.setAuthority("PATIENT");
            this.roleService.addRole(roleServiceModel);
        }

        if (doctorRole == null) {
            RoleServiceModel roleServiceModel = new RoleServiceModel();
            roleServiceModel.setAuthority("DOCTOR");
            this.roleService.addRole(roleServiceModel);
        }

        List<WardNamesViewModel> wardNames = new ArrayList<>();
        this.wardService.findAllWardNames().forEach(wardName -> {
            WardNamesViewModel wardNamesViewModel = new WardNamesViewModel();
            wardNamesViewModel.setWardName(wardName);
            wardNames.add(wardNamesViewModel);
        });

        DataWardCacheSingleton.getInstance().addWards(wardNames);
    }
}