package com.example.lapcov_19.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lapcov_19.R;

public class Kategorikeluhan extends AppCompatActivity {
Button btn_kesehatan, btn_umum, btn_nilai, btn_saran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategorikeluhan);

        btn_kesehatan = findViewById(R.id.btnkel1);
        btn_umum = findViewById(R.id.btnkel2);
        btn_nilai = findViewById(R.id.btnkel3);
        btn_saran = findViewById(R.id.btnkel4);

        btn_kesehatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kesehatan = new Intent(Kategorikeluhan.this, AddKeluhan.class);
                kesehatan.putExtra("kategori","Kesehatan");
                startActivity(kesehatan);
            }
        });

        btn_umum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent umum = new Intent(Kategorikeluhan.this, AddKeluhan.class);
                umum.putExtra("kategori","Umum");
                startActivity(umum);
            }
        });

        btn_nilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nilai = new Intent(Kategorikeluhan.this, AddKeluhan.class);
                nilai.putExtra("kategori","nilai");
                startActivity(nilai);
            }
        });

        btn_saran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent saran = new Intent(Kategorikeluhan.this, AddKeluhan.class);
                saran.putExtra("kategori", "saran");
                startActivity(saran);
            }
        });
    }
}
