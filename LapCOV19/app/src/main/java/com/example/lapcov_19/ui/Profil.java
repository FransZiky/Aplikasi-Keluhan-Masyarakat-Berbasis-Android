package com.example.lapcov_19.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lapcov_19.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class Profil extends AppCompatActivity {
    public final static String TAG_NAMA = "nama";
    public final static String TAG_JK = "jk";
    public final static String TAG_TANGGALLAHIR = "tanggallahir";
    public final static String TAG_ALAMAT = "alamat";
    public final static String TAG_USERNAME = "username";
    public final static String TAG_PASSWORD = "password";
    public final static String TAG_PEKERJAAN = "perkerjaan";
    public final static String TAG_NIK = "nik";
    public final static String TAG_FOTO = "foto";

    SharedPreferences sharedpreferences;
    TextView mNama, mJK, mtanggallahir, mAlamat, mUsername, mPassword, mPekerjaan, mNIK;
    ImageView mFoto;
    Button edit, keluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        mNama = findViewById(R.id.pNama);
        mNama.setText(sharedpreferences.getString(TAG_NAMA,null));
        mJK = findViewById(R.id.pJK);
        mJK.setText(sharedpreferences.getString(TAG_JK, null));
        mtanggallahir = findViewById(R.id.pTanggalLahir);
        mtanggallahir.setText(sharedpreferences.getString(TAG_TANGGALLAHIR,null));
        mAlamat = findViewById(R.id.pAlamat);
        mAlamat.setText(sharedpreferences.getString(TAG_ALAMAT, null));
        mUsername = findViewById(R.id.pUsername);
        mUsername.setText(sharedpreferences.getString(TAG_USERNAME, null));
        mPassword = findViewById(R.id.pPassword);
        mPassword.setText(sharedpreferences.getString(TAG_PASSWORD, null));
        mPekerjaan = findViewById(R.id.pPekerjaan);
        mPekerjaan.setText(sharedpreferences.getString(TAG_PEKERJAAN, null));
        mNIK = findViewById(R.id.pNIK);
        mNIK.setText(sharedpreferences.getString(TAG_NIK, null));


        edit = findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profil.this, EditData.class);
                startActivity(intent);
            }
        });

//        keluar = findViewById(R.id.keluar);
//        keluar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Profil.this, Login.class);
//                startActivity(intent);
//            }
//        });

        mFoto = findViewById(R.id.prFoto);
        Picasso.get()
                .load("http://192.168.43.202:/WebServices/Web_Services_Lapcovid19/public/FotoProfil/"+sharedpreferences.getString(TAG_FOTO, null))
                .into(mFoto);
    }

}
