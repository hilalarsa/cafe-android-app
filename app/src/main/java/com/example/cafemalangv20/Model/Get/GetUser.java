package com.example.cafemalangv20.Model.Get;

import com.google.gson.annotations.SerializedName;

public class GetUser {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private String result;
    @SerializedName("username")
    private String username;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }

    public String getUsername() {
        return username;
    }

}
