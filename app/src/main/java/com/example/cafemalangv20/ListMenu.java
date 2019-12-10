package com.example.cafemalangv20;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.cafemalangv20.Model.Menu;
import com.example.cafemalangv20.api.RetrofitServer;
import com.example.cafemalangv20.api.ServiceApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMenu extends AppCompatActivity {

    ServiceApi mServiceApi;

    EditText txtNamaMenu, txtDeskripsi, txtHarga, txtGambar;
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
                CallUpdateAndDeleteDialog(Menu.getIdMenu(), Menu.getNamaMenu(), Menu.getDeskripsi(), Menu.getHarga(), Menu.getGambar(), Menu.getIdUser());
            }
        });

    }

    private void initViews() {
        listViewMenu = findViewById(R.id.listViewMenu);
    }

    private void CallUpdateAndDeleteDialog(final String id_menu, final String nama_menu, final String deskripsi, final String harga, final String gambar, String id_user){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ListMenu.this);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_popup_menu, null);
        dialogBuilder.setView(dialogView);

        //Access Dialog views for Menu
        txtNamaMenu = dialogView.findViewById(R.id.txtNamaMenu);
        txtDeskripsi = dialogView.findViewById(R.id.txtDeskripsi);
        txtHarga = dialogView.findViewById(R.id.txtHarga);
        txtGambar = dialogView.findViewById(R.id.txtGambar);

        txtNamaMenu.setText(nama_menu);
        txtDeskripsi.setText(deskripsi);
        txtHarga.setText(harga);
        txtGambar.setText(gambar);

        final Button btnUpdate = dialogView.findViewById(R.id.btnUpdate);
        final Button btnDelete = dialogView.findViewById(R.id.btnDelete);

        dialogBuilder.setTitle("Please fill with your data");
        final AlertDialog b = dialogBuilder.create();
        b.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama_menu = txtNamaMenu.getText().toString().trim();
                String deskripsi = txtDeskripsi.getText().toString().trim();
                String harga = txtHarga.getText().toString().trim();
                String gambar = txtGambar.getText().toString().trim();

                if (!TextUtils.isEmpty(nama_menu)) {
                    if (!TextUtils.isEmpty(deskripsi)) {
                        if (!TextUtils.isEmpty(harga)) {
                            if (!TextUtils.isEmpty(gambar)) {
                                //Method for update data
                                updateMenu(id_menu, nama_menu, deskripsi, harga, gambar);
                                b.dismiss();
                            }
                        }
                    }
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMenu(id_menu);
                b.dismiss();
            }
        });
    }

    public void updateMenu(String id_menu, String nama_menu, String deskripsi, String harga, String gambar){

    }

    public void deleteMenu(String id_menu){

    }

}
