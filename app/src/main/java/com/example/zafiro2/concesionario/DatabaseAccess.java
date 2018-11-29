package com.example.zafiro2.concesionario;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
               arrayCoches.add(new Coches(c.getString(1),c.getString(1),c.getString(1),c.getString(1),c.getFloat(4),c.getInt(5)));

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
                arrayCoches.add(new Coches(c.getString(1),c.getString(1),c.getString(1),c.getString(1),c.getFloat(4),c.getInt(5)));

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
                arrayExtras.add(new Extras(c.getString(1),c.getString(2),c.getFloat(3)));
            }while (c.moveToNext());
        }
        c.close();
        return arrayExtras;
    }

}
