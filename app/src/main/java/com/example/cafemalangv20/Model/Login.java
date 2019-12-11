package com.example.cafemalangv20.Model;

import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("id_user")
    private String id_user;

    public Login(String username, String password, String id_user) {
        this.username = username;
        this.password = password;
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
}
