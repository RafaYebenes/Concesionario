package com.example.zafiro2.concesionario;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.zafiro2.concesionario.BaseDatos.DatabaseAccess;
import com.example.zafiro2.concesionario.Listas.ListaCoches;
import com.example.zafiro2.concesionario.Objetos.Coches;

import static android.view.View.VISIBLE;

public class DatosCoches extends AppCompatActivity {

    ImageView imvCoche;
    EditText edtDatosMarca;
    EditText edtDatosModelo;
    EditText edtDatosPrecio;
    EditText edtDatosDescripcion;
    FloatingActionButton fbaGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_coches);
        Intent intent = getIntent();
        int id = intent.getIntExtra("pos",0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar4);
        toolbar.setTitle("Concesionario");
        setSupportActionBar(toolbar);

        cargarObejos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_datos_coche_nuevo, menu);
        return true;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) { //Al hacer clic en coches nuevos empieza la activada y manda el entero 0 que recibira la clase ListaCoches, 0 siguinifa coches nuevos, 1 coches de ocasion
            case R.id.mModificar:
                    habilitarEdicionDatos(true);
                    fbaGuardar.setVisibility(VISIBLE);
                break;
            case R.id.mEliminar:

                break;
            case R.id.mGenerarPresupuesto:

                break;

        }//fin switch*/

        return super.onOptionsItemSelected(item);
    }

    public void cargarDatos(int pos){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstace(this);
        databaseAccess.open();
        Coches coche = databaseAccess.traerUnCoche(pos);
        edtDatosMarca.setText(coche.getMarca());
        edtDatosModelo.setText(coche.getModelo());
        edtDatosPrecio.setText(Float.toString(coche.getPrecio()));
        edtDatosDescripcion.setText(coche.getDescripcion());

    }
    public void cargarObejos(){
        imvCoche = findViewById(R.id.imvCoche);
        edtDatosMarca = findViewById(R.id.edtDatosMarca);
        edtDatosModelo = findViewById(R.id.edtDatosModelo);
        edtDatosPrecio = findViewById(R.id.edtDatosPrecio);
        fbaGuardar = findViewById(R.id.fbaGuardar);

        habilitarEdicionDatos(false);

    }

    public void habilitarEdicionDatos(boolean bol){
        if(bol==false){
            edtDatosMarca.setEnabled(false);
            edtDatosModelo.setEnabled(false);
            edtDatosPrecio.setEnabled(false);
            edtDatosDescripcion.setEnabled(false);
        }
        if(bol==true){

            edtDatosMarca.setEnabled(true);
            edtDatosModelo.setEnabled(true);
            edtDatosPrecio.setEnabled(true);
            edtDatosDescripcion.setEnabled(true);

        }
    }
}
