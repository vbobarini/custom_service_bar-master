package com.example.ultimo2023.model;

public class Producto {
    int id;
    String nombre;
    int precio;
    String image;
    String descripcion;

    public Producto(){

    }

    public Producto(String nombre, int precio, String image, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.image = image;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
