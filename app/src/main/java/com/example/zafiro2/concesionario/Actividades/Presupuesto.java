package com.example.zafiro2.concesionario.Actividades;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zafiro2.concesionario.Adaptadores.AdaptadorListaExtrasPresupuesto;
import com.example.zafiro2.concesionario.BaseDatos.DatabaseAccess;
import com.example.zafiro2.concesionario.Objetos.Coches;
import com.example.zafiro2.concesionario.Objetos.Extras;
import com.example.zafiro2.concesionario.R;

import java.util.ArrayList;

public class Presupuesto extends AppCompatActivity {

    TextView txvMarcaPre;
    TextView txvModeloPre;
    TextView txvPrecioPre;
    ListView lvListaExtrasPre;
    FloatingActionButton fbaGenerarResumen;
    Coches coche = new Coches();
    ArrayList<Extras> arrayExtrasPre;
    AdaptadorListaExtrasPresupuesto adaptadorListaExtrasPresupuesto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presupuesto);
        Intent intent = getIntent();
        int cocheRecibido = intent.getIntExtra("coche",0);
        CargarObjetos();
        if(cocheRecibido!=0){
            CargarDatos(cocheRecibido);
        }
        else{
            Toast.makeText(this, "Fallo al cargar el vehiculo", Toast.LENGTH_SHORT).show();
        }
    }

    public void CargarObjetos(){

        txvMarcaPre = findViewById(R.id.txvMarcaPre);
        txvModeloPre = findViewById(R.id.txvModeloPre);
        txvPrecioPre = findViewById(R.id.txvPrecioPre);
        lvListaExtrasPre = findViewById(R.id.lvListaExtrasPre);
    }
    public void CargarDatos(int id){
        DatabaseAccess  databaseAccess = DatabaseAccess.getInstace(this);
        databaseAccess.open();
        coche = databaseAccess.traerUnCoche(id,0);

        txvMarcaPre.setText(coche.getMarca());
        txvModeloPre.setText(coche.getModelo());
        txvPrecioPre.setText(Float.toString(coche.getPrecio()));

        arrayExtrasPre = databaseAccess.todosLosExtras();
        adaptadorListaExtrasPresupuesto = new AdaptadorListaExtrasPresupuesto(this,arrayExtrasPre);
        lvListaExtrasPre.setAdapter(adaptadorListaExtrasPresupuesto);

        lvListaExtrasPre.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Extras extra = arrayExtrasPre.get(i);
                if(extra.isSelecionado()==false){
                    extra.setSelecionado(true);
                    String precio = txvPrecioPre.getText().toString();
                    Float precioSuma = extra.getPrecio() + Float.parseFloat(precio);
                    txvPrecioPre.setText(Float.toString(precioSuma));
                    view.setBackgroundColor(Color.GRAY);
                }else{
                    extra.setSelecionado(false);
                    String precio = txvPrecioPre.getText().toString();
                    Float precioSuma =  Float.parseFloat(precio) - extra.getPrecio();
                    txvPrecioPre.setText(Float.toString(precioSuma));
                    view.setBackgroundColor(Color.alpha(0));
                }
            }
        });


        
    }
}
