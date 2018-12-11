package com.example.zafiro2.concesionario.Objetos;

import java.io.Serializable;
import java.util.ArrayList;

public class Presupuestos  implements Serializable {

    int id, tamaño;
    ArrayList arrayExtras;
    String Marca;
    String Modelo;
    String Desc;
    Float precio;


    public Presupuestos(int id, ArrayList<Extras> arrayExtras, String marca, String modelo, Float precio) {
        this.id = id;
        this.arrayExtras = arrayExtras;
        Marca = marca;
        Modelo = modelo;
        this.precio = precio;
    }

    public Presupuestos() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList getArrayExtras() {
        return arrayExtras;
    }

    public void setArrayExtras(ArrayList arrayExtras) {
        this.arrayExtras = arrayExtras;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
}
