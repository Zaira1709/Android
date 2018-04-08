package com.example.zaira.pendientes;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zaira on 05/04/18.
 */

public class Pendientes implements Serializable {
    String tarea;
    int tipo;
    String date;

    public Pendientes() {

    }
    public Pendientes(String tarea, int tipo, String date) {
        this.tarea = tarea;
        this.tipo = tipo;
        this.date = date;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
