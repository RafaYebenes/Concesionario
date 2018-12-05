package com.example.zafiro2.concesionario.Objetos;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zafiro2.concesionario.BaseDatos.DatabaseAccess;
import com.example.zafiro2.concesionario.Objetos.Coches;
import com.example.zafiro2.concesionario.R;

import java.io.ByteArrayOutputStream;

public class NuevoCoche extends AppCompatActivity {

    EditText edtNuevoMarca;
    EditText edtNuevoModelo;
    EditText edtNuevoDescripcion;
    EditText edtNuevoPrecio;
    ImageView imbImagenNuevo;
    FloatingActionButton fbaNuevo;
    Coches coche;
    private byte[] foto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_coche);

        cargarDatos();
    }

    public void cargarDatos(){
        edtNuevoMarca = findViewById(R.id.edtNuevoMarca);
        edtNuevoModelo = findViewById(R.id.edtNuevoModelo);
        edtNuevoDescripcion = findViewById(R.id.edtNuevoDescripcion);
        edtNuevoPrecio = findViewById(R.id.edtNuevoPrecio);
        imbImagenNuevo = findViewById(R.id.imbImagenNuevo);

        fbaNuevo = findViewById(R.id.fbaNuevo);
        fbaNuevo.setOnClickListener(mCorkyListener);
        imbImagenNuevo.setOnClickListener(mCorkyListener);
    }
    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onClick(View v) {
            if (v.getId() == findViewById(R.id.fbaNuevo).getId()) {//Si pulsamos en siguiente
                Intent intent = getIntent();
                int nuevo = intent.getIntExtra("nuevo",0);
                guardarDatos(nuevo);
            }
            if(v.getId()==findViewById(R.id.imbImagenNuevo).getId()){
                if(checkCameraPermission()) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 0);
                }
            }

        }
    };


    public void guardarDatos(int nuevo){
        Coches coche = new Coches(/*c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getBlob(4), c.getFloat(5), c.getInt(6)*/);
        DatabaseAccess  databaseAccess = DatabaseAccess.getInstace(this);
        databaseAccess.open();
        int id = databaseAccess.contarCoches();

        Toast.makeText(this, Integer.toString(id), Toast.LENGTH_SHORT).show();


        coche.setMarca(edtNuevoMarca.getText().toString());
        coche.setModelo(edtNuevoModelo.getText().toString());
        coche.setDescripcion(edtNuevoDescripcion.getText().toString());
        coche.setPrecio(Float.parseFloat(edtNuevoPrecio.getText().toString()));
        coche.setNuevo(nuevo);
        coche.setImagen(foto);

        databaseAccess.insertarCoche(coche);
        setResult(RESULT_OK, null);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // SI EL CÓDIGO RE PETICIÓN ES 0, LA IMAGEN VIENE DE LA CÁMARA DE FOTOS.
        if (requestCode == 0) {
            // CREAMOS UN MAPA DE BITS CON LOS DATOS QUE HEMOS RECOGIDO DE LA CÁMARA DE FOTOS.
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            // PONEMOS EL MAPA DE BITS EN EL IMAGEVIEW.
            imbImagenNuevo.setImageBitmap(bitmap);
            // CREAMOS UN ARRAY DE BYTES DE SALIDA.
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            // COMPRIMIMOS EL MAPA DE BITS EN PNG Y LA VARIABLE FOTO_COCHE TOMA EL VALOR DEL FLUJO DE SALIDA DE ARRAY DE BYTES.
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

            foto = stream.toByteArray();
        }
    }

    // MÉTODO PARA COMPROBAR LOS PERMISOS DE LA CÁMARA.
    private boolean checkCameraPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso para la camara!.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 225);
            return false;
        } else {
            Log.i("Mensaje", "Tienes permiso para usar la camara.");
            return true;
        }
    }
}
