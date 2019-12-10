package com.example.cafemalangv20.api;

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
    Call<Menu> deleteMenu(@Field("id_menu") String id_menu);

//    Meja
    
}
