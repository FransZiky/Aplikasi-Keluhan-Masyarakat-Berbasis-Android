package com.example.lapcov_19.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lapcov_19.R;

public class Dashboard extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    public final static String TAG_NAMA = "nama";
    public final static String TAG_JK = "jk";
    public final static String TAG_TANGGALLAHIR = "tanggallahir";
    public final static String TAG_ALAMAT = "alamat";
    public final static String TAG_USERNAME = "username";
    public final static String TAG_PASSWORD = "password";
    public final static String TAG_PEKERJAAN = "perkerjaan";
    public final static String TAG_NIK = "nik";
    public final static String TAG_FOTO = "foto";

    TextView txt_profil, txt_informasi, txt_keluhan, txt_data;
    TextView txt_status, txt_about;
    LinearLayout profil, cekCovid;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);


        txt_informasi = findViewById(R.id.cekinformasi);
        txt_keluhan =  findViewById(R.id.cekkeluhan);
        txt_data = findViewById(R.id.cekpenduduk);
        txt_status = findViewById(R.id.cekstatus);
        txt_about = findViewById(R.id.tentangkami);
        profil = findViewById(R.id.profil);
        cekCovid = findViewById(R.id.cekCovid);

        if (sharedpreferences.getString("role", null).equals("penduduk")){
                profil.setVisibility(View.INVISIBLE);
                cekCovid.setVisibility(View.VISIBLE);
        }else if(sharedpreferences.getString("role", null).equals("pengurus")){
                profil.setVisibility(View.VISIBLE);
                cekCovid.setVisibility(View.INVISIBLE);
        }



        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Profil.class);
                startActivity(intent);
            }
        });

        txt_informasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Informasi.class);
                startActivity(intent);
            }
        });

        txt_keluhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Keluhan.class);
                startActivity(intent);
            }
        });

        txt_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, DataPenduduk.class);
                startActivity(intent);
            }
        });

        txt_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, KategoriStatus.class);
                startActivity(intent);
            }
        });


//        LinearLayout mCekCovid = findViewById(R.id.cekCovid);
        cekCovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Kategoricovid.class);
                startActivity(intent);
            }
        });

        txt_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, About.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean  onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId()==R.id.about){
//            startActivity(new Intent(this, AboutActivity.class));
//        } else if (item.getItemId() == R.id.setting) {
//            startActivity(new Intent(this, SettingActivity.class));
//        } else if (item.getItemId() == R.id.help) {
//            startActivity(new Intent(this, HelpActivity.class));
//        }

        if (sharedpreferences.getString("role", null).equals("penduduk")){

        }else if(sharedpreferences.getString("role", null).equals("pengurus")){
            profil.setVisibility(View.VISIBLE);
            cekCovid.setVisibility(View.INVISIBLE);
        }



        if (item.getItemId()== R.id.logut){
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(Login.session_status, false);
            editor.putString(TAG_JK, null);
            editor.putString(TAG_NAMA, null);
            editor.putString(TAG_PASSWORD, null);
            editor.putString(TAG_USERNAME, null);
            editor.putString(TAG_TANGGALLAHIR,null);
            editor.putString(TAG_ALAMAT,null);
            editor.putString(TAG_FOTO,null);
            editor.putString(TAG_NIK,null);
            editor.putString(TAG_PEKERJAAN,null);
            editor.commit();
            Intent ua = new Intent(Dashboard.this, Login.class);
            finish();
            startActivity(ua);
        } else if (item.getItemId() == R.id.profilMenu) {
            Intent intent = new Intent(Dashboard.this, Profil.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.cekLoca) {
            Intent intent = new Intent(Dashboard.this, RequestLocationUpdatesWithCallbackActivity.class);
            startActivity(intent);
        }

        return true;
    }
}
