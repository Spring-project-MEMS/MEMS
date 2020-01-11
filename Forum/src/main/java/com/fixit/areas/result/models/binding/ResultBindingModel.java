package com.fixit.areas.result.models.binding;

import com.fixit.areas.result.services.ResultService;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Size;

public class ResultBindingModel {

    @Size(min=0,max=1000)
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
