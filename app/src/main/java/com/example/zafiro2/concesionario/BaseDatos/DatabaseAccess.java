package com.example.zafiro2.concesionario.BaseDatos;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.zafiro2.concesionario.Objetos.Coches;
import com.example.zafiro2.concesionario.Objetos.Extras;

import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private  static DatabaseAccess instace;

    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstace(Context context){
        if(instace ==null){
            instace = new DatabaseAccess(context);
        }
        return instace;
    }
    public void open(){
        this.database = openHelper.getWritableDatabase();
    }

    public void close(){
        if(database!=null){
            this.database.close();
        }
    }

    public ArrayList<Coches> todosLosCochesNuevos(){
        Cursor c;
        ArrayList<Coches> arrayCoches = new ArrayList<Coches>();

        c = database.rawQuery("select * from coches where nuevo=0",null);

        if(c.moveToFirst()){
            do{
               arrayCoches.add(new Coches(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getBlob(4),c.getFloat(5),c.getInt(6)));

            }while (c.moveToNext());
        }
        c.close();
        return arrayCoches;
    }
    public ArrayList<Coches> todosLosCochesOcasion(){
        Cursor c;
        ArrayList<Coches> arrayCoches = new ArrayList<Coches>();

        c = database.rawQuery("select * from coches where nuevo=1",null);

        if(c.moveToFirst()){
            do{
                arrayCoches.add(new Coches(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getBlob(4),c.getFloat(5),c.getInt(6)));

            }while (c.moveToNext());
        }
        c.close();
        return arrayCoches;
    }

    public ArrayList<Extras> todosLosExtras(){
        Cursor c;
        ArrayList<Extras> arrayExtras = new ArrayList<Extras>();

        c = database.rawQuery("select * from extras",null);

        if(c.moveToFirst()){
            do{
                arrayExtras.add(new Extras(c.getInt(0),c.getString(1),c.getString(2),c.getFloat(3)));
            }while (c.moveToNext());
        }
        c.close();
        return arrayExtras;
    }
    public Coches traerUnCoche(int pos, int nuevo){

        Cursor c = null;
        Coches coche = new Coches();
        String posicion = Integer.toString(pos);
        c = database.rawQuery("select * from coches where id ="+pos+" and nuevo ="+nuevo, null);

        if(c.moveToFirst()){
            do{
                coche = new Coches(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getBlob(4),c.getFloat(5),c.getInt(6));
            }while (c.moveToNext());
        }

        c.close();
        return coche;

    }
    public void updateCliente(Coches coche, int posi){

        int pos = posi;

        ContentValues valores = new ContentValues();

        valores.put("marca", coche.getMarca());
        valores.put("modelo", coche.getModelo());
        valores.put("descripcion", coche.getDescripcion());
        valores.put("imagen",coche.getImagen());
        valores.put("precio", coche.getPrecio());


        database.update("coches",valores,"id="+pos,null);
        database.close();
    }

    public void eliminarCoche(int pos){

        database.delete("coches","id="+pos,null);
        database.close();
    }

    public int contarCoches(){
        int cont = 0;
        Cursor c;
        c = database.rawQuery("select * from coches", null);

        if(c!=null){
            cont = c.getCount();
        }

        return cont;
    }

    public void insertarCoche(Coches coche){
        ContentValues valores = new ContentValues();

        valores.put("marca",  coche.getMarca());
        valores.put("modelo", coche.getModelo());
        valores.put("descripcion", coche.getDescripcion());
        valores.put("imagen",coche.getImagen());
        valores.put("precio", coche.getPrecio());
        valores.put("nuevo",  coche.getNuevo());

        database.insert("coches",null,valores);
        database.close();
    }

    public void eliminarExtra(int id){
        database.delete("extras","id="+id,null);
        database.close();
    }

    public void insertarExtra(Extras extra){
        ContentValues valores = new ContentValues();

        valores.put("nombre",extra.getNombre());
        valores.put("descripcion",extra.getDescripcion());
        valores.put("precio",extra.getPrecio());

        database.insert("extras",null,valores);
        database.close();
    }
}
