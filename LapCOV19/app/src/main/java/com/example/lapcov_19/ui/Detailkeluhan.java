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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detailkeluhan extends AppCompatActivity {


    TextView mkeluhanKategori, mKeluhanTanggal, mPesan;
    Button btn_edit, hapus, balas;
    SharedPreferences sharedPreferences;
    Context mContext;
    int id_keluhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailkeluhan);

        sharedPreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);

        Intent getIntent = getIntent();
        mkeluhanKategori = findViewById(R.id.keluhanKategori);
        mkeluhanKategori.setText(getIntent.getStringExtra("kategori"));
        mKeluhanTanggal = findViewById(R.id.keluhanTanggal);
        mKeluhanTanggal.setText(getIntent.getStringExtra("tanggal"));
        hapus = findViewById(R.id.btnkel4);
        id_keluhan = getIntent.getIntExtra("id_keluhan",0);

        btn_edit = findViewById(R.id.btnkel5);

        if(sharedPreferences.getString("role", null).equals("pengurus")){
           btn_edit.setText("Balas");
           btn_edit.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(Detailkeluhan.this, TambahInformasi.class);
                   intent.putExtra("kategori", getIntent.getStringExtra("kategori"));
                   intent.putExtra("isi", getIntent.getStringExtra("isi"));
                   intent.putExtra("id_keluhan", getIntent.getIntExtra("id_keluhan",0));
                   intent.putExtra("status", getIntent.getStringExtra("status"));
                   startActivity(intent);
               }
           });
        }else if (sharedPreferences.getString("role", null).equals("penduduk")){
            btn_edit.setText("Edit");
            btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Detailkeluhan.this, Editkeluhan .class);
                    intent.putExtra("kategori", getIntent.getStringExtra("kategori"));
                    intent.putExtra("isi", getIntent.getStringExtra("isi"));
                    intent.putExtra("id_keluhan", getIntent.getIntExtra("id_keluhan",0));
                    intent.putExtra("status", getIntent.getStringExtra("status"));
                    startActivity(intent);
                }
            });
        }

        mPesan = findViewById(R.id.pesan);
        mPesan.setText(getIntent.getStringExtra("isi"));


        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hapus();
            }
        });
    }

    public String getTanggal(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
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
                                            Intent intent = new Intent(Detailkeluhan.this, Keluhan.class);
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
                                        Toast.makeText(Detailkeluhan.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
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
}
