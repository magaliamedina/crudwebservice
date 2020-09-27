package com.example.crudwebservice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    private ListView list;
    Adaptador adaptador;
    public static ArrayList<Users>users= new ArrayList<>();
    String url="https://magaliamedina.000webhostapp.com/crudwebservice/mostrar.php";
    Users usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        list = findViewById(R.id.listview);
        adaptador= new Adaptador(this, users);
        list.setAdapter(adaptador);

        //items para editar, eliminar y ver detalles
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog=new ProgressDialog(view.getContext());

                CharSequence[] dialogoItem={"Ver datos","Eliminar datos", "Editar datos"};
                builder.setTitle(users.get(position).getNombre());
                builder.setItems(dialogoItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i) {

                            case 0:
                                //pasamos position para poder recibir en detalles
                                startActivity(new Intent(getApplicationContext(), Detalles.class)
                                        .putExtra("position",position));
                                break;
                            case 1:
                                break;
                            case 2:
                                break;

                        }
                    }
                });
                builder.create().show();
            }
        });
        mostrarDatos();
    }

    public void mostrarDatos() {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                users.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String sucess=jsonObject.getString("sucess");
                    JSONArray jsonArray=jsonObject.getJSONArray("datos");
                    if (sucess.equals("1")) {
                        for (int i=0;i<jsonArray.length();i++) {
                            JSONObject object= jsonArray.getJSONObject(i);
                            String id= object.getString("id");
                            String nombre = object.getString("nombre");
                            String email = object.getString("email");
                            String contacto = object.getString("contacto");
                            String direccion = object.getString("direccion");
                            usuarios = new Users(id, nombre, email, contacto, direccion);
                            users.add(usuarios);
                            adaptador.notifyDataSetChanged();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Home.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }


    public void btnAgg(View view) {
        Intent i = new Intent(this, Insertar.class);
        startActivity(i);
    }
}
