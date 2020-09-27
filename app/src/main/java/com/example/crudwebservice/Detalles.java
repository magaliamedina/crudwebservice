package com.example.crudwebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Detalles extends AppCompatActivity {
    TextView tvnombre, tvemail, tvcontacto, tvdireccion,tvid;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        tvid = findViewById(R.id.txtid);

        tvnombre = findViewById(R.id.txtnombre);
        tvemail = findViewById(R.id.txtemail);
        tvcontacto = findViewById(R.id.txtcontacto);
        tvdireccion = findViewById(R.id.txtdireccion);

        //recibimos los parametros de Home
        Intent intent=getIntent();
        position= intent.getExtras().getInt("position");

        tvid.setText("ID " + Home.users.get(position).getId());
        tvnombre.setText("Nombre " + Home.users.get(position).getNombre());
        tvemail.setText("Email " + Home.users.get(position).getEmail());
        tvcontacto.setText("Contacto " + Home.users.get(position).getContacto());
        tvdireccion.setText("Dirección " + Home.users.get(position).getDireccion());

    }
}
