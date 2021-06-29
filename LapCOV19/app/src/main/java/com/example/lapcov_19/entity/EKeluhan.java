package com.example.lapcov_19.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EKeluhan {
    @SerializedName("id_keluhan")
    @Expose
    private int id_keluhan;

    @SerializedName("pengirim")
    private String pengirim;
    @SerializedName("kategori")
    private String kategori;
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("isi")
    private String isi;
    @SerializedName("status")
    private String status;

    public int getId_keluhan() {
        return id_keluhan;
    }

    public void setId_keluhan(int id_keluhan) {
        this.id_keluhan = id_keluhan;
    }

    public String getPengirim() {
        return pengirim;
    }

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
