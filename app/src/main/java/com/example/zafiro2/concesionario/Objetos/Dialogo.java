package com.example.zafiro2.concesionario.Objetos;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zafiro2.concesionario.R;

public class Dialogo {

    EditText edtNombre;
    EditText edtApellidos;
    EditText edtFecha;
    EditText edtEmail;
    EditText edtTelefono;
    EditText edtPoblacion;
    EditText edtDireccion;

    Button btnEnviar;
    Button btnCancelar;

    public interface DialogoEmergente {
        void ResultadoDialogo(String nombre, String apellidos, String email, int telefono, String poblacion, String direccion);
    }

    private DialogoEmergente interfaz;

    public Dialogo(Context context, DialogoEmergente actividad) {

        interfaz = actividad;

        final Dialog dialogo = new Dialog(context);
        dialogo.getWindow();
        dialogo.setContentView(R.layout.layout_dialogo);

        edtNombre = dialogo.findViewById(R.id.edtNombre);
        edtApellidos = dialogo.findViewById(R.id.edtApellidos);
        edtFecha = dialogo.findViewById(R.id.edtFecha);
        edtEmail = dialogo.findViewById(R.id.edtEmail);
        edtTelefono = dialogo.findViewById(R.id.edtTelefono);
        edtPoblacion = dialogo.findViewById(R.id.edtPoblacion);
        edtDireccion = dialogo.findViewById(R.id.edtDireccion);
        btnEnviar = dialogo.findViewById(R.id.btnEnviar);
        btnCancelar = dialogo.findViewById(R.id.btnCancelar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             String nombre = edtNombre.getText().toString();
             String Apellidos = edtApellidos.getText().toString();
             String Direccion = edtDireccion.getText().toString();
             String Poblacion = edtPoblacion.getText().toString();
             String email = edtEmail.getText().toString();
             int telefono = Integer.parseInt(edtTelefono.getText().toString());

             if(nombre.length() != 0 && Apellidos.length() != 0 && email.length() != 0 && telefono != 0 && Poblacion.length() != 0 && Direccion.length() != 0) {
                    interfaz.ResultadoDialogo(nombre, Apellidos, email, telefono, Poblacion, Direccion);
                    dialogo.dismiss();
                } else {
                 Toast.makeText(dialogo.getContext(), "Rellena todos los campos por favor", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialogo.show();
    }


}
