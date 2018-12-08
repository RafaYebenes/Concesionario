package com.example.zafiro2.concesionario.Listas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.zafiro2.concesionario.BaseDatos.DatabaseAccess;
import com.example.zafiro2.concesionario.Adaptadores.AdaptadorListaExtras;
import com.example.zafiro2.concesionario.Objetos.Extras;
import com.example.zafiro2.concesionario.Actividades.NuevoExtra;
import com.example.zafiro2.concesionario.R;

import java.util.ArrayList;

public class ListaExtras extends AppCompatActivity {

    ListView lvExtras;
    ArrayList<Extras> arrayExtras;
    Menu newMenu;
    int pos;
    FloatingActionButton fbaCrearExtra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_extras);
        cargarDatos();
        lvExtras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                newMenu.findItem(R.id.mEliminarExtra).setVisible(true);
                Extras extra = arrayExtras.get(position);
                pos = extra.getId();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        newMenu = menu;
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_lista_extras, menu);
        menu.findItem(R.id.mExtras_Extras).setEnabled(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) { //Al hacer clic en coches nuevos empieza la activada y manda el entero 0 que recibira la clase ListaCoches, 0 siguinifa coches nuevos, 1 coches de ocasion
            case R.id.mCoches_Nuevos_Extras:
                Intent intent = new Intent(this,ListaCoches.class);
                intent.putExtra("band",0);
                startActivity(intent);
                break;

            case R.id.mCoches_Ocasion_Extras:
                Intent intent2 = new Intent(this,ListaCoches.class);
                intent2.putExtra("band",1);
                startActivity(intent2);
                break;
            case R.id.mEliminarExtra:
                final DatabaseAccess databaseAccess = DatabaseAccess.getInstace(this);
                databaseAccess.open();


                AlertDialog.Builder builder = new AlertDialog.Builder(ListaExtras.this);
                builder.setTitle("Eliminar un Extra");
                builder.setMessage("Desea realmente eliminar el Extra?");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Hacer cosas aqui al hacer clic en el boton NO
                    }
                });
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseAccess.eliminarExtra(pos);
                        actualiarDatos();
                    }
                });
                builder.show();

                break;
        }//fin switch*/

        return super.onOptionsItemSelected(item);
    }


    public void cargarDatos(){

        Toolbar toolbar4 = findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar4);
        toolbar4.setTitle("Extras");
        lvExtras = findViewById(R.id.lvExtras);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstace(this);
        databaseAccess.open();
        arrayExtras = databaseAccess.todosLosExtras();
        AdaptadorListaExtras adaptadorListaExtras = new AdaptadorListaExtras(this,arrayExtras);
        lvExtras.setAdapter(adaptadorListaExtras);
        fbaCrearExtra = findViewById(R.id.fbaCrearExtra);
        fbaCrearExtra.setOnClickListener(mCorkyListener);
    }

    public void actualiarDatos(){
        DatabaseAccess databaseAccess = DatabaseAccess.getInstace(this);
        databaseAccess.open();
        arrayExtras = databaseAccess.todosLosExtras();
        AdaptadorListaExtras adaptadorListaExtras = new AdaptadorListaExtras(this,arrayExtras);
        lvExtras.setAdapter(adaptadorListaExtras);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if ((requestCode == 1) && (resultCode == RESULT_OK)) {
            actualiarDatos();
        }
    }

    private View.OnClickListener mCorkyListener = new View.OnClickListener() {

        public void onClick(View v) {
            if (v.getId() == findViewById(R.id.fbaCrearExtra).getId()) {//Si pulsamos en siguiente

              Intent intent = new Intent(getApplicationContext(),NuevoExtra.class);
              startActivityForResult(intent,1);

            }


        }
    };
}
