package com.example.crudwebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Insertar extends AppCompatActivity {

    EditText tvnombre, tvemail, tvcontacto, tvdireccion;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvnombre = findViewById(R.id.tvnombre);
        tvemail = findViewById(R.id.tvemail);
        tvcontacto = findViewById(R.id.tvcontacto);
        tvdireccion = findViewById(R.id.tvdireccion);
        btn1 = findViewById(R.id.btninsertar);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
            }
        });
    }

    private void insertar() {
        final String nombre = tvnombre.getText().toString();
       final String email = tvemail.getText().toString();
        final String contacto = tvcontacto.getText().toString();
        final String direccion = tvdireccion.getText().toString();

        //para detectar errores
        final ProgressDialog progressDialog = new ProgressDialog(this);
        if (nombre.isEmpty()) {
            tvnombre.setError("complete los campos");
        }
        else if (email.isEmpty()) {
            tvemail.setError("complete los campos");
        }
        else if (contacto.isEmpty()) {
            tvcontacto.setError("complete los campos");
        }
        else if (direccion.isEmpty()) {
            tvdireccion.setError("complete los campos");
        } else {
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "http://magaliamedina.000webhostapp.com/crudwebservice/insertar.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Datos insertados")) {
                        Toast.makeText(Insertar.this, "datos ingresados", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    } else {
                        Toast.makeText(Insertar.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            } , new Response.ErrorListener() {
                @Override
                public void onErrorResponse (VolleyError error) {
                    Toast.makeText(Insertar.this, error.getMessage(), Toast.LENGTH_SHORT).show  ();
                    progressDialog.dismiss();
                }
            }) {
                @Override
                //para cargar nuestro datos de la base de datos
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String>params = new HashMap<String, String>();
                    //nombres es el nombre como está almacenado en la base de datos
                    params.put("nombre", tvnombre.getText().toString());
                    params.put("email",tvemail.getText().toString());
                    params.put("contacto",tvcontacto.getText().toString());
                    params.put("direccion",tvdireccion.getText().toString());

                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(Insertar.this);
            requestQueue.add(request);
        }


    }
}
