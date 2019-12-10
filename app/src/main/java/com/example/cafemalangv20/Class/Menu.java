package com.example.cafemalangv20.Class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Menu {

    @SerializedName("id_menu")
    @Expose
    private String id_menu;
    @SerializedName("nama_menu")
    @Expose
    private String nama_menu;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("harga")
    @Expose
    private String harga;
    @SerializedName("gambar")
    @Expose
    private String gambar;

    List<Menu> listMenu;

    public Menu(){ }

    public Menu(String id_menu, String nama_menu, String deskripsi, String harga, String gambar){
        this.id_menu = id_menu;
        this.nama_menu = nama_menu;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.gambar = gambar;
    }

    public List<Menu> getAllMenu(){
        return listMenu;
    }

    public String getIdMenu() {
        return id_menu;
    }
    public void setIdMenu(String id_menu) {
        this.id_menu = id_menu;
    }

    public String getNamaMenu() {
        return nama_menu;
    }
    public void setNamaMenu(String nama_menu) {
        this.nama_menu = nama_menu;
    }

    public String getDeskripsi() {
        return deskripsi;
    }
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getHarga() {
        return harga;
    }
    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getGambar() {return gambar;}
    public void setGambar(String gambar) {this.gambar = gambar;}


}