package com.fixit.areas.category.services;

import java.util.Set;
import com.fixit.areas.category.models.service.CategoryServiceModel;

public interface CategoryService {
    void create(CategoryServiceModel categoryServiceModel);

    CategoryServiceModel findByName(String name);

    Set<CategoryServiceModel> findAll();

    Set<String> findAllNames();

    CategoryServiceModel findById(Long id);
}
