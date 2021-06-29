package com.example.lapcov_19.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lapcov_19.R;

public class Kategoricovid extends AppCompatActivity {
Button bt;
Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategoricovid);

        bt = findViewById(R.id.btncov1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about = new Intent(Kategoricovid.this, Gejalacovid.class);
                about.putExtra("gejala", "1");
                about.putExtra("aktivitas","0");
                startActivity(about);
            }
        });

        bt1 = findViewById(R.id.btncov2);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about = new Intent(Kategoricovid.this, Aktivitascovid.class);
                about.putExtra("aktivitas","1");
                about.putExtra("gejala", "0");
                startActivity(about);
            }
        });
    }
}
