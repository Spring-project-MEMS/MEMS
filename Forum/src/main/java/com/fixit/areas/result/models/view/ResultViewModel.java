package com.fixit.areas.result.models.view;

public class ResultViewModel {

    private String description;
    private Long wardId;

    public ResultViewModel() {
    }

    public Long getWardId() {
        return wardId;
    }

    public void setWardId(Long wardId) {
        this.wardId = wardId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
