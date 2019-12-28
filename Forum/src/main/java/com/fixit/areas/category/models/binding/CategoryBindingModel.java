package com.fixit.areas.category.models.binding;

import javax.validation.constraints.Size;
import com.fixit.constants.Constants;

public class CategoryBindingModel {
    @Size(min = 3, max = 20, message = Constants.CATEGORY_NAME_LENGTH)
    private String name;

    private String description;

    public CategoryBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
