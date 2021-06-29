package com.example.lapcov_19.model;

import com.example.lapcov_19.entity.EKeluhan;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KeluhanResult {
    @SerializedName("keluhan")
    @Expose
    List<EKeluhan> keluhan;

    public List<EKeluhan> getKeluhan() {
        return keluhan;
    }

    public void setmKeluhan(List<EKeluhan> keluhan) {
        this.keluhan = keluhan;
    }
}
