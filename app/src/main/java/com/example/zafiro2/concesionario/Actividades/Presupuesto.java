package com.example.zafiro2.concesionario.Actividades;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zafiro2.concesionario.Adaptadores.AdaptadorListaExtrasPresupuesto;
import com.example.zafiro2.concesionario.BaseDatos.DatabaseAccess;
import com.example.zafiro2.concesionario.Objetos.Coches;
import com.example.zafiro2.concesionario.Objetos.Extras;
import com.example.zafiro2.concesionario.Objetos.Presupuestos;
import com.example.zafiro2.concesionario.R;
import com.example.zafiro2.concesionario.ResumenPresupuesto;

import java.util.ArrayList;

public class Presupuesto extends AppCompatActivity {

    TextView txvMarcaPre;
    TextView txvModeloPre;
    TextView txvPrecioPre;
    ListView lvListaExtrasPre;
    FloatingActionButton fbaGenerarResumen;
    Coches coche = new Coches();
    ArrayList<Extras> arrayExtrasPre;
    ArrayList<Extras> arrayExtrasAux = new ArrayList<Extras>();
    AdaptadorListaExtrasPresupuesto adaptadorListaExtrasPresupuesto;
    Presupuestos presupuestos = new Presupuestos();
    int[] array = new int[]{};

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
        fbaGenerarResumen = findViewById(R.id.fbaGenerarResumen);
        fbaGenerarResumen.setOnClickListener(mCorkyListener);
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
                    if(arrayExtrasAux.size()==0){
                        arrayExtrasAux.add(extra);

                    }
                    else{
                        arrayExtrasAux.add(extra);
                    }
                }else{
                    extra.setSelecionado(false);
                    String precio = txvPrecioPre.getText().toString();
                    Float precioSuma =  Float.parseFloat(precio) - extra.getPrecio();
                    txvPrecioPre.setText(Float.toString(precioSuma));
                    view.setBackgroundColor(Color.alpha(0));
                    if(arrayExtrasAux.size()!=0) {
                        arrayExtrasAux.remove(extra);
                    }
                }
            }
        });


        
    }

    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == findViewById(R.id.fbaGenerarResumen).getId()) {//Si pulsamos en siguiente
               presupuestos.setId(coche.getId());
                presupuestos.setMarca(coche.getMarca());
                presupuestos.setModelo(coche.getModelo());
                presupuestos.setPrecio(Float.parseFloat(txvPrecioPre.getText().toString()));
                presupuestos.setTamaño(arrayExtrasAux.size());
                presupuestos.setDesc(coche.getDescripcion());

                Intent intent = new Intent(getApplicationContext(),ResumenPresupuesto.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("presupuesto",presupuestos);
                for(int i =0; i<arrayExtrasAux.size();i++){
                    bundle.putSerializable("elemento"+i+1,arrayExtrasAux.get(i));
                }
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    };
}
