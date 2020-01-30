package com.fixit.areas.result.models.view;

public class ResultIrmViewModel extends ResultViewModel {
    private String img;

    public ResultIrmViewModel() {
    }

    @Override
    public ResultType getType() {
        return ResultType.IRM;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
