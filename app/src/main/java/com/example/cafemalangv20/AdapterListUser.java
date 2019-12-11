package com.example.cafemalangv20;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cafemalangv20.Model.Meja;
import com.example.cafemalangv20.Model.User;

import java.util.List;

public class AdapterListUser extends ArrayAdapter<User> {

    private Activity context;
    //list of users
    List<User> Users;

    public AdapterListUser(Activity context, List<User> Users) {
        super(context, R.layout.layout_user_list, Users);
        this.context = context;
        this.Users = Users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_user_list, null, true);
        //initialize
        TextView textViewNoUser = (TextView) listViewItem.findViewById
                (R.id.textViewNoMeja);
//        TextView textViewJumlahKursi = (TextView) listViewItem.findViewById
//                (R.id.jumlah_kursi);
        TextView textViewStatus = (TextView) listViewItem.findViewById
                (R.id.textViewStatus);
        //getting user at position
        User User = Users.get(position);
        //set user name
        textViewNoUser.setText(User.getId_user());
        //set user email
//        textViewJumlahKursi.setText(User.getJumlahKursi());
        textViewStatus.setText(User.getUsername());
        return listViewItem;
    }
}
