package com.example.lapcov_19.ui;


import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lapcov_19.R;

public class Splashscreen extends AppCompatActivity {

    Button x;
    Button x1,x2;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        x = (Button) findViewById(R.id.btn1);
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        x.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View y){
                spinner.setVisibility(View.VISIBLE);
                ObjectAnimator objectAnimator = null;
                objectAnimator.ofObject(
                        x,"textColor", new ArgbEvaluator(), Color.BLUE,Color.RED,Color.GREEN
                ).setDuration(500).start();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent pindah = new Intent (getApplicationContext(),Register.class);
                        startActivity(pindah);
                        finish();
                    }
                },3000);
            }
        });
        x1 = (Button) findViewById(R.id.btn3);
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        x1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View y){
                spinner.setVisibility(View.VISIBLE);
                ObjectAnimator objectAnimator = null;
                objectAnimator.ofObject(
                        x1,"textColor", new ArgbEvaluator(), Color.BLUE,Color.RED,Color.GREEN
                ).setDuration(500).start();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent pindah = new Intent (getApplicationContext(),Kategoricovid.class);
                        startActivity(pindah);
                        finish();
                    }
                },3000);
            }
        });

        x2 = (Button) findViewById(R.id.btn2);
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        x2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View y){
                spinner.setVisibility(View.VISIBLE);
                ObjectAnimator objectAnimator = null;
                objectAnimator.ofObject(
                        x2,"textColor", new ArgbEvaluator(), Color.BLUE,Color.RED,Color.GREEN
                ).setDuration(500).start();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent pindah = new Intent (getApplicationContext(),Login.class);
                        startActivity(pindah);
                        finish();
                    }
                },50);
            }
        });
    }
}
