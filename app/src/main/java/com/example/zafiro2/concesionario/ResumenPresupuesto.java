package com.example.zafiro2.concesionario;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zafiro2.concesionario.Adaptadores.AdaptadorListaExtrasPresupuesto;
import com.example.zafiro2.concesionario.BaseDatos.DatabaseAccess;
import com.example.zafiro2.concesionario.Objetos.Extras;
import com.example.zafiro2.concesionario.Objetos.Presupuestos;

import java.util.ArrayList;

public class ResumenPresupuesto extends AppCompatActivity {

    TextView txvMarcaResumen;
    TextView txvModeloResumen;
    TextView txvDescResumen;
    TextView txvPrecioResumen;
    ListView lvExtrasResumen;
    FloatingActionButton fbaEnviarCorreo;
    ArrayList<Extras> arrayListExtras = new ArrayList<Extras>();
    int[] array = new int[]{};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen_presupuesto);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Presupuestos presupuestos = new Presupuestos();
        presupuestos  = (Presupuestos) bundle.getSerializable("presupuesto");
        cargarDatos();
        txvMarcaResumen.setText(presupuestos.getMarca());
        txvModeloResumen.setText(presupuestos.getModelo());
        txvDescResumen.setText(presupuestos.getDesc());
        txvPrecioResumen.setText("Precio: "+Float.toString(presupuestos.getPrecio())+"€");
        CargarListView(presupuestos.getTamaño(),bundle);

    }

    public void cargarDatos(){
        txvMarcaResumen = findViewById(R.id.txvMarcaResumen);
        txvModeloResumen = findViewById(R.id.txvModeloResumen);
        txvDescResumen = findViewById(R.id.txvDescResumen);
        txvPrecioResumen = findViewById(R.id.txvPrecioResumen);
        fbaEnviarCorreo = findViewById(R.id.fbaEnviarCorreo);
        lvExtrasResumen = findViewById(R.id.lvExtrasResumen);
    }

    public void CargarListView(int size, Bundle bundle){

        DatabaseAccess databaseAccess = DatabaseAccess.getInstace(this);
        databaseAccess.open();
        for(int i = 0; i<size;i++){
            Extras extra = (Extras) bundle.getSerializable("elemento"+i+1);
            arrayListExtras.add(extra);
        }
        AdaptadorListaExtrasPresupuesto  adaptadorListaExtrasPresupuesto = new AdaptadorListaExtrasPresupuesto(this,arrayListExtras);
        lvExtrasResumen.setAdapter(adaptadorListaExtrasPresupuesto);
        lvExtrasResumen.setEnabled(false);
    }

    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == findViewById(R.id.fbaGuardar).getId()) {//Si pulsamos en siguiente
                
            }
        }
    };
}
