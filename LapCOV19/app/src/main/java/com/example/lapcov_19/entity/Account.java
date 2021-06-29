package com.example.lapcov_19.entity;

import com.google.gson.annotations.SerializedName;
import  com.google.gson.annotations.Expose;

import java.sql.Blob;
import java.util.Date;

public class Account {
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("jk")
    @Expose
    private  String jk;
    @SerializedName("tanggallahir")
    @Expose
    private Date tanggalLahir;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("nik")
    @Expose
    private int nik;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("pekerjaan")
    @Expose
    private String pekerjaan;
    @SerializedName("foto")
    @Expose
    private String  foto;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getNik() {
        return nik;
    }

    public void setNik(int nik) {
        this.nik = nik;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
