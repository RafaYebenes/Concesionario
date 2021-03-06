package com.example.zafiro2.concesionario.Objetos;

public class Coches {

    String marca, modelo, descripcion;
    private byte[] imagen;
    Float precio;
    Integer nuevo;
    int id;
    public Coches(int id, String marca, String modelo, String descripcion, byte[] imagen, Float precio, Integer nuevo) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precio = precio;
        this.nuevo = nuevo;
    }

    public Coches() {
    }


    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getNuevo() {
        return nuevo;
    }

    public void setNuevo(Integer nuevo) {
        this.nuevo = nuevo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
