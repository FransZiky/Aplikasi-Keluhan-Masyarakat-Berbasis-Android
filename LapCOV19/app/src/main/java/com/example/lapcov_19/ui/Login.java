package com.example.lapcov_19.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lapcov_19.R;
import com.example.lapcov_19.network.APIClient;
import com.example.lapcov_19.model.AccountResult;
import com.example.lapcov_19.network.APIServices;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public final static String TAG_NAMA = "nama";
    public final static String TAG_JK = "jk";
    public final static String TAG_TANGGALLAHIR = "tanggallahir";
    public final static String TAG_ALAMAT = "alamat";
    public final static String TAG_USERNAME = "username";
    public final static String TAG_PASSWORD = "password";
    public final static String TAG_PEKERJAAN = "perkerjaan";
    public final static String TAG_NIK = "nik";
    public final static String TAG_FOTO = "foto";
    public final static String TAG_ROLE = "role";



    public static final String session_status = "session_status";
    Boolean session = false;
    public static final String my_shared_preferences = "my_shared_preferences";

    ConnectivityManager conMgr;

    SharedPreferences sharedpreferences;

    EditText txt_username, txt_password;
    Button btnLogin;
    Context mContext;
    APIServices mApiServices;
    ProgressDialog pDialog;
    String success;
    String username,password,nama,nik,id_account,alamat,tanggallahir,foto,jk,pekerjaan, role;
    private static final String TAG = Login.class.getSimpleName();


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = this;


        txt_username = (EditText) findViewById(R.id.user);
        txt_password = (EditText) findViewById(R.id.pass);
        btnLogin = (Button) findViewById(R.id.btnlogin);
        TextView daftar = findViewById(R.id.daftar);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });


        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedpreferences.getBoolean(session_status, false);
        nama = sharedpreferences.getString(TAG_NAMA, null);
        username = sharedpreferences.getString(TAG_USERNAME, null);
        password = sharedpreferences.getString(TAG_PASSWORD, null);
        nik = sharedpreferences.getString(TAG_NIK, null);
        jk = sharedpreferences.getString(TAG_JK, null);
        foto = sharedpreferences.getString(TAG_FOTO, null);
        tanggallahir = sharedpreferences.getString(TAG_TANGGALLAHIR, null);
        alamat = sharedpreferences.getString(TAG_ALAMAT, null);
        pekerjaan = sharedpreferences.getString(TAG_PEKERJAAN, null);
        role = sharedpreferences.getString(TAG_ROLE, null);


        System.out.println(session);
        if(session){
            Intent intent = new Intent(Login.this, Dashboard.class);
            intent.putExtra(TAG_NAMA, nama);
            intent.putExtra(TAG_USERNAME, username);
            intent.putExtra(TAG_PASSWORD, password);
            intent.putExtra(TAG_NIK, nik);
            intent.putExtra(TAG_JK, jk);
            intent.putExtra(TAG_FOTO, foto);
            intent.putExtra(TAG_TANGGALLAHIR, tanggallahir);
            intent.putExtra(TAG_ALAMAT, alamat);
            intent.putExtra(TAG_PEKERJAAN, pekerjaan);
            intent.putExtra(TAG_ROLE, role);
            finish();
            startActivity(intent);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txt_username.getText().toString();
                String password = txt_password.getText().toString();
                if(username.equals("") || password.equals("")){
                    Toast.makeText(Login.this, "Username Password Harus Diisi", Toast.LENGTH_SHORT).show();
                }
                else {
                    requestLogin(username, password);
                }
            }

        });

    }

    public void requestLogin(final String username, final String passwrod){
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Logging in ...");
        showDialog();
         APIServices services = APIClient.getRetrofit().create(APIServices.class);

         Call<JsonObject> call = services.loginReq(username,passwrod);

         call.enqueue(new Callback<JsonObject>() {
             @Override
             public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                 Log.e(TAG, "Login Response: " + response.toString());
                 hideDialog();
                 Log.d("res", response.body().toString());

                 try {
                     JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                     success = jsonObject.getString("success");
                     if(success.equals("1")){
                         String username = jsonObject.getString(TAG_USERNAME);
                         String password = jsonObject.getString(TAG_PASSWORD);
                         String nama = jsonObject.getString(TAG_NAMA);
                         String jk = jsonObject.getString(TAG_JK);
                         String tanggallahir = jsonObject.getString(TAG_TANGGALLAHIR);
                         String alamat = jsonObject.getString(TAG_ALAMAT);
                         String pekerjaan = jsonObject.getString(TAG_PEKERJAAN);
                         String nik = jsonObject.getString(TAG_NIK);
                         String foto = jsonObject.getString(TAG_FOTO);
                         String role = jsonObject.getString(TAG_ROLE);

                         Log.e("Successfully Login!", jsonObject.toString());
                         Toast.makeText(getApplicationContext(), jsonObject.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();


                         SharedPreferences.Editor editor = sharedpreferences.edit();
                         editor.putBoolean(session_status, true);
                         editor.putString(TAG_NAMA, nama);
                         editor.putString(TAG_USERNAME, username);
                         editor.putString(TAG_PASSWORD, password);
                         editor.putString(TAG_NIK, nik);
                         editor.putString(TAG_JK, jk);
                         editor.putString(TAG_FOTO, foto);
                         editor.putString(TAG_TANGGALLAHIR, tanggallahir);
                         editor.putString(TAG_ALAMAT, alamat);
                         editor.putString(TAG_PEKERJAAN, pekerjaan);
                         editor.putString(TAG_ROLE, role);
                         editor.commit();

                         Intent intent = new Intent(Login.this, Dashboard.class);
                         intent.putExtra(TAG_NAMA, nama);
                         intent.putExtra(TAG_USERNAME, username);
                         intent.putExtra(TAG_PASSWORD, password);
                         intent.putExtra(TAG_NIK, nik);
                         intent.putExtra(TAG_JK, jk);
                         intent.putExtra(TAG_FOTO, foto);
                         intent.putExtra(TAG_TANGGALLAHIR, tanggallahir);
                         intent.putExtra(TAG_ALAMAT, alamat);
                         intent.putExtra(TAG_PEKERJAAN, pekerjaan);
                         intent.putExtra(TAG_ROLE, role);
                         finish();
                         startActivity(intent);
                     }else{
                         //Toast.makeText(getApplicationContext(),
                         //jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                         Toast.makeText(getApplicationContext(), "Email / Password salah!", Toast.LENGTH_LONG).show();
                     }
                 } catch (JSONException e) {
                     e.printStackTrace();
                 }

             }

             @Override
             public void onFailure(Call<JsonObject> call, Throwable t) {
                 Log.d("error",t.getMessage());
                 Toast.makeText(Login.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();
             }
         });


    }

    private void generateNoticeList(ArrayList<AccountResult> noticeArrayList) {

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
