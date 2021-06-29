package com.example.lapcov_19.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lapcov_19.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InformasiDataPenduduk extends AppCompatActivity {

    Button btnDashboard;
    TextView txt_nama, txt_jk, txt_tanggalLahir, txt_alamat, txt_nik;
    ImageView fotoProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_data_penduduk);

       txt_nama = findViewById(R.id.nama);
       txt_jk = findViewById(R.id.jk);
       txt_alamat = findViewById(R.id.alamat);
       txt_nik = findViewById(R.id.nik);
       txt_tanggalLahir = findViewById(R.id.tanggalLahir);
       fotoProfil = findViewById(R.id.fotoProfil);

        btnDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformasiDataPenduduk.this, Dashboard.class);
                startActivity(intent);
            }
        });

        Intent getIntent = getIntent();

        String nk = String.valueOf(getIntent.getIntExtra("nik", 0));

        Date date = (Date) this.getIntent().getExtras().get("tanggalLahir");
        SimpleDateFormat s = new SimpleDateFormat("dd-MMM-yyyy");
        String dt = s.format(date);
        txt_nama.setText(getIntent.getStringExtra("nama"));
        txt_jk.setText(getIntent.getStringExtra("jk"));
        txt_alamat.setText(getIntent.getStringExtra("alamat"));
        txt_tanggalLahir.setText(dt);
        txt_nik.setText(nk);
        Picasso.get()
                .load("http://192.168.43.202:/WebServices/Web_Services_Lapcovid19/public/FotoProfil/"+getIntent.getStringExtra("foto"))
                .into(fotoProfil);
    }
}
