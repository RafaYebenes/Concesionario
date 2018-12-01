package com.example.zafiro2.concesionario.Listas;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zafiro2.concesionario.BaseDatos.DatabaseAccess;
import com.example.zafiro2.concesionario.DatosCoches;
import com.example.zafiro2.concesionario.Objetos.AdaptadorListaCoches;
import com.example.zafiro2.concesionario.Objetos.Coches;
import com.example.zafiro2.concesionario.R;

import java.util.ArrayList;

public class ListaCoches extends AppCompatActivity {

    ListView lvCoches;

    ArrayList<Coches> arrayCoches;
    FloatingActionButton fba;
    AdaptadorListaCoches adaptadorListaCoches;
    int band = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_coches);
        CargarObjetos();
        lvCoches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DatosCoches.class);
                intent.putExtra("pos",position);
                startActivityForResult(intent,1);
            }
        });

    }

    public void CargarObjetos(){
        Toolbar toolbar3 = (Toolbar) findViewById(R.id.toolbar3);

        lvCoches = findViewById(R.id.lvCoches);
       // fba = findViewById(R.id.fbaNuevoCoche);
        Intent intent = getIntent();
        band= intent.getIntExtra("band",0);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstace(this);
        databaseAccess.open();
        if(band==0){
            arrayCoches = databaseAccess.todosLosCochesNuevos();
            toolbar3.setTitle("Coches Nuevos");
        }
        else{
            arrayCoches = databaseAccess.todosLosCochesOcasion();
            toolbar3.setTitle("Coches de Ocasión");
        }
        if(arrayCoches!=null) { //Prueba de entrega de datos desde la bbdd
            adaptadorListaCoches = new AdaptadorListaCoches(this, arrayCoches);
            lvCoches.setAdapter(adaptadorListaCoches);
        }
        else{
            Toast.makeText(this, "Fallo critico", Toast.LENGTH_SHORT).show();
        }
        setSupportActionBar(toolbar3);
    }
}
