package com.example.cafemalangv20;

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

import com.example.cafemalangv20.Model.Meja;
import com.example.cafemalangv20.Model.Menu;
import com.example.cafemalangv20.api.RetrofitServer;
import com.example.cafemalangv20.api.ServiceApi;

import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMeja extends AppCompatActivity {

    ServiceApi mServiceApi;

    EditText txtNoMeja, txtJumlahKursi, txtStatus;
    List<Meja> Mejas;
    ListView listViewMeja;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meja);
        initViews();

        mServiceApi = RetrofitServer.getClient().create(ServiceApi.class);
        Call<List<Meja>> mejaCall = mServiceApi.getMeja();

        Log.d("TAG", "on create");

        mejaCall.enqueue(new Callback<List<Meja>>() {
            @Override
            public void onResponse(Call<List<Meja>> call, Response<List<Meja>> response) {
                Log.d("TAG", "on response");
                //creating Userlist adapter
                AdapterListMeja MejaAdapter = new AdapterListMeja(ListMeja.this, response.body());
                //attaching adapter to the listview
                listViewMeja.setAdapter(MejaAdapter);
            }

            @Override
            public void onFailure(Call<List<Meja>> call, Throwable t) {
                Log.d("TAG", "on failure");
                Log.d("TAG", t.toString());
            }
        });

        listViewMeja.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //show detail
                Meja Meja = Mejas.get(i);
                CallUpdateAndDeleteDialog(Meja.getIdMeja(), Meja.getNoMeja(), Meja.getJumlahKursi(), Meja.getStatus(), Meja.getIdUser());
            }
        });

    }

    private void initViews() {
        listViewMeja = findViewById(R.id.listViewMeja);
    }

    private void CallUpdateAndDeleteDialog(final String id_meja, final String no_meja, final String jumlah_kursi, final String status, String id_user) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ListMeja.this);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_popup_meja, null);
        dialogBuilder.setView(dialogView);

        //Access Dialog views for Meja
        txtNoMeja = dialogView.findViewById(R.id.no_meja);
        txtJumlahKursi = dialogView.findViewById(R.id.jumlah_kursi);
        txtStatus = dialogView.findViewById(R.id.status);

        txtNoMeja.setText(no_meja);
        txtJumlahKursi.setText(jumlah_kursi);
        txtStatus.setText(status);

        final Button btnUpdate = dialogView.findViewById(R.id.btnUpdate);
        final Button btnDelete = dialogView.findViewById(R.id.btnDelete);

        dialogBuilder.setTitle("Please fill with your data");
        final AlertDialog b = dialogBuilder.create();
        b.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String no_meja = txtNoMeja.getText().toString().trim();
                String jumlah_kursi = txtJumlahKursi.getText().toString().trim();
                String status = txtStatus.getText().toString().trim();
//                String gambar = txtGambar.getText().toString().trim();

                if (!TextUtils.isEmpty(no_meja)) {
                    if (!TextUtils.isEmpty(jumlah_kursi)) {
                        if (!TextUtils.isEmpty(status)) {
                            updateMeja(id_meja, no_meja, status);
                            b.dismiss();
                        }
                    }
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMeja(id_meja);
                b.dismiss();
            }
        });

    }

    public boolean addMeja() {
        String no_meja = txtNoMeja.getText().toString().trim();
        String jumlah_kursi = txtJumlahKursi.getText().toString().trim();
        String status = txtStatus.getText().toString().trim();


        if (!TextUtils.isEmpty(no_meja)) {
            if (!TextUtils.isEmpty(jumlah_kursi)) {
                if (!TextUtils.isEmpty(status)) {
                    Call<Meja> mejaCall = mServiceApi.postMeja("", no_meja, jumlah_kursi, status, "1");
                    mejaCall.enqueue(new Callback<Meja>() {
                        @Override
                        public void onResponse(Call<Meja> call, Response<Meja> response) {
                            Log.d("TAG", "Post success");
                            Log.d("TAG", response.body().toString());
                            txtNoMeja.setText("");
                            txtJumlahKursi.setText("");
                            txtStatus.setText("");
                            Toast.makeText(getApplicationContext(), "Menu added", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<Meja> call, Throwable t) {
                            Log.d("TAG", "Post failure");

                        }
                    });
                } else {
                    Toast.makeText(this, "Masukkan Status", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Masukkan Jumlah Kursi", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Masukkan No Meja", Toast.LENGTH_LONG).show();
        }
        return  true;
    }


    public void updateMeja(String id_meja, String no_meja, String status) {

    }

    public void deleteMeja(String id_meja) {

    }
}
