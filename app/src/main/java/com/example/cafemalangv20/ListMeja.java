package com.example.cafemalangv20;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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

        Log.d("TAG","on create");

        mejaCall.enqueue(new Callback<List<Meja>>() {
            @Override
            public void onResponse(Call<List<Meja>> call, Response<List<Meja>> response) {
                Log.d("TAG","on response");
                //creating Userlist adapter
                AdapterListMeja MenuAdapter = new AdapterListMenu(ListMeja.this, response.body());
                //attaching adapter to the listview
                listViewMeja.setAdapter(MejaAdapter);
            }
            @Override
            public void onFailure(Call<List<Meja>> call, Throwable t) {
                Log.d("TAG","on failure");
                Log.d("TAG",t.toString());
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

    private void CallUpdateAndDeleteDialog(final String id_meja, final String no_meja, final String jumlah_kursi, final String status, String id_user){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ListMeja.this);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_popup_meja, null);
        dialogBuilder.setView(dialogView);

    }

    }
}
