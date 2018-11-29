package com.example.zafiro2.concesionario.Listas;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zafiro2.concesionario.BaseDatos.DatabaseAccess;
import com.example.zafiro2.concesionario.Objetos.AdaptadorListaCoches;
import com.example.zafiro2.concesionario.Objetos.Coches;
import com.example.zafiro2.concesionario.R;

import java.util.ArrayList;

public class ListaCoches extends AppCompatActivity {

    ListView lvCoches;

    ArrayList<Coches> arrayCoches;
    FloatingActionButton fba;
    int band = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_coches);
        CargarObjetos();
    }

    public void CargarObjetos(){
        lvCoches = findViewById(R.id.lvCoches);
       // fba = findViewById(R.id.fbaNuevoCoche);
        Intent intent = getIntent();
        band= intent.getIntExtra("band",0);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstace(this);
        databaseAccess.open();

        //if(band==0){
            arrayCoches = databaseAccess.todosLosCochesNuevos();
        //}
        /*else{
            arrayCoches = databaseAccess.todosLosCochesOcasion();
        }*/
        if(arrayCoches!=null) { //Prueba de entrega de datos desde la bbdd
            AdaptadorListaCoches adaptadorListaCoches = new AdaptadorListaCoches(this, arrayCoches);
            lvCoches.setAdapter(adaptadorListaCoches);
        }
        else{
            Toast.makeText(this, "Fallo critico", Toast.LENGTH_SHORT).show();
        }
    }
}
