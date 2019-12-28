package com.fixit.config;

import com.fixit.areas.category.models.view.CategoryNamesViewModel;
import com.fixit.areas.category.services.CategoryService;
import com.fixit.areas.role.models.service.RoleServiceModel;
import com.fixit.areas.role.services.RoleService;
import com.fixit.cache.DataCacheSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private final RoleService roleService;

    private final CategoryService categoryService;

    @Autowired
    public DataLoader(RoleService roleService, CategoryService categoryService) {
        this.roleService = roleService;
        this.categoryService = categoryService;
    }

    public void run(ApplicationArguments args) {
        RoleServiceModel userRole = this.roleService.findByAuthority("USER");
        RoleServiceModel adminRole = this.roleService.findByAuthority("ADMIN");

        if (userRole == null) {
            RoleServiceModel roleServiceModel = new RoleServiceModel();
            roleServiceModel.setAuthority("USER");
            this.roleService.addRole(roleServiceModel);
        }

        if (adminRole == null) {
            RoleServiceModel roleServiceModel = new RoleServiceModel();
            roleServiceModel.setAuthority("ADMIN");
            this.roleService.addRole(roleServiceModel);
        }

        List<CategoryNamesViewModel> categoryNames = new ArrayList<>();
        this.categoryService.findAllNames().forEach(categoryName -> {
            CategoryNamesViewModel categoryNamesViewModel = new CategoryNamesViewModel();
            categoryNamesViewModel.setName(categoryName);
            categoryNames.add(categoryNamesViewModel);
        });
        DataCacheSingleton.getInstance().addCategories(categoryNames);
    }
}