package com.fixit.areas.result.models.view;

public abstract class ResultViewModel {

    private Long id;
    private String description;
    private Long wardId;

    public ResultViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public abstract ResultType getType();
}
