package com.example.cafemalangv20;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.cafemalangv20.Class.Menu;
import com.example.cafemalangv20.api.RetrofitServer;
import com.example.cafemalangv20.api.ServiceApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMenu extends AppCompatActivity {

    ServiceApi mServiceApi;

    List<Menu> Menus;
    ListView listViewMenu;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu);
        initViews();

        mServiceApi = RetrofitServer.getClient().create(ServiceApi.class);
        Call<List<Menu>> menuCall = mServiceApi.getMenu();


        Log.d("TAG","on create");

        menuCall.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                Log.d("TAG","on response");
                //creating Userlist adapter
                AdapterListMenu MenuAdapter = new AdapterListMenu(ListMenu.this, response.body());
                //attaching adapter to the listview
                listViewMenu.setAdapter(MenuAdapter);
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                Log.d("TAG","on failure");
                Log.d("TAG",t.toString());
            }
        });

        listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //show detail
                Menu Menu = Menus.get(i);
//                CallUpdateAndDeleteDialog(Matkul.getMatkulId(), Matkul.getMatkulName(), Matkul.getSks(), Matkul.getMatkulImage(), key);
            }
        });

    }

    private void initViews() {
        listViewMenu = findViewById(R.id.listViewMenu);
    }

}
