package com.example.zafiro2.concesionario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.zafiro2.concesionario.Listas.ListaCoches;
import com.example.zafiro2.concesionario.Listas.ListaExtras;
import com.example.zafiro2.concesionario.Objetos.Conocenos;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Concesionario");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_estandar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

       switch (id) { //Al hacer clic en coches nuevos empieza la activada y manda el entero 0 que recibira la clase ListaCoches, 0 siguinifa coches nuevos, 1 coches de ocasion
            case R.id.mCoches_Nuevos:
            Intent intent = new Intent(this,ListaCoches.class);
            intent.putExtra("band",0);
            startActivity(intent);
            break;

            case R.id.mCoches_Ocasion:
            Intent intent2 = new Intent(this,ListaCoches.class);
            intent2.putExtra("band",1);
            startActivity(intent2);
            break;

           case R.id.mExtras:
               Intent intent3 = new Intent(this,ListaExtras.class);
               startActivity(intent3);
               break;
           case R.id.mConocenos:
                Intent intent4 = new Intent(this,Conocenos.class);
                startActivity(intent4);
        }//fin switch*/

        return super.onOptionsItemSelected(item);
    }
}
