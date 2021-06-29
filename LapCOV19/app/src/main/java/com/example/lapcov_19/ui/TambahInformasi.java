package com.example.lapcov_19.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lapcov_19.R;
import com.example.lapcov_19.model.InformasiResult;
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

public class TambahInformasi extends AppCompatActivity {

    Button simpan;
    String judul, tanggal, kategori, isi;
    EditText txt_judul, txt_tanggal, txt_kategori, txt_isi;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_informasi);


        Intent getIntent  = getIntent();
        simpan = findViewById(R.id.btnSimpan);
        mContext = this;
        txt_judul = findViewById(R.id.edit0);

        txt_kategori = findViewById(R.id.edit1);

        txt_kategori.setText(getIntent.getStringExtra("kategori"));

        txt_tanggal = findViewById(R.id.edit2);
        txt_tanggal.setText(getTanggal());

        txt_isi = findViewById(R.id.isiInformasi);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judul = txt_judul.getText().toString();
                kategori = txt_kategori.getText().toString();
                isi = txt_isi.getText().toString().trim();
                tanggal = txt_tanggal.getText().toString();
                if (TextUtils.isEmpty(isi)){
                    txt_isi.setError("Isi harus diisi");
                }else{
                    simpanInformasi(judul, tanggal, kategori, isi);
                }
            }
        });
    }

    public void simpanInformasi(final String judul, final String tanggal, final String kategori, final String  isi){
        APIServices services = APIClient.getRetrofit().create(APIServices.class);


        services.addInfromasi(judul, tanggal, kategori, isi).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                                try {
                                    JSONObject jsonResult = new JSONObject(response.body().string());
                                    if (jsonResult.getString("success").equals("1")){
                                        Toast.makeText(mContext, "Berhasil menambahakn informasi",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(TambahInformasi.this, Informasi.class);
                                        startActivity(intent);
                                    }else {
                                        String error_message = jsonResult.getString("message");
                                        Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                    }
                                }catch (JSONException | IOException e){
                                    e.printStackTrace();
                                }
                        }else{
                            Toast.makeText(TambahInformasi.this, response.toString(), Toast.LENGTH_SHORT).show();
                        }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("error",t.getMessage());
                        Toast.makeText(TambahInformasi.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getTanggal(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
