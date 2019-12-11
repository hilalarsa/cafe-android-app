package com.example.cafemalangv20;

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

import com.example.cafemalangv20.Model.Cafe;
import com.example.cafemalangv20.Model.Menu;
import com.example.cafemalangv20.api.RetrofitServer;
import com.example.cafemalangv20.api.ServiceApi;

import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListCafe extends AppCompatActivity {

    ServiceApi mServiceApi;

    EditText txtNamaCafe, txtLokasi, txtDeskripsi;
    List<Menu> Cafes;
    ListView listViewCafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cafe);
        initViews();

        mServiceApi = RetrofitServer.getClient().create(ServiceApi.class);
        Call<List<Cafe>> cafeCall = mServiceApi.getCafe();

        cafeCall.enqueue(new Callback<List<Cafe>>() {
            @Override
            public void onResponse(Call<List<Cafe>> call, final Response<List<Cafe>> response) {
                Log.d("TAG","on response");
                //creating Userlist adapter
                AdapterListCafe cafeAdapter = new AdapterListCafe(ListCafe.this, response.body());
                //attaching adapter to the listview
                listViewCafe.setAdapter(cafeAdapter);
                listViewCafe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //show detail
                        Cafe cafe = response.body().get(i);
                        CallUpdateAndDeleteDialog(cafe.getIdCafe(), cafe.getNamaCafe(), cafe.getLokasi(), cafe.getDeskripsi(), cafe.getIdCafe());
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Cafe>> call, Throwable t) {
                Log.d("TAG","on failure");
                Log.d("TAG",t.toString());
            }
        });



    }

    private void initViews() {
        listViewCafe = findViewById(R.id.listViewCafe);
    }

    private void CallUpdateAndDeleteDialog(final String id_cafe, final String nama_cafe, final String lokasi, final String deskripsi, String id_user){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ListCafe.this);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_popup_cafe, null);
        dialogBuilder.setView(dialogView);

        //Access Dialog views for Menu
        txtNamaCafe = dialogView.findViewById(R.id.updateTextCafe);
        txtLokasi = dialogView.findViewById(R.id.updateTextLokasi);
        txtDeskripsi = dialogView.findViewById(R.id.txtDeskripsi);


        txtNamaCafe.setText(nama_cafe);
        txtLokasi.setText(lokasi);
        txtDeskripsi.setText(deskripsi);



        final Button btnUpdate = dialogView.findViewById(R.id.btnUpdate);
        final Button btnDelete = dialogView.findViewById(R.id.btnDelete);

        dialogBuilder.setTitle("Please fill with your data");
        final AlertDialog b = dialogBuilder.create();
        b.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama_cafe = txtNamaCafe.getText().toString().trim();
                String lokasi = txtLokasi.getText().toString().trim();
                String deskripsi = txtDeskripsi.getText().toString().trim();


                if (!TextUtils.isEmpty(nama_cafe)) {
                    if (!TextUtils.isEmpty(lokasi)) {
                        if (!TextUtils.isEmpty(deskripsi)){
                                //Method for update data
                                updateCafe(id_cafe, nama_cafe, lokasi, deskripsi);
                                b.dismiss();
                            }
                        }
                    }
                }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCafe(id_cafe);
                b.dismiss();
            }
        });
    }

    public boolean updateCafe(String id_cafe, String nama_cafe, String lokasi, String deskripsi){
        Toast.makeText(getApplicationContext(), "Cafe Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    public boolean deleteCafe(String id_cafe){
        Toast.makeText(getApplicationContext(), "Cafe Deleted", Toast.LENGTH_LONG).show();
        return true;
    }

}
