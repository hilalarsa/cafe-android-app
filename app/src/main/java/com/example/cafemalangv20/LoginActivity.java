package com.example.cafemalangv20;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cafemalangv20.Model.Get.GetUser;
import com.example.cafemalangv20.api.RetrofitServer;
import com.example.cafemalangv20.api.ServiceApi;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button btLogin;
    ServiceApi mApiInterface;
    String svUsername, svId_user;

    public static final String PREFS = "examplePrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btLogin = (Button) findViewById(R.id.btLogin);
    }

    public void buttonClick(View view){
        this.login();
    }

    private void login() {
        String username = this.etUsername.getText().toString();
        String password = this.etPassword.getText().toString();
        this.checkCredentials(username, password);
    }

    private void openHome() {
        Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
        intent.putExtra("username", svUsername);
        intent.putExtra("id_user", svId_user);
        this.startActivity(intent);
    }

    private void saveCredentials() {
        SharedPreferences handler = this.getSharedPreferences("data_login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = handler.edit();

        editor.putString("username", this.etUsername.getText().toString());
        editor.apply();
    }

    private void checkCredentials(String username, String password) {
        mApiInterface = RetrofitServer.getClient().create(ServiceApi.class);
        RequestBody reqUsername = MultipartBody.create(MediaType.parse("multipart/form-data"),
                (username.toString().isEmpty())?"":username.toString());
        RequestBody reqPassword = MultipartBody.create(MediaType.parse("multipart/form-data"),
                (password.toString().isEmpty())?"":password.toString());

        Call<GetUser> mLoginCall = mApiInterface.postLogin(reqUsername, reqPassword);
        mLoginCall.enqueue(new Callback<GetUser>() {
            @Override
            public void onResponse(Call<GetUser> call, Response<GetUser> response) {
                Log.d("Login", response.body().getStatus()+" "+response.body().getResult());
                if (response.body().getStatus().equals("kamu salah")){
                    //alert gagal login
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("");
                    builder.setMessage("Username atau Password salah");
                    builder.setCancelable(false);

                    builder.setNegativeButton("OKE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alert=builder.create();
                    alert.show();
                }else{
                    svUsername = response.body().getUsername();
                    svId_user = response.body().getResult();
                    saveCredentials();
                    openHome();
                }
            }
            @Override
            public void onFailure(Call<GetUser> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Tidak terhubung server", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
