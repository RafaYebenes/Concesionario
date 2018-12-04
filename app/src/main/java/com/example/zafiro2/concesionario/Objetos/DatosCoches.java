package com.example.zafiro2.concesionario.Objetos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zafiro2.concesionario.BaseDatos.DatabaseAccess;
import com.example.zafiro2.concesionario.R;

import static android.view.View.VISIBLE;

public class DatosCoches extends AppCompatActivity {

    ImageView imvCoche;
    EditText edtDatosMarca;
    EditText edtDatosModelo;
    EditText edtDatosPrecio;
    EditText edtDatosDescripcion;
    FloatingActionButton fbaGuardar;
    Coches coche = new Coches();
    int[] id = new int[]{};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_coches);
        Intent intent = getIntent();
        id = intent.getIntArrayExtra("pos");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar4);
        toolbar.setTitle("Concesionario");
        setSupportActionBar(toolbar);

        cargarObejos();
        cargarDatos(id[0],id[1]);


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
                    eliminarCoche();
                    setResult(RESULT_OK, null);
                    finish();
                break;
            case R.id.mGenerarPresupuesto:

                break;

        }//fin switch*/

        return super.onOptionsItemSelected(item);
    }

    public void cargarDatos(int pos, int nuevo){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstace(this);
        databaseAccess.open();
        coche = databaseAccess.traerUnCoche(pos, nuevo);

        edtDatosMarca.setText(coche.getMarca());
        edtDatosModelo.setText(coche.getModelo());
        edtDatosPrecio.setText(Float.toString(coche.getPrecio()));
        edtDatosDescripcion.setText(coche.getDescripcion());
        int id = this.getResources().getIdentifier(coche.getImagen(), "drawable", this.getApplicationContext().getPackageName());
        Drawable drawable = getResources().getDrawable(id);
        imvCoche.setImageDrawable(drawable);

    }
    public void cargarObejos(){
        imvCoche = findViewById(R.id.imvCocheDatos);
        edtDatosMarca = findViewById(R.id.edtDatosMarca);
        edtDatosModelo = findViewById(R.id.edtDatosModelo);
        edtDatosPrecio = findViewById(R.id.edtDatosPrecio);
        edtDatosDescripcion = findViewById(R.id.edtDatosDescripcion);
        fbaGuardar = findViewById(R.id.fbaGuardar);
        fbaGuardar.setOnClickListener(mCorkyListener);
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

    public void guardarDatos(){
        String precio = edtDatosPrecio.getText().toString();

        Coches cocheActualizado = new Coches(coche.getId(),edtDatosMarca.getText().toString(), edtDatosModelo.getText().toString(), edtDatosDescripcion.getText().toString(),coche.getImagen(),Float.parseFloat(precio),coche.getNuevo());
        DatabaseAccess databaseAccess = DatabaseAccess.getInstace(this);
        databaseAccess.open();
        databaseAccess.updateCliente(cocheActualizado,id[0]);
        databaseAccess.close();
    }

    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == findViewById(R.id.fbaGuardar).getId()) {//Si pulsamos en siguiente
                guardarDatos();
                setResult(RESULT_OK, null);
                finish();

            }
        }
    };

    public void eliminarCoche(){
        DatabaseAccess databaseAccess =  DatabaseAccess.getInstace(this);
        databaseAccess.open();
        databaseAccess.eliminarCoche(coche.getId());
        databaseAccess.close();
    }
}
