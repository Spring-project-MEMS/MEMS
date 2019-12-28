package com.fixit.areas.result.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "result_irm")
public class ResultIrm extends Result{
    private String img;

    public ResultIrm(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
