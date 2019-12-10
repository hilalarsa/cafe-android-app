package com.example.cafemalangv20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnProfile, btnMenu, btnMeja, btnUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    public void initView(){
        btnProfile = findViewById(R.id.btnProfile);
        btnMenu = findViewById(R.id.btnMenu);
        btnMeja = findViewById(R.id.btnMeja);
        btnUser = findViewById(R.id.btnUser);
    }

    public void initListener(){
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this method is actually performing the write operation
                addProfile();
            }
        });
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this method is actually performing the write operation
                Intent intent = new Intent(MainActivity.this, ListMenu.class);
                startActivity(intent);
//                addMenu();
            }
        });
        btnMeja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this method is actually performing the write operation
                Intent intent2 = new Intent(MainActivity.this, ListMeja.class);
                startActivity(intent2);
//                addMenu();
            }
        });
    }

    public void addProfile(){

    }
    public void addMenu(){

    }

}
