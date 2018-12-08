package com.example.zafiro2.concesionario.Actividades;

import android.content.Intent;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.zafiro2.concesionario.BaseDatos.DatabaseAccess;
import com.example.zafiro2.concesionario.Objetos.Extras;
import com.example.zafiro2.concesionario.R;

public class NuevoExtra extends AppCompatActivity {

    EditText edtNombreNuevoExtra;
    EditText edtDescNuevoExtra;
    EditText edtPrecioNuevoExtra;
    FloatingActionButton fbaGuardarNuevoExtra;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_extra);
        cargarDatos();


    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void cargarDatos(){
        edtDescNuevoExtra = findViewById(R.id.edtDesNuevoExtra);
        edtNombreNuevoExtra = findViewById(R.id.edtNombreNuevoExtra);
        edtPrecioNuevoExtra = findViewById(R.id.edtPrecioNuevoExtra);
        fbaGuardarNuevoExtra = findViewById(R.id.fbaGuardarNuevoExtra);
        fbaGuardarNuevoExtra.setOnClickListener(mCorkyListener);
        Toolbar toolbar5 = findViewById(R.id.toolbar5);
        toolbar5.setTitle("Nuevo Extra");

    }

    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onClick(View v) {
            if (v.getId() == findViewById(R.id.fbaGuardarNuevoExtra).getId()) {//Si pulsamos en siguiente
                if(GuardarExtra()) {
                    setResult(RESULT_OK);
                    finish();
                }

            }


        }
    };

    public boolean GuardarExtra(){
        if(edtPrecioNuevoExtra.getText().length()>0&&edtNombreNuevoExtra.getText().length()>0&&edtDescNuevoExtra.getText().length()>0) {
            Extras extras = new Extras();
            extras.setNombre(edtNombreNuevoExtra.getText().toString());
            extras.setDescripcion(edtDescNuevoExtra.getText().toString());
            extras.setPrecio(Float.parseFloat(edtPrecioNuevoExtra.getText().toString()));

            DatabaseAccess databaseAccess = DatabaseAccess.getInstace(this);
            databaseAccess.open();
            databaseAccess.insertarExtra(extras);
            databaseAccess.close();
            return true;
        }
        else{
            Toast.makeText(this, "Debes rellenar los campos antes de guardar", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


}
