package com.example.mplusuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;

public class MainActivity extends AppCompatActivity {

    EditText edtCodigo;
    TextView nombres, apellidos, genero, region, especialidad;
    Button btnBuscar;

    RequestQueue requestQueue; //estudiar realiza una cola de peticiones
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtCodigo = findViewById(R.id.camDoc);
        nombres = findViewById(R.id.txtNombre);
        apellidos = findViewById(R.id.txtApellido);
        genero = findViewById(R.id.txtGenero);
        region = findViewById(R.id.txtRegion);
        especialidad = findViewById(R.id.txtEspecialidad);
        btnBuscar = findViewById(R.id.btnConsutar);




    }
}