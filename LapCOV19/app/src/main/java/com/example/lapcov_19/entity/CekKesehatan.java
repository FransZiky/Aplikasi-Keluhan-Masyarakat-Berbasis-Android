package com.example.lapcov_19.entity;

import com.google.gson.annotations.SerializedName;

public class CekKesehatan {
    @SerializedName("id_cek")
    private int id_cek;

    @SerializedName("daftarpertanyaan_gejala")
    private String daftarpertanyaan_gejala;

    @SerializedName("daftarpertanyaan_aktivitas")
    private String daftarpertanyaan_aktivitas;

    @SerializedName("username")
    private String username;

    @SerializedName("hasil")
    private int hasil;

    public int getId_cek() {
        return id_cek;
    }

    public void setId_cek(int id_cek) {
        this.id_cek = id_cek;
    }

    public String getDaftarpertanyaan_gejala() {
        return daftarpertanyaan_gejala;
    }

    public void setDaftarpertanyaan_gejala(String daftarpertanyaan_gejala) {
        this.daftarpertanyaan_gejala = daftarpertanyaan_gejala;
    }

    public String getDaftarpertanyaan_aktivitas() {
        return daftarpertanyaan_aktivitas;
    }

    public void setDaftarpertanyaan_aktivitas(String daftarpertanyaan_aktivitas) {
        this.daftarpertanyaan_aktivitas = daftarpertanyaan_aktivitas;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getHasil() {
        return hasil;
    }

    public void setHasil(int hasil) {
        this.hasil = hasil;
    }
}
