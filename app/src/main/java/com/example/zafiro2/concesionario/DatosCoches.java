package com.example.zafiro2.concesionario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DatosCoches extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_coches);
        Intent intent = getIntent();
        int id = intent.getIntExtra("pos",0);

    }
}
