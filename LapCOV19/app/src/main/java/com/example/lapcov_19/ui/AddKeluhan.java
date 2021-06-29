package com.example.lapcov_19.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lapcov_19.R;
import com.example.lapcov_19.network.APIClient;
import com.example.lapcov_19.network.APIServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddKeluhan extends AppCompatActivity {

    EditText isi;
    TextView tgl, kategori;

    Button hapus, simpan;
    SharedPreferences sharedPreferences;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addkeluhan);

        Intent getIntent = getIntent();

        sharedPreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        isi = findViewById(R.id.isiEditKeluhan);
        kategori = findViewById(R.id.keluhanKategori);
        kategori.setText(getIntent.getStringExtra("kategori"));
        isi.setText(getIntent.getStringExtra("isi"));
        tgl = findViewById(R.id.keluhanTanggal);
        tgl.setText(getTanggal());

        hapus = findViewById(R.id.btnkel4);
        simpan = findViewById(R.id.btnkel5);
        mContext = this;


        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  kategori = getIntent.getStringExtra("kategori");
                String tanggal = getTanggal();
                String pengirim = sharedPreferences.getString("username",null);
                String isi1 = isi.getText().toString();
                String status =  "Proses";
                tambahKeluhan(pengirim, kategori, tanggal, isi1, status);
            }
        });
    }

    public String getTanggal(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }


    public void tambahKeluhan(final String pengirim,final String kategori,final String tanggal,final String isi,final String status){
        APIServices services = APIClient.getRetrofit().create(APIServices.class);
        services.addKeluhan(pengirim, kategori, tanggal, isi, status)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonResult = new JSONObject(response.body().string());
                                if (jsonResult.getString("success").equals("1")){
                                    Toast.makeText(mContext, "Berhasil menambahakn informasi",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(AddKeluhan.this, Keluhan.class);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    String error_message = jsonResult.getString("message");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            }catch (JSONException | IOException e){
                                e.printStackTrace();
                            }
                        }else {
                            Toast.makeText(AddKeluhan.this, response.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("error",t.getMessage());
                        Toast.makeText(AddKeluhan.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }


}
