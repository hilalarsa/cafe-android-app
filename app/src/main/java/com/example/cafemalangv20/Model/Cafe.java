package com.example.cafemalangv20.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cafe {
    @SerializedName("id_cafe")
    @Expose
    private String id_cafe;
    @SerializedName("nama_cafe")
    @Expose
    private String nama_cafe;
    @SerializedName("lokasi")
    @Expose
    private String lokasi;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;

    @SerializedName("id_user")
    @Expose
    private String id_user;

    List<Cafe> listCafe;

    public Cafe(){ }

    public Cafe(String id_cafe, String nama_cafe, String lokasi, String deskripsi, String id_user){
        this.id_cafe = id_cafe;
        this.nama_cafe = nama_cafe;
        this.lokasi = lokasi;
        this.deskripsi = deskripsi;
        this.id_user = id_user;
    }

    public List<Cafe> getAllCafe(){
        return listCafe;
    }

    public String getIdCafe() {
        return id_cafe;
    }
    public void setIdCafe(String id_cafe) {
        this.id_cafe = id_cafe;
    }

    public String getNamaCafe() {
        return nama_cafe;
    }
    public void setNamaCafe(String nama_cafe) {
        this.nama_cafe = nama_cafe;
    }

    public String getLokasi() {
        return lokasi;
    }
    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }


    public String getIdUser() {return id_user;}
    public void setIdUser(String id_user) {this.id_user = id_user;}

}
