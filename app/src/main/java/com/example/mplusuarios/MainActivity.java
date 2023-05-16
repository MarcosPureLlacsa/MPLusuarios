package com.example.mplusuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    EditText edtCodigo;
    TextView nombres, apellidos, genero, region, especialidad;
    Button btnBuscar;

    RequestQueue requestQueue; //estudiar realiza una cola de peticiones
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);
        edtCodigo = findViewById(R.id.camDoc);
        nombres = findViewById(R.id.txtNombre);
        apellidos = findViewById(R.id.txtApellido);
        genero = findViewById(R.id.txtGenero);
        region = findViewById(R.id.txtRegion);
        especialidad = findViewById(R.id.txtEspecialidad);
        btnBuscar = findViewById(R.id.btnConsutar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarServicio();
            }
        });
    }
    private void cargarServicio() {
        String documento = edtCodigo.getText().toString();
        String url = "http://192.168.0.42/api/consultarusuario.php?id="+documento;
        url = url.replace(" ", "%20");
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonObjectRequest);

    }

    @Override
    public void onResponse(JSONObject response) {
        //respuesta exitosa
        JSONArray jsonArray = response.optJSONArray("usuario");
        JSONObject jsonObject = null;
        try {
            jsonObject = jsonArray.getJSONObject(0);
            nombres.setText(jsonObject.optString("nombres"));
            apellidos.setText(jsonObject.optString("apellidos"));
            genero.setText(jsonObject.optString("genero"));
            region.setText(jsonObject.optString("region"));
            especialidad.setText(jsonObject.optString("especialidad"));

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        //error en la respuesta
        Log.i("Error", error.toString());
    }
}