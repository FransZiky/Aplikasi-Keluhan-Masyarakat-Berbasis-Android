package com.example.lapcov_19.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.lapcov_19.R;
import com.example.lapcov_19.adapter.CekKesehatanAdapter;
import com.example.lapcov_19.adapter.ECekKesehatanAdapter;
import com.example.lapcov_19.entity.ECekKesehatan;
import com.example.lapcov_19.entity.CekKesehatan;
import com.example.lapcov_19.model.CekKesehatanResult;
import com.example.lapcov_19.model.CekKesehatanViewModel;
import com.example.lapcov_19.network.APIClient;
import com.example.lapcov_19.network.APIServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HasilCekCovid extends AppCompatActivity {

    private CekKesehatanViewModel mCekKesehatanViewModel;
    List<CekKesehatan> mCekKesehatan;
    CekKesehatanAdapter cekKesehatanAdapter;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_cek_covid);

        sharedPreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);

        mCekKesehatan = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.content_cekKesehatan);






        if (sharedPreferences.getString("role", null).equals("penduduk")) {
            final ECekKesehatanAdapter adapter = new ECekKesehatanAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            mCekKesehatanViewModel = ViewModelProviders.of(this).get(CekKesehatanViewModel.class);

            mCekKesehatanViewModel = ViewModelProviders.of(this).get(CekKesehatanViewModel.class);
            mCekKesehatanViewModel.deletKelas();
            mCekKesehatanViewModel.getCekKesehatan().observe(this, new Observer<List<ECekKesehatan>>() {
                @Override
                public void onChanged(List<ECekKesehatan> ECekKesehatans) {
                    adapter.eSetCekKesehatan(ECekKesehatans);
                    for (int i=0;i<ECekKesehatans.size();i++){
//                        System.out.println("Hoh : "+ ECekKesehatans.get(i).getUsername());
                    }
                }
            });


            APIServices services = APIClient.getRetrofit().create(APIServices.class);
            String username =  sharedPreferences.getString("username", null);
            Call<CekKesehatanResult> call =  services.searchCekKesehatan(username);

            call.enqueue(new Callback<CekKesehatanResult>() {
                @Override
                public void onResponse(Call<CekKesehatanResult> call, Response<CekKesehatanResult> response) {
                    if (response.isSuccessful()){
//                        List<ECekKesehatan> eCekKesehatans = response.body().getCekKesehatan();
//                        cekKesehatanAdapter.setCekKesehatan(mCekKesehatan);
                        List<CekKesehatan> ck = response.body().getCekKesehatan();
                        int size = response.body().getCekKesehatan().size();
                        if (size>0) {
                            for (int i=0;i<size;i++) {
                                ECekKesehatan eCekKesehatans = new ECekKesehatan(ck.get(i).getId_cek(), ck.get(i).getDaftarpertanyaan_gejala(),
                                        ck.get(i).getDaftarpertanyaan_aktivitas(), ck.get(i).getUsername(), String.valueOf(ck.get(i).getHasil()));
                                mCekKesehatanViewModel.insetCekKesehata(eCekKesehatans);
                            }
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), response.message() ,
                                Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<CekKesehatanResult> call, Throwable t) {
                    Log.d("error",t.getMessage());
                    Toast.makeText(HasilCekCovid.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        else{
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            cekKesehatanAdapter = new CekKesehatanAdapter(getApplicationContext(), mCekKesehatan);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(cekKesehatanAdapter);
            APIServices services = APIClient.getRetrofit().create(APIServices.class);
                Call<CekKesehatanResult> call = services.getCekKesehatan();

                call.enqueue(new Callback<CekKesehatanResult>() {
                    @Override
                    public void onResponse(Call<CekKesehatanResult> call, Response<CekKesehatanResult> response) {
                       if (response.isSuccessful()){
                           mCekKesehatan = response.body().getCekKesehatan();
                           cekKesehatanAdapter.setCekKesehatan(mCekKesehatan);
                       }else {
                           Toast.makeText(getApplicationContext(), response.message() ,
                                   Toast.LENGTH_LONG).show();
                       }
                    }

                    @Override
                    public void onFailure(Call<CekKesehatanResult> call, Throwable t) {
                        Log.d("error",t.getMessage());
                    Toast.makeText(HasilCekCovid.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        }


    }
}
