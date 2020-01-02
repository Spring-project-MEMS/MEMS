package com.fixit.cache;

import com.fixit.areas.ward.models.view.WardNamesViewModel;

import java.util.ArrayList;
import java.util.List;

public class DataWardCacheSingleton {

    private static final DataWardCacheSingleton instance = new DataWardCacheSingleton();

    private List<WardNamesViewModel> wards = new ArrayList<>();

    private DataWardCacheSingleton(){}

    public static DataWardCacheSingleton getInstance(){
        return instance;
    }

    public List<WardNamesViewModel> getWards() {
        return this.wards;
    }

    public void addWard(WardNamesViewModel wardNamesViewModel) {
        this.wards.add(wardNamesViewModel);
    }

    public void addWards(List<WardNamesViewModel> wardNamesViewModels) {
        this.wards = wardNamesViewModels;
    }
}
