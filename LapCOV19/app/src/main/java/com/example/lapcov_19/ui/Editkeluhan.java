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

public class Editkeluhan extends AppCompatActivity {

    TextView mkeluhanKategori, mKeluhanTanggal;
    Button btn_kirim, hapus;
    SharedPreferences sharedPreferences;
    EditText txt_isi;
    Context mContext;
    int id_keluhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editkeluhan);

        sharedPreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        mContext = this;
        Intent getIntent = getIntent();
        mkeluhanKategori = findViewById(R.id.keluhanKategori);
        mkeluhanKategori.setText(getIntent.getStringExtra("kategori"));
        mKeluhanTanggal = findViewById(R.id.keluhanTanggal);
        mKeluhanTanggal.setText(getTanggal());
        txt_isi = findViewById(R.id.pesan);
        txt_isi.setText(getIntent.getStringExtra("isi"));
        id_keluhan = getIntent.getIntExtra("id_keluhan",0);




        hapus = findViewById(R.id.btnkel4);
        btn_kirim = findViewById(R.id.btnkel5);

        btn_kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kategori = getIntent.getStringExtra("kategori");
                String tanggal = getTanggal();
                String pengirim = sharedPreferences.getString("username",null);
                String isi1 = txt_isi.getText().toString();
                String status = getIntent.getStringExtra("status");


                simpan(pengirim, kategori, tanggal, isi1, status);

            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hapus();
            }
        });

    }

    public void simpan(final String pengirim,final String kategori,final String tanggal,final String isi,final String status){
        APIServices services = APIClient.getRetrofit().create(APIServices.class);

        services.updateKeluhan(id_keluhan,pengirim, kategori, tanggal, isi, status)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonResult = new JSONObject(response.body().string());
                                if (jsonResult.getString("success").equals("1")){
                                    Toast.makeText(mContext, "Berhasil mengubah data",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Editkeluhan.this, Keluhan.class);
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
                            Toast.makeText(Editkeluhan.this, response.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("error",t.getMessage());
                        Toast.makeText(Editkeluhan.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void hapus(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        APIServices services = APIClient.getRetrofit().create(APIServices.class);

        alertDialogBuilder.setTitle("Ingin menghapus keluhan ini??");

        alertDialogBuilder
                .setMessage("Klik Ya untuk menghapus!")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        services.deleteKeluhan(id_keluhan)
                                .enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if (response.isSuccessful()){
                                            Intent intent = new Intent(Editkeluhan.this, Keluhan.class);
                                            startActivity(intent);
                                            finish();
                                        }else{
                                            Toast.makeText(getApplicationContext(), response.message() ,
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        Log.d("error",t.getMessage());
                                        Toast.makeText(Editkeluhan.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                })
                .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    public String getTanggal(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
