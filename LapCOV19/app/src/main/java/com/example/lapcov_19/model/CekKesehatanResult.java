package com.example.lapcov_19.model;

import com.example.lapcov_19.entity.CekKesehatan;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CekKesehatanResult {
    @SerializedName("cekKesehatan")
    @Expose
    private List<CekKesehatan> cekKesehatan;

    public List<CekKesehatan> getCekKesehatan(){
        return cekKesehatan;
    }
}
