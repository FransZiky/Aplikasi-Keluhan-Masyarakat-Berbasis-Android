package com.example.lapcov_19.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.lapcov_19.R;
import com.example.lapcov_19.adapter.PendudukAdapter;
import com.example.lapcov_19.entity.Account;
import com.example.lapcov_19.model.AccountResult;
import com.example.lapcov_19.network.APIClient;
import com.example.lapcov_19.network.APIServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataPenduduk extends AppCompatActivity {

    List<Account> mAccount;
    PendudukAdapter pendudukAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_penduduk);


        mAccount = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.content_penduduk);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        pendudukAdapter = new PendudukAdapter(getApplicationContext(), mAccount);
        recyclerView.setAdapter(pendudukAdapter);

        APIServices services = APIClient.getRetrofit().create(APIServices.class);
        Call<AccountResult> call = services.getAcc();

        call.enqueue(new Callback<AccountResult>() {
            @Override
            public void onResponse(Call<AccountResult> call, Response<AccountResult> response) {
                if(response.isSuccessful()){


                    mAccount = response.body().getAcc();
                    pendudukAdapter.setAccount(mAccount);

                }else {
                    Toast.makeText(getApplicationContext(), response.message() ,
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AccountResult> call, Throwable t) {
                Log.d("error",t.getMessage());
                Toast.makeText(DataPenduduk.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
