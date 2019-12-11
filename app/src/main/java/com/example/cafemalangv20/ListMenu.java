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
import android.widget.Toast;

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

    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_menu);
        initViews();
        initListeners();

        mServiceApi = RetrofitServer.getClient().create(ServiceApi.class);
        Call<List<Menu>> menuCall = mServiceApi.getMenu();

        menuCall.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, final Response<List<Menu>> response) {
                Log.d("TAG","on response");

                AdapterListMenu MenuAdapter = new AdapterListMenu(ListMenu.this, response.body());

                listViewMenu.setAdapter(MenuAdapter);
                listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //show detail
                        Menu Menu = response.body().get(i);
                        CallUpdateAndDeleteDialog(Menu.getIdMenu(), Menu.getNamaMenu(), Menu.getDeskripsi(), Menu.getHarga(), Menu.getGambar(), Menu.getIdUser());
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                Log.d("TAG","on failure");
                Log.d("TAG",t.toString());
            }
        });
    }

    private void initViews() {
        listViewMenu = findViewById(R.id.listViewMenu);
        txtNamaMenu = findViewById(R.id.nama_menu);
        txtDeskripsi = findViewById(R.id.deskripsi);
        txtHarga = findViewById(R.id.harga);
        txtGambar = findViewById(R.id.gambar);

        btnAdd = findViewById(R.id.btnAdd);
    }

    private void initListeners() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMenu();
            }
        });
    }

    private void CallUpdateAndDeleteDialog(final String id_menu, final String nama_menu, final String deskripsi, final String harga, final String gambar, String id_user){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ListMenu.this);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_popup_menu, null);
        dialogBuilder.setView(dialogView);

        //Access Dialog views for Menu
        final EditText txtNamaMenu = dialogView.findViewById(R.id.txtNamaMenu);
        final EditText txtDeskripsi = dialogView.findViewById(R.id.txtDeskripsi);
        final EditText txtHarga = dialogView.findViewById(R.id.txtHarga);
        final EditText txtGambar = dialogView.findViewById(R.id.txtGambar);

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

    public boolean addMenu(){
        String nama_menu = txtNamaMenu.getText().toString().trim();
        String deskripsi = txtDeskripsi.getText().toString().trim();
        String harga = txtHarga.getText().toString().trim();
        String gambar = txtGambar.getText().toString().trim();

        if (!TextUtils.isEmpty(nama_menu)) {
            if (!TextUtils.isEmpty(deskripsi)) {
                if (!TextUtils.isEmpty(harga)) {
                    if (!TextUtils.isEmpty(gambar)) {
                        //do add here bruh
                        Call<Menu> menuCall = mServiceApi.postMenu(nama_menu, deskripsi, harga, gambar, "1");
                        menuCall.enqueue(new Callback<Menu>() {
                            @Override
                            public void onResponse(Call<Menu> call, Response<Menu> response) {
                                Log.d("TAG", "Post success");
                                Log.d("TAG", response.body().toString());
                                txtNamaMenu.setText("");
                                txtDeskripsi.setText("");
                                txtHarga.setText("");
                                txtGambar.setText("");
                                Toast.makeText(getApplicationContext(), "Menu added", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<Menu> call, Throwable t) {
                                Log.d("TAG", "Post failure");
                            }
                        });
                    } else {
                        Toast.makeText(this, "Masukkan Gambar", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "Masukkan Harga", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Masukkan Deskripsi", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Masukkan Nama Menu", Toast.LENGTH_LONG).show();
        }

        Toast.makeText(getApplicationContext(), "Menu Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    public boolean updateMenu(String id_menu, String nama_menu, String deskripsi, String harga, String gambar){
        Call<Menu> menuCall = mServiceApi.putMenu(nama_menu, deskripsi, harga, gambar, "1");
        menuCall.enqueue(new Callback<Menu>() {
            @Override
            public void onResponse(Call<Menu> call, Response<Menu> response) {
                Log.d("TAG", "Put success");
                Log.d("TAG", response.body().toString());
                txtNamaMenu.setText("");
                txtDeskripsi.setText("");
                txtHarga.setText("");
                txtGambar.setText("");
            }

            @Override
            public void onFailure(Call<Menu> call, Throwable t) {
                Log.d("TAG", "Put failure");
                Log.d("TAG", t.toString());
            }
        });
        Toast.makeText(getApplicationContext(), "Menu Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    public boolean deleteMenu(String id_menu){
        Call<Menu> menuCall = mServiceApi.deleteMenu(id_menu);
        menuCall.enqueue(new Callback<Menu>() {
            @Override
            public void onResponse(Call<Menu> call, Response<Menu> response) {
                Log.d("TAG", "Put success");
                Log.d("TAG", response.body().toString());
                Toast.makeText(getApplicationContext(), "Menu Deleted", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Menu> call, Throwable t) {
                Log.d("TAG", "Delete failure");
                Log.d("TAG", t.toString());
            }
        });
        return true;
    }

}
