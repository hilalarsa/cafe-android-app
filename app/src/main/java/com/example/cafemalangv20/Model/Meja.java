package com.example.cafemalangv20.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Meja {
    @SerializedName("id_meja")
    @Expose
    private String id_meja;
    @SerializedName("no_meja")
    @Expose
    private String no_meja;
    @SerializedName("jumlah_kursi")
    @Expose
    private String jumlah_kursi;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("id_user")
    @Expose
    private String id_user;

    List<Meja> listMeja;

    public Meja(){ }

    public Meja(String id_meja, String no_meja, String jumlah_kursi, String status, String id_user){
        this.id_meja = id_meja;
        this.no_meja = no_meja;
        this.jumlah_kursi = jumlah_kursi;
        this.status = status;
        this.id_user = id_user;
    }

    public List<Meja> getAllMeja(){
        return listMeja;
    }

    public String getIdMeja() {
        return id_meja;
    }
    public void setIdMeja(String id_meja) {
        this.id_meja = id_meja;
    }

    public String getNoMeja() {
        return no_meja;
    }
    public void setNoMeja(String no_meja) {
        this.no_meja = no_meja;
    }

    public String getJumlahKursi() {
        return jumlah_kursi;
    }
    public void setJumlahKursi(String jumlah_kursi) {
        this.jumlah_kursi = jumlah_kursi;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdUser() {return id_user;}
    public void setIdUser(String id_user) {this.id_user = id_user;}
}
