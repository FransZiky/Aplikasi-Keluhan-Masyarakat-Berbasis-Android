package com.example.lapcov_19.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lapcov_19.R;

public class detail_informasi extends AppCompatActivity {

    TextView judul, tanggal, isi, kategori2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_informasi);

        Intent getIntent = getIntent();

        judul = findViewById(R.id.judul);
        judul.setText(getIntent.getStringExtra("judul"));
        tanggal = findViewById(R.id.tanggal);
        tanggal.setText(getIntent.getStringExtra("tanggal"));
        isi = findViewById(R.id.isi);
        isi.setText(getIntent.getStringExtra("isi"));
        kategori2 = findViewById(R.id.kategori0);
        kategori2.setText("kategori");

    }
}