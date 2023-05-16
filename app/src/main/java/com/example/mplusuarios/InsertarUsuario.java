package com.example.mplusuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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

public class InsertarUsuario extends AppCompatActivity {

    EditText id, mombres, apellidos;
    Spinner generosp, regionsp, especialidadsp;
    Button btnRegistrar;

    ArrayList especialidadArrayString;

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

        fillSpinner();
    }

    private void fillSpinner() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://localhost/api/ConsultarTabla.php?tabla=especialidad";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("especialidad");
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String especialidad = jsonObject1.getString("especialidad");
                        especialidadArrayString.add(especialidad);
                    }
                    especialidadsp.setAdapter(new ArrayAdapter<String> (getApplication(), android.R.layout.simple_spinner_dropdown_item, especialidadArrayString));
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

    }
}