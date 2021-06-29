package com.example.lapcov_19.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lapcov_19.R;

public class KategoriStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori_status);

        Button hasilcek = (Button) findViewById(R.id.btnhasil);
        Button status = (Button) findViewById(R.id.btnstatus);

        hasilcek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KategoriStatus.this, HasilCekCovid.class);
                startActivity(intent);
            }
        });

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KategoriStatus.this, StatusPenduduk.class);
                startActivity(intent);
            }
        });
    }
}
