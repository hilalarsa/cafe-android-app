package com.example.cafemalangv20;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cafemalangv20.Model.Menu;

import java.util.List;

public class AdapterListMenu extends ArrayAdapter<Menu> {
    private Activity context;
    //list of users
    List<Menu> Menus;

    public AdapterListMenu(Activity context, List<Menu> Menus) {
        super(context, R.layout.layout_menu_list, Menus);
        this.context = context;
        this.Menus = Menus;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_menu_list, null, true);
        //initialize
        TextView textViewNamaMenu = (TextView) listViewItem.findViewById
                (R.id.textViewNamaMenu);
        TextView textViewHarga = (TextView) listViewItem.findViewById
                (R.id.textViewHarga);
        //getting user at position
        Menu Menu = Menus.get(position);
        //set user name
        textViewNamaMenu.setText(Menu.getNamaMenu());
        //set user email
        textViewHarga.setText(Menu.getHarga());
        return listViewItem;
    }
}