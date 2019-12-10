package com.example.cafemalangv20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnProfile, btnMenu, btnMeja, btnUser, btnCafe;
    TextView tvUsername;
    String svUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUsername=(TextView)findViewById(R.id.tvUsername);

        //shared prev
        Bundle sv = getIntent().getExtras();
        SharedPreferences handler = this.getSharedPreferences("data_login", Context.MODE_PRIVATE);
        tvUsername.setText(sv.getCharSequence("username"));

        initView();
        initListener();
    }

    public void initView(){
        btnProfile = findViewById(R.id.btnProfile);
        btnMenu = findViewById(R.id.btnMenu);
        btnMeja = findViewById(R.id.btnMeja);
        btnUser = findViewById(R.id.btnUser);
        btnCafe = findViewById(R.id.btnCafe);
    }

    public void initListener(){
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this method is actually performing the write operation
                Intent intent = new Intent(MainActivity.this, ListCafe.class);
                startActivity(intent);
            }
        });
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this method is actually performing the write operation
                Intent intent = new Intent(MainActivity.this, ListMenu.class);
                startActivity(intent);
                //addMenu();
            }
        });
    }

    public void addProfile(){

    }
    public void addMenu(){

    }

    //menu logout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menuLogout:
                SharedPreferences setting = getSharedPreferences("key", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = setting.edit();
                editor.remove("username");
                editor.remove("password");
                editor.commit();
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
