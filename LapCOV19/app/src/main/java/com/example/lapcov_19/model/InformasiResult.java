package com.example.lapcov_19.model;

import com.example.lapcov_19.entity.EInformasi;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InformasiResult {
    @SerializedName("informasi")
    @Expose
    List<EInformasi> informasi;

    public List<EInformasi> getInformasi() {
        return informasi;
    }

    public void setInformasi(List<EInformasi> informasi) {
        this.informasi = informasi;
    }
}
