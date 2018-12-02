package com.example.zafiro2.concesionario.Listas;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zafiro2.concesionario.BaseDatos.DatabaseAccess;
import com.example.zafiro2.concesionario.Objetos.DatosCoches;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_estandar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) { //Al hacer clic en coches nuevos empieza la activada y manda el entero 0 que recibira la clase ListaCoches, 0 siguinifa coches nuevos, 1 coches de ocasion
            case R.id.mCoches_Nuevos:
                Intent intent = new Intent(this,ListaCoches.class);
                intent.putExtra("band",0);
                startActivity(intent);
                break;
            case R.id.mCoches_Ocasion:
                Intent intent2 = new Intent(this,ListaCoches.class);
                intent2.putExtra("band",1);
                startActivity(intent2);
                break;

        }//fin switch*/

        return super.onOptionsItemSelected(item);
    }
}
