package com.example.lapcov_19.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.lapcov_19.adapter.KeluhanAdapter;
import com.example.lapcov_19.entity.EKeluhan;
import com.example.lapcov_19.model.KeluhanResult;
import com.example.lapcov_19.network.APIClient;
import com.example.lapcov_19.network.APIServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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

public class Keluhan extends AppCompatActivity {

    List<EKeluhan> mKeluhan;
    KeluhanAdapter keluhanAdapter;
    SharedPreferences sharedPreferences;

    public final static String TAG_USERNAME = "username";
    public final static String TAG_ROLE = "role";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keluhan);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Keluhan.this, Kategorikeluhan.class);
                startActivity(intent);
            }
        });

        if(sharedPreferences.getString("role", null).equals("pengurus")){
            fab.setVisibility(View.INVISIBLE);
        }

        mKeluhan = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.content_keluhan);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        keluhanAdapter = new KeluhanAdapter(getApplicationContext(), mKeluhan);
        recyclerView.setAdapter(keluhanAdapter);

        APIServices services = APIClient.getRetrofit().create(APIServices.class);
//        Call<KeluhanResult> call = services.getKeluhan();

        if (sharedPreferences.getString(TAG_ROLE, null).equals("penduduk")) {

            String username = sharedPreferences.getString(TAG_USERNAME, null);
            Call<KeluhanResult> call = services.searchKeluhan(username);

            call.enqueue(new Callback<KeluhanResult>() {
                @Override
                public void onResponse(Call<KeluhanResult> call, Response<KeluhanResult> response) {
                    if (response.isSuccessful()) {
                        mKeluhan = response.body().getKeluhan();
                        keluhanAdapter.setKeluhan(mKeluhan);
                    } else {
                        Toast.makeText(getApplicationContext(), response.message(),
                                Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<KeluhanResult> call, Throwable t) {
                    Log.d("error", t.getMessage());
                    Toast.makeText(Keluhan.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Call<KeluhanResult> call = services.getKeluhan();

            call.enqueue(new Callback<KeluhanResult>() {
                @Override
                public void onResponse(Call<KeluhanResult> call, Response<KeluhanResult> response) {
                    if (response.isSuccessful()){
                            mKeluhan = response.body().getKeluhan();
                            keluhanAdapter.setKeluhan(mKeluhan);
                        }else{
                            Toast.makeText(getApplicationContext(), response.message() ,
                                    Toast.LENGTH_LONG).show();
                        }
                }

                @Override
                public void onFailure(Call<KeluhanResult> call, Throwable t) {
                    Log.d("error",t.getMessage());
                    Toast.makeText(Keluhan.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }



//        call.enqueue(new Callback<KeluhanResult>() {
//            @Override
//            public void onResponse(Call<KeluhanResult> call, Response<KeluhanResult> response) {
//                if (response.isSuccessful()){
//                    mKeluhan = response.body().getKeluhan();
//                    keluhanAdapter.setKeluhan(mKeluhan);
//                }else{
//                    Toast.makeText(getApplicationContext(), response.message() ,
//                            Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<KeluhanResult> call, Throwable t) {
//                Log.d("error",t.getMessage());
//                Toast.makeText(Keluhan.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });

    }

}
