package com.fixit.cache;

import java.util.ArrayList;
import java.util.List;
import com.fixit.areas.category.models.view.CategoryNamesViewModel;

public class DataCacheSingleton {

    private static final DataCacheSingleton instance = new DataCacheSingleton();

    private List<CategoryNamesViewModel> categories = new ArrayList<>();

    private DataCacheSingleton(){}

    public static DataCacheSingleton getInstance(){
        return instance;
    }

    public List<CategoryNamesViewModel> getCategories() {
        return this.categories;
    }

    public void addCategory(CategoryNamesViewModel categoryNamesViewModel) {
        this.categories.add(categoryNamesViewModel);
    }

    public void addCategories(List<CategoryNamesViewModel> categories) {
        this.categories = categories;
    }
}
