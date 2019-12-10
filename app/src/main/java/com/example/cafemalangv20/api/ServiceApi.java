package com.example.cafemalangv20.api;

import com.example.cafemalangv20.Model.Cafe;
import com.example.cafemalangv20.Model.Menu;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ServiceApi {
    String BASE_URL = "http://192.168.43.110:8080/proyek-backup/proyek/index.php/";


    @GET("Rest_menu")
    Call<List<Menu>> getMenu();

    @FormUrlEncoded
    @POST("Rest_menu")
    Call<Menu> postMenu
            (@Field("id_menu") String id_menu, @Field("nama_menu") String nama_menu,
             @Field("deskripsi") String deskripsi, @Field("harga") String harga,
             @Field("gambar") String gambar, @Field("id_user") String id_user);

    @FormUrlEncoded
    @PUT("Rest_menu")
    Call<Menu> putMenu
            (@Field("id_menu") String id_menu, @Field("nama_menu") String nama_menu,
            @Field("deskripsi") String deskripsi, @Field("harga") String harga,
            @Field("gambar") String gambar, @Field("id_user") String id_user);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "Rest_menu", hasBody = true)
    Call<Menu> deleteStok(@Field("id_menu") String id_menu);

//    Meja

    @GET("Rest_cafe")
    Call<List<Cafe>> getCafe();

    @FormUrlEncoded
    @POST("Rest_cafe")
    Call<Cafe> postCafe
            (@Field("id_cafe") String id_cafe, @Field("nama_cafe") String nama_cafe, @Field("lokasi") String lokasi,
             @Field("deskripsi") String deskripsi);

    @FormUrlEncoded
    @PUT("Rest_cafe")
    Call<Cafe> putCafe
            (@Field("id_cafe") String id_cafe, @Field("nama_cafe") String nama_cafe, @Field("lokasi") String lokasi,
             @Field("deskripsi") String deskripsi);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "Rest_cafe", hasBody = true)
    Call<Cafe> deleteCafe(@Field("id_cafe") String id_cafe);
}
