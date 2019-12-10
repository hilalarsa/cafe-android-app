package com.example.cafemalangv20.Model.Get;

import com.google.gson.annotations.SerializedName;

public class GetUser {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private String result;
    @SerializedName("id_user")
    private String id_user;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }

    public String getId_user() {
        return id_user;
    }

}
