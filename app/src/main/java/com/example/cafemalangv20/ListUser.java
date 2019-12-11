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
import com.example.cafemalangv20.Model.User;
import com.example.cafemalangv20.api.RetrofitServer;
import com.example.cafemalangv20.api.ServiceApi;

import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUser extends AppCompatActivity {

    ServiceApi mServiceApi;

    EditText txtIdUser, txtNama, txtEmail, txtUser, txtPassword, txtlevel;
    List<User> Users;
    ListView listViewUser;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        initViews();

        mServiceApi = RetrofitServer.getClient().create(ServiceApi.class);
        Call<List<User>> userCall = mServiceApi.getUser();

        Log.d("TAG", "on create");

        userCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.d("TAG", "on response");
                //creating Userlist adapter
                AdapterListUser UserAdapter = new AdapterListUser(ListUser.this, response.body());
                //attaching adapter to the listview
                listViewUser.setAdapter(UserAdapter);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("TAG", "on failure");
                Log.d("TAG", t.toString());
            }
        });

        listViewUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //show detail
                User User = Users.get(i);
                CallUpdateAndDeleteDialog(User.getId_user(), User.getNama(), User.getEmail(), User.getUsername(), User.getPassword(), User.getLevel());
            }
        });

    }

    private void initViews() {
        listViewUser = findViewById(R.id.listViewUser);
    }

    private void CallUpdateAndDeleteDialog(final String id_user, final String nama, final String email, final String username, String password, String level) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ListUser.this);

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_popup_user, null);
        dialogBuilder.setView(dialogView);

        //Access Dialog views for User
//        txtIdUser = dialogView.findViewById(R.id.id_user);
        txtNama = dialogView.findViewById(R.id.nama_user);
        txtEmail = dialogView.findViewById(R.id.email);
        txtUser = dialogView.findViewById(R.id.username);
        txtPassword = dialogView.findViewById(R.id.password);
        txtlevel = dialogView.findViewById(R.id.level);

        final Button btnUpdate = dialogView.findViewById(R.id.btnUpdate);
        final Button btnDelete = dialogView.findViewById(R.id.btnDelete);

        dialogBuilder.setTitle("Please fill with your data");
        final AlertDialog b = dialogBuilder.create();
        b.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = txtNama.getText().toString().trim();
                String email = txtEmail.getText().toString().trim();
                String username = txtUser.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String level = txtlevel.getText().toString().trim();

                if (!TextUtils.isEmpty(nama)) {
                    if (!TextUtils.isEmpty(email)) {
                        if (!TextUtils.isEmpty(username)) {
                            if (!TextUtils.isEmpty(password)) {
                                if (!TextUtils.isEmpty(level)) {
                                    updateUser(id_user, nama, email, username, password, level);
                                    b.dismiss();
                                }
                            }
                            }
                        }
                    btnDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            deleteUser(id_user);
                            b.dismiss();
                        }
                    });
                    }
                }

        });
    }

    public boolean addUser() {
//        String id_user = txtIdUser.getText().toString().trim();
        String nama = txtNama.getText().toString().trim();
        String email = txtEmail.getText().toString().trim();
        String username = txtUser.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        String level = txtlevel.getText().toString().trim();


        if (!TextUtils.isEmpty(nama)) {
            if (!TextUtils.isEmpty(email)){
                if (!TextUtils.isEmpty(username)) {
                    if (!TextUtils.isEmpty(password)) {
                        if (!TextUtils.isEmpty((level))){
                            Call<User> userCall = mServiceApi.postUser("", nama, email, username, password, level);
                            userCall.enqueue(new Callback<User>() {
                                @Override
                                public void onResponse(Call<User> call, Response<User> response) {
                                    Log.d("TAG", "Post success");
                                    Log.d("TAG", response.body().toString());
                                    txtIdUser.setText("");
                                    txtNama.setText("");
                                    txtEmail.setText("");
                                    txtUser.setText("");
                                    txtPassword.setText("");
                                    txtlevel.setText("");
                                    Toast.makeText(getApplicationContext(), "User added", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onFailure(Call<User> call, Throwable t) {
                                    Log.d("TAG", "Post failure");

                                }
                            });
                        }
                    }
                } else {
                    Toast.makeText(this, "Masukkan Nama", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Masukkan Email", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Masukkan Username", Toast.LENGTH_LONG).show();
        }
        return  true;
    }

    public void updateUser(String id_user, String nama, String email, String username, String password, String level) {

    }

    public void deleteUser(String id_user) {

    }
}
