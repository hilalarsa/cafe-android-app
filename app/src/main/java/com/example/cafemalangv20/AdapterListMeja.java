package com.example.cafemalangv20;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cafemalangv20.Model.Meja;

import java.util.List;

class AdapterListMeja extends ArrayAdapter<Meja> {
    private Activity context;
    //list of users
    List<Meja> Mejas;

    public AdapterListMeja(Activity context, List<Meja> Mejas) {
        super(context, R.layout.layout_meja_list, Mejas);
        this.context = context;
        this.Mejas = Mejas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_meja_list, null, true);
        //initialize
        TextView textViewNoMeja = (TextView) listViewItem.findViewById
                (R.id.textViewNoMeja);
//        TextView textViewJumlahKursi = (TextView) listViewItem.findViewById
//                (R.id.jumlah_kursi);
        TextView textViewStatus = (TextView) listViewItem.findViewById
                (R.id.textViewStatus);
        //getting user at position
        Meja Meja = Mejas.get(position);
        //set user name
        textViewNoMeja.setText(Meja.getNoMeja());
        //set user email
//        textViewJumlahKursi.setText(Meja.getJumlahKursi());
        textViewStatus.setText(Meja.getStatus());
        return listViewItem;
    }
}
