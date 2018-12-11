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
import com.example.zafiro2.concesionario.Actividades.NuevoCoche;
import com.example.zafiro2.concesionario.Actividades.DatosCoches;
import com.example.zafiro2.concesionario.Adaptadores.AdaptadorListaCoches;
import com.example.zafiro2.concesionario.Conocenos;
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
                Coches coche = arrayCoches.get(position);
                int[] posYBand = new int[]{coche.getId(),band};
                Intent intent = new Intent(getApplicationContext(), DatosCoches.class);
                intent.putExtra("pos",posYBand);
                startActivityForResult(intent,1);
            }
        });

    }

    public void CargarObjetos(){
        Toolbar toolbar3 = findViewById(R.id.toolbar3);

        lvCoches = findViewById(R.id.lvCoches);
         fba = findViewById(R.id.fbaCrear);
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
        if(arrayCoches!=null) { //Prueba de  entrega de datos desde la bbdd
            adaptadorListaCoches = new AdaptadorListaCoches(this, arrayCoches);
            lvCoches.setAdapter(adaptadorListaCoches);
        }
        else{
            Toast.makeText(this, "Fallo critico", Toast.LENGTH_SHORT).show();
        }
        setSupportActionBar(toolbar3);

        fba.setOnClickListener(mCorkyListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_estandar, menu);
        if(band==0) {
            menu.findItem(R.id.mCoches_Ocasion).setEnabled(true);
            menu.findItem(R.id.mCoches_Nuevos).setEnabled(false);
        }
        if(band==1){
            menu.findItem(R.id.mCoches_Nuevos).setEnabled(true);
            menu.findItem(R.id.mCoches_Ocasion).setEnabled(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) { //Al hacer clic en coches nuevos empieza la activada y manda el entero 0 que recibira la clase ListaCoches, 0 siguinifa coches nuevos, 1 coches de ocasion
            case R.id.mCoches_Nuevos:
                band=0;
                actualizarObjetos();

                break;
            case R.id.mCoches_Ocasion:
                band=1;
                actualizarObjetos();
                break;
            case  R.id.mExtras:
                Intent intent3 = new Intent(this,ListaExtras.class);
                startActivity(intent3);
                break;
            case R.id.mConocenos:
                Intent intent4 = new Intent(this, Conocenos.class);
                startActivity(intent4);
                break;
        }//fin switch*/

        return super.onOptionsItemSelected(item);
    }

    public void actualizarObjetos(){
        Toolbar toolbar3 =  findViewById(R.id.toolbar3);
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if ((requestCode == 1) && (resultCode == RESULT_OK)) {
            actualizarObjetos();
        }
    }

    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == findViewById(R.id.fbaCrear).getId()) {//Si pulsamos en siguiente
                Intent intent = new Intent(getApplicationContext(), NuevoCoche.class);
                intent.putExtra("nuevo",band);
                startActivityForResult(intent,1);
            }
        }
    };
}
