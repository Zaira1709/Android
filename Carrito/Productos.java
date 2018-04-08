package com.example.zaira.carritocompras;

import java.io.Serializable;

/**
 * Created by zaira on 03/04/18.
 */

public class Productos  implements Serializable {
    private String nombre;
    private String descripcion;
    private int imagen;
    private double precio;
    private  int imagen2;

    public Productos(String nombre, String descripcion, int imagen, double precio, int imagen2) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precio = precio;
        this.imagen2 = imagen2;
    }

    public int getImagen2() {
        return imagen2;
    }

    public void setImagen2(int imagen2) {
        this.imagen2 = imagen2;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImagen() {
        return imagen;
    }

    public double getPrecio() {
        return precio;
    }
}
