package com.example.lapcov_19.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.lapcov_19.adapter.InformasiAdapter;
import com.example.lapcov_19.entity.EInformasi;
import com.example.lapcov_19.model.InformasiResult;
import com.example.lapcov_19.network.APIClient;
import com.example.lapcov_19.network.APIServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lapcov_19.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Informasi extends AppCompatActivity {

    private List<EInformasi> mInformasi;
    private InformasiAdapter informasiAdapter;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        if (sharedpreferences.getString("role", null).equals("penduduk")){
            fab.setVisibility(View.INVISIBLE);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Informasi.this, TambahInformasi.class);
                startActivity(intent);
            }
        });

        mInformasi = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.content_informasi);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        informasiAdapter = new InformasiAdapter(getApplicationContext(), mInformasi);
        recyclerView.setAdapter(informasiAdapter);

        APIServices services = APIClient.getRetrofit().create(APIServices.class);

        Call<InformasiResult> call = services.getInformasi();

        call.enqueue(new Callback<InformasiResult>() {
            @Override
            public void onResponse(Call<InformasiResult> call, Response<InformasiResult> response) {
                if (response.isSuccessful()){
                    mInformasi = response.body().getInformasi();
                    informasiAdapter.setInformasi(mInformasi);
                }else{
                    Toast.makeText(getApplicationContext(), response.message() ,
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<InformasiResult> call, Throwable t) {
                Log.d("error",t.getMessage());
                Toast.makeText(Informasi.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}
