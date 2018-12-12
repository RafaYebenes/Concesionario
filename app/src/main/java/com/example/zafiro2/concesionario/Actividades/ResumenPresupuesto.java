package com.example.zafiro2.concesionario.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zafiro2.concesionario.Adaptadores.AdaptadorListaExtrasPresupuesto;
import com.example.zafiro2.concesionario.BaseDatos.DatabaseAccess;
import com.example.zafiro2.concesionario.Objetos.Dialogo;
import com.example.zafiro2.concesionario.Objetos.Email;
import com.example.zafiro2.concesionario.Objetos.Extras;
import com.example.zafiro2.concesionario.Objetos.Presupuestos;
import com.example.zafiro2.concesionario.R;

import java.util.ArrayList;

public class ResumenPresupuesto extends AppCompatActivity {

    TextView txvMarcaResumen;
    TextView txvModeloResumen;
    TextView txvDescResumen;
    TextView txvPrecioResumen;
    ListView lvExtrasResumen;
    FloatingActionButton fbaEnviarCorreo;
    ArrayList<Extras> arrayListExtras = new ArrayList<Extras>();
    Presupuestos presupuestos = new Presupuestos();



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
                new Dialogo(getApplicationContext(),this);
            }
        }
    };

    //@Override
    public void ResultadoDialogo(String nombre, String apellidos, String email, int telefono, String poblacion, String direccion) {

        String contenido = "Estimado "+nombre+" "+apellidos+"\nAdjuntamos el presupuesto que usted solicito:\n\t"+presupuestos.getMarca()+" "+presupuestos.getModelo()+"\n\t Lista de extras: \n\t";

        for(int i =0;i<arrayListExtras.size();i++){
            contenido = contenido + arrayListExtras.get(i).getNombre()+"\n";
        }
        contenido = contenido+"\nEl precio total de su factura asciende a "+presupuestos.getPrecio()+"€";

       new Email("appconcesionario@gmail.com","concesionario123").execute(new Email.Mail("appconcesionario@gmail.com",email,"Presupuesto",contenido));
    }
}
