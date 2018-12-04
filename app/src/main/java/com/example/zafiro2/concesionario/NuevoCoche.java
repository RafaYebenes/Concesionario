package com.example.zafiro2.concesionario;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zafiro2.concesionario.BaseDatos.DatabaseAccess;
import com.example.zafiro2.concesionario.Objetos.Coches;

public class NuevoCoche extends AppCompatActivity {

    EditText edtNuevoMarca;
    EditText edtNuevoModelo;
    EditText edtNuevoDescripcion;
    EditText edtNuevoPrecio;
    FloatingActionButton fbaNuevo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_coche);

        cargarDatos();
    }

    public void cargarDatos(){
        edtNuevoMarca = findViewById(R.id.edtNuevoMarca);
        edtNuevoModelo = findViewById(R.id.edtNuevoModelo);
        edtNuevoDescripcion = findViewById(R.id.edtDatosDescripcion);
        edtNuevoPrecio = findViewById(R.id.edtNuevoPrecio);

        fbaNuevo = findViewById(R.id.fbaNuevo);
        fbaNuevo.setOnClickListener(mCorkyListener);
    }
    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == findViewById(R.id.fbaNuevo).getId()) {//Si pulsamos en siguiente
                guardarDatos();
            }
        }
    };

    public void guardarDatos(){
        Coches coche = new Coches();
        DatabaseAccess  databaseAccess = DatabaseAccess.getInstace(this);
        databaseAccess.open();
        int id = databaseAccess.contarCoches();

        Toast.makeText(this, Integer.toString(id), Toast.LENGTH_SHORT).show();

        coche.setId(id+1);
        coche.setMarca(edtNuevoMarca.getText().toString());
        coche.setModelo(edtNuevoModelo.getText().toString());
        coche.setDescripcion(edtNuevoDescripcion.getText().toString());
        coche.setPrecio(Float.parseFloat(edtNuevoPrecio.getText().toString()));

        finish();
        //falta la imagen
    }
}
