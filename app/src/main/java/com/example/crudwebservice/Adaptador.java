package com.example.crudwebservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class Adaptador extends ArrayAdapter<Users> {


    Context context;
    List<Users>arrayListUsers;
    public Adaptador(@NonNull Context context,List<Users>arrayListUsers) {
        //se crea archivo xml my_list_item
        super(context, R.layout.my_list_item, arrayListUsers);

        this.context = context;
        this.arrayListUsers=arrayListUsers;
    }

    @NonNull
    @Override
    public View getView (int position, @NonNull View convertView, @NonNull ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_item, null, true);
        TextView tvid= view.findViewById(R.id.tvid);
        TextView tvnombre = view.findViewById(R.id.tvnombre);

        tvid.setText(arrayListUsers.get(position).getId());
        tvnombre.setText(arrayListUsers.get(position).getNombre());
        return view;

    }
}
