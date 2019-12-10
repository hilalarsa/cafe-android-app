package com.example.cafemalangv20;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cafemalangv20.Model.Cafe;

import java.util.List;

public class AdapterListCafe extends ArrayAdapter<Cafe> {
    private Activity context;
    //list of users
    List<Cafe> Cafes;

    public AdapterListCafe(Activity context, List<Cafe> Cafes) {
        super(context, R.layout.layout_cafe_list, Cafes);
        this.context = context;
        this.Cafes = Cafes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_cafe_list, null, true);
        //initialize
        TextView textViewNamaCafe = (TextView) listViewItem.findViewById
                (R.id.textViewNamaCafe);
        TextView textViewLokasi = (TextView) listViewItem.findViewById
                (R.id.textViewLokasi);
        //getting user at position
        Cafe Cafe = Cafes.get(position);
        //set user name
        com.example.cafemalangv20.Model.Cafe.setText(com.example.cafemalangv20.Model.Cafe.getNamaCafe());
        //set user email
        textViewLokasi.setText(com.example.cafemalangv20.Model.Cafe.getLokasi());
        return listViewItem;
    }
}