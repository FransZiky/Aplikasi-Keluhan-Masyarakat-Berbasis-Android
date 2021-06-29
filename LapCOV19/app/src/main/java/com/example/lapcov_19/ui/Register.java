package com.example.lapcov_19.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lapcov_19.R;
import com.example.lapcov_19.entity.Account;
import com.example.lapcov_19.model.AccountResult;
import com.example.lapcov_19.network.APIClient;
import com.example.lapcov_19.network.APIServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    EditText mtglLahir, mNama, mJK, mAlamat, mNIK, mUsername, mPassword, mPekerjaan;
    Calendar myCalendar = Calendar.getInstance();
    Context mContext;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnNext = (Button) findViewById(R.id.btnNext);
        mNama = findViewById(R.id.nama);
        mJK = findViewById(R.id.jk);
        mAlamat = findViewById(R.id.almt);
        mNIK = findViewById(R.id.nik);
        mUsername = findViewById(R.id.inuser);
        mtglLahir = findViewById(R.id.tglLhr);
        mPassword = findViewById(R.id.inpass);
        mPekerjaan = findViewById(R.id.pekerjaan);
        mContext = this;

        mtglLahir.setFocusableInTouchMode(false);
        mtglLahir.setFocusable(false);
        mtglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Register.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString().trim();
                String jk = mJK.getText().toString().trim();
                String alamat = mAlamat.getText().toString().trim();
                String nik = mNIK.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String tglLahir = mtglLahir.getText().toString().trim();
                String nama = mNama.getText().toString().trim();
                String pekerjaan = mPekerjaan.getText().toString().trim();
                String foto = "default.png";

                if (TextUtils.isEmpty(nama)){
                    mNama.setError("Usrname tidak boleh kosong");
                }else if(TextUtils.isEmpty(jk)){
                    mJK.setError("Usrname tidak boleh kosong");
                }else if(TextUtils.isEmpty(tglLahir)){
                    mtglLahir.setError("Usrname tidak boleh kosong");
                }else if(TextUtils.isEmpty(alamat)){
                    mAlamat.setError("Usrname tidak boleh kosong");
                }else if(TextUtils.isEmpty(nik)){
                    mNIK.setError("Usrname tidak boleh kosong");
                }else if(TextUtils.isEmpty(username)){
                    mUsername.setError("Usrname tidak boleh kosong");
                }else if(TextUtils.isEmpty(password)){
                    mPassword.setError("Usrname tidak boleh kosong");
                }else if(TextUtils.isEmpty(pekerjaan)){
                    mPekerjaan.setError("Pekerjaan tidak boleh kosong");
                }else{
                    cekRegister(nama, jk, tglLahir, alamat, nik, username, password, pekerjaan, foto);
                }
            }
        });
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setBirth();
        }

    };

    private void setBirth() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mtglLahir.setText(sdf.format(myCalendar.getTime()));
    }

    public void cekRegister(final String nama, final  String jk, final  String tanggallahir, final String alamat,
                            final  String nik, final String username, final String password, final String pekerjaan,
                            final String foto){
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Mengecek Data...");
        showDialog();
        final APIServices services = APIClient.getRetrofit().create(APIServices.class);

        Call<AccountResult> acc = services.getAcc();
        acc.enqueue(new Callback<AccountResult>() {
            @Override
            public void onResponse(Call<AccountResult> call, Response<AccountResult> response) {
                if (response.isSuccessful()){
                    List<Account> list = response.body().getAcc();
                    int jml = response.body().getAcc().size();
                    int conf = 1;
                    if (jml>0){
                        for (int i=0;i<jml;i++){
                            System.out.println(list.get(i).getUsername());
                            if (list.get(i).getUsername().equals(username)){
                                System.out.println(username);
                                conf = 0;
                            }
                        }
                    }

                    if(conf == 0){
                        hideDialog();
                        mUsername.setError("Username telah terdaftar");
                        Toast.makeText(Register.this, "Username telah terdaftar " , Toast.LENGTH_SHORT).show();

                    }else if(conf ==1){
                        String role="penduduk";
                        services.regrister(nama, jk, tanggallahir, alamat, nik, username, password, pekerjaan, foto, role).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()) {
                                    try {
                                        JSONObject jsonResult = new JSONObject(response.body().string());
                                        if (jsonResult.getString("success").equals("1")) {
                                            hideDialog();
                                            Toast.makeText(mContext, "Register Berhasil, Silahkan Login",
                                                    Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Register.this, Login.class);
                                            startActivity(intent);
                                        } else {
                                            hideDialog();
                                            String error_message = jsonResult.getString("message");
                                            Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException | IOException e) {
                                        hideDialog();
                                        e.printStackTrace();
                                    }
                                }else{
                                    hideDialog();
                                    Toast.makeText(Register.this, response.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                hideDialog();
                                Log.d("error",t.getMessage());
                                Toast.makeText(Register.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }else{
                    hideDialog();
                    Toast.makeText(Register.this, response.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AccountResult> call, Throwable t) {
                hideDialog();
                Log.d("error",t.getMessage());
                Toast.makeText(Register.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
