package com.example.lapcov_19.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lapcov_19.R;
import com.example.lapcov_19.network.APIClient;
import com.example.lapcov_19.network.APIServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditData extends AppCompatActivity {

    EditText mtglLahir, mNama, mJK, mAlamat, mNIK,  mPassword, mPekerjaan;
    ImageView mFoto;
    Calendar myCalendar = Calendar.getInstance();
    Context mContext;
    SharedPreferences sharedpreferences;
    ProgressDialog pDialog;
    FloatingActionButton mLoadFoto;
    private Bitmap bitmap;
    Button simpan;

    public final static String TAG_NAMA = "nama";
    public final static String TAG_JK = "jk";
    public final static String TAG_TANGGALLAHIR = "tanggallahir";
    public final static String TAG_ALAMAT = "alamat";
    public final static String TAG_USERNAME = "username";
    public final static String TAG_PASSWORD = "password";
    public final static String TAG_PEKERJAAN = "perkerjaan";
    public final static String TAG_NIK = "nik";
    public final static String TAG_FOTO = "foto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        mNama = findViewById(R.id.nama);
        mNama.setText(sharedpreferences.getString("nama",null));
        mJK = findViewById(R.id.jk);
        mJK.setText(sharedpreferences.getString("jk",null));
        mAlamat = findViewById(R.id.almt);
        mAlamat.setText(sharedpreferences.getString("alamat",null));
        mNIK = findViewById(R.id.nik);
        mNIK.setText(sharedpreferences.getString("nik",null));
        mtglLahir = findViewById(R.id.tglLhr);
        mtglLahir.setText(sharedpreferences.getString("tanggallahir",null));
        mPassword = findViewById(R.id.inpass);
        mPassword.setText(sharedpreferences.getString("password",null));
        mPekerjaan = findViewById(R.id.pekerjaan);
        mPekerjaan.setText(sharedpreferences.getString("perkerjaan",null));
        mLoadFoto = findViewById(R.id.pLoadPoto);
        simpan = findViewById(R.id.btnSimpan);
        mFoto = findViewById(R.id.pFoto);
        Picasso.get()
                .load("http://192.168.43.202:/WebServices/Web_Services_Lapcovid19/public/FotoProfil/"+sharedpreferences.getString(TAG_FOTO, null))
                .into(mFoto);
        mContext = this;

        mtglLahir.setFocusableInTouchMode(false);
        mtglLahir.setFocusable(false);
        mtglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditData.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        mLoadFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
            }
        });

        // function tombol
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePorfil();
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

    private void chooseFile() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                mFoto.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
//
//    public String getStringImage(Bitmap bmp){
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] imageBytes = baos.toByteArray();
//        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
//        return encodedImage;
//    }

    public File createTempFile(Bitmap bitmap) {
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                System.currentTimeMillis()+"_image.PNG");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG,0, bos);
        byte[] bitmapData = bos.toByteArray();

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapData);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public void updatePorfil(){
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Mengupdate Data...");
        showDialog();
        String jk = mJK.getText().toString().trim();
        String alamat = mAlamat.getText().toString().trim();
        String nik = mNIK.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String tanggallahir = mtglLahir.getText().toString().trim();
        String nama = mNama.getText().toString().trim();
        String pekerjaan = mPekerjaan.getText().toString().trim();

        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("nama", createFormatString(nama));
        map.put("jk", createFormatString(jk));
        map.put("tanggallahir", createFormatString(tanggallahir));
        map.put("alamat", createFormatString(alamat));
        map.put("nik", createFormatString(nik));
        map.put("username", createFormatString(sharedpreferences.getString(TAG_USERNAME, null)));
        map.put("password", createFormatString(password));
        map.put("pekerjaan", createFormatString(pekerjaan));

        File file = null;
        MultipartBody.Part body = null;
        if (bitmap==null){
            file = null;
            body = null;
        }else{
            file = createTempFile(bitmap);
            RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
            body = MultipartBody.Part.createFormData("foto", file.getName(), reqFile);
        }



        APIServices services = APIClient.getRetrofit().create(APIServices.class);
        Call<ResponseBody> call = services.updateProfil(body, map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    hideDialog();
                    try {
                        JSONObject job = new JSONObject(response.body().string());
                        if (job.getString("success").equals("1")){
                            System.out.println("==============================================-----------------------");
                            String jk = mJK.getText().toString().trim();
                            String alamat = mAlamat.getText().toString().trim();
                            String nik = mNIK.getText().toString().trim();
                            String password = mPassword.getText().toString().trim();
                            String tanggallahir = mtglLahir.getText().toString().trim();
                            String nama = mNama.getText().toString().trim();
                            String pekerjaan = mPekerjaan.getText().toString().trim();

                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString(TAG_FOTO,job.getString("foto"));
                            editor.putString(TAG_NAMA, nama);
                            editor.putString(TAG_PASSWORD, password);
                            editor.putString(TAG_NIK, nik);
                            editor.putString(TAG_JK, jk);
                            editor.putString(TAG_TANGGALLAHIR, tanggallahir);
                            editor.putString(TAG_ALAMAT, alamat);
                            editor.putString(TAG_PEKERJAAN, pekerjaan);
                            editor.commit();

                            Toast.makeText(mContext, "Sukses Mengubah Data", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditData.this, Profil.class);
                            startActivity(intent);
                            finish();
                        }
                    }catch (JSONException | IOException e){
                        e.printStackTrace();
                    }
                }else {
                    hideDialog();
                    Toast.makeText(EditData.this,"Something went wrong...Error message: " +response.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                    hideDialog();
                    Log.d("error",t.getMessage());
                    Toast.makeText(EditData.this, "Something went wrong...Error message: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @NonNull
    private RequestBody createFormatString(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
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
