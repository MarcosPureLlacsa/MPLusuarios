package com.example.mplusuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import util.Util;

public class InsertarUsuario extends AppCompatActivity {

    EditText id, mombres, apellidos;
    Spinner generosp, regionsp, especialidadsp;
    Button btnRegistrar;
    ArrayList especialidadArrayString,regionArrayString,generoArrayString;

    Util u;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_usuario);

        id = findViewById(R.id.IUId);
        mombres = findViewById(R.id.IUNombres);
        apellidos = findViewById(R.id.IUApellidos);
        generosp = findViewById(R.id.spnIUGenero);
        regionsp = findViewById(R.id.spnIURegion);
        especialidadsp = findViewById(R.id.spnIUEspecialidad);
        btnRegistrar = findViewById(R.id.btnIUGuardar);
        especialidadArrayString = new ArrayList();
        regionArrayString = new ArrayList();
        generoArrayString = new ArrayList();

        generoSpinner("ConsultarTabla.php?tabla=","genero",generoArrayString ,generosp);
        regionSpinner("ConsultarTabla.php?tabla=","region",regionArrayString ,regionsp);
        fillSpinner("ConsultarTabla.php?tabla=","especialidad", especialidadArrayString,especialidadsp);



        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String i = id.getText().toString();
                String nombre = mombres.getText().toString();
                String ape = apellidos.getText().toString();

                registrar_Usuario( "InsertarUsuario2.php?id="+i+"&nombres="+nombre+"&apellidos="+ape+
                        "&idgenero="+generosp.getSelectedItemId()+
                        "&idregion="+regionsp.getSelectedItemId()+
                        "&idespecialidad="+especialidadsp.getSelectedItemId());
            }
        });
    }

    private void registrar_Usuario(String ws) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = u.RUTA + ws ;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(InsertarUsuario.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(InsertarUsuario.this, "No registra", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
        
    }

    private void generoSpinner(String s, String genero, ArrayList generoArrayString, Spinner generosp) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = u.RUTA + s + genero;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray(genero);

                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        generoArrayString.add(jsonObject1.getString(genero));
                    }
                    generosp.setAdapter(new ArrayAdapter<String>(getApplication(),
                            android.R.layout.simple_spinner_dropdown_item,generoArrayString));

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(stringRequest);
    }

    private void regionSpinner(String s, String region, ArrayList regionArrayString, Spinner regionsp) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = u.RUTA + s + region;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray(region);

                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        regionArrayString.add(jsonObject1.getString(region));
                    }
                    regionsp.setAdapter(new ArrayAdapter<String>(getApplication(),
                            android.R.layout.simple_spinner_dropdown_item,regionArrayString));

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(stringRequest);
    }

    private void fillSpinner( String ws, String tabla, ArrayList arrayStringDatosMostrar, Spinner spinner) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = u.RUTA + ws + tabla;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray(tabla);

                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        arrayStringDatosMostrar.add(jsonObject1.getString(tabla));
                    }
                    spinner.setAdapter(new ArrayAdapter<String>(getApplication(),
                            android.R.layout.simple_spinner_dropdown_item,arrayStringDatosMostrar));

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
    });
        requestQueue.add(stringRequest);
    }
}