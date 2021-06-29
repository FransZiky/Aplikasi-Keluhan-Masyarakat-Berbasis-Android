package com.example.lapcov_19.network;

import com.example.lapcov_19.entity.EKeluhan;
import com.example.lapcov_19.model.AccountResult;
import com.example.lapcov_19.model.CekKesehatanResult;
import com.example.lapcov_19.model.InformasiResult;
import com.example.lapcov_19.model.KeluhanResult;
import com.google.gson.JsonObject;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface APIServices {
    @GET("/WebServices/Web_Services_Lapcovid19/api/account/index")
    Call<AccountResult> getAcc();

    @FormUrlEncoded
    @POST("/WebServices/Web_Services_Lapcovid19/api/account/login")
    Call<JsonObject> loginReq(@Field("username") String username,
                                @Field("password") String password);

    @FormUrlEncoded
    @POST("/WebServices/Web_Services_Lapcovid19/api/account/register")
    Call<ResponseBody> regrister(@Field("nama") String nama,
                                 @Field("jk") String jk,
                                 @Field("tanggallahir") String tanggallahir,
                                 @Field("alamat") String alamat,
                                 @Field("nik") String nik,
                                 @Field("username") String username,
                                 @Field("password") String password,
                                 @Field("pekerjaan") String pekerjaan,
                                 @Field("foto") String foto,
                                 @Field("role") String role);

//    @FormUrlEncoded
//    @POST("/WebServices/Web_Services_Lapcovid19/api/account/update")
//    Call<ResponseBody> update(@Field("nama") String nama,
//                              @Field("jk") String jk,
//                              @Field("tanggallahir") String tanggallahir,
//                              @Field("alamat") String alamat,
//                              @Field("nik") String nik,
//                              @Field("username") String username,
//                              @Field("password") String password,
//                              @Field("pekerjaan") String pekerjaan,
//                              @Field("foto") String foto);

    @Multipart
    @POST("/WebServices/Web_Services_Lapcovid19/api/account/update")
    Call<ResponseBody> updateProfil(@Part MultipartBody.Part foto,
                                    @PartMap Map<String, RequestBody> text);

    @FormUrlEncoded
    @POST("/WebServices/Web_Services_Lapcovid19/api/cekKesehatan/create")
    Call<ResponseBody> cekKesehata(@Field("gejala") String gejala,
                                   @Field("aktivitasi")String aktivitas,
                                   @Field("username") String username,
                                   @Field("hasil") int hasil);

    @GET("/WebServices/Web_Services_Lapcovid19/api/cekKesehatan/index")
    Call<CekKesehatanResult> getCekKesehatan();

    @GET("/WebServices/Web_Services_Lapcovid19/api/cekKesehatan/search/{username}")
    Call<CekKesehatanResult>  searchCekKesehatan(@Path("username") String username);

    @GET("/WebServices/Web_Services_Lapcovid19/api/informasi/index")
    Call<InformasiResult> getInformasi();

    @FormUrlEncoded
    @POST("/WebServices/Web_Services_Lapcovid19/api/informasi/store")
    Call<ResponseBody> addInfromasi(@Field("judul") String judul,
                                    @Field("tanggal") String tanggal,
                                    @Field("kategori") String kategori,
                                    @Field("isi") String isi);

    @GET("/WebServices/Web_Services_Lapcovid19/api/keluhan/index")
    Call<KeluhanResult> getKeluhan();

    @FormUrlEncoded
    @POST("/WebServices/Web_Services_Lapcovid19/api/keluhan/store")
    Call<ResponseBody> addKeluhan(@Field("pengirim") String pengirim,
                                  @Field("kategori") String kategori,
                                  @Field("tanggal") String tanggal,
                                  @Field("isi") String isi,
                                  @Field("status") String status);

    @GET("/WebServices/Web_Services_Lapcovid19/api/keluhan/search/{username}")
    Call<KeluhanResult> searchKeluhan(@Path("username") String username);

    @DELETE("/WebServices/Web_Services_Lapcovid19/api/keluhan/delete/{id_keluhan}")
    Call<ResponseBody> deleteKeluhan(@Path("id_keluhan") int id_keluhan);

    @FormUrlEncoded
    @POST("/WebServices/Web_Services_Lapcovid19/api/keluhan/update")
    Call<ResponseBody> updateKeluhan(@Field("id_keluhan") int id_keluhan,
                                     @Field("pengirim") String pengirim,
                                     @Field("kategori") String kategori,
                                     @Field("tanggal") String tanggal,
                                     @Field("isi") String isi,
                                     @Field("status") String status);


}
