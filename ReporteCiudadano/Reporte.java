package com.example.zaira.reporteciudadano;

import java.io.Serializable;

public class Reporte implements Serializable {
    int id_reporte;
    String hashtag;
    String comentario;
    double latitud;
    double longitud;
    double distancia;

    public Reporte() {
    }

    public Reporte(int id_reporte, String hashtag, String comentario, double latitud, double longitud, double distancia) {
        this.id_reporte = id_reporte;
        this.hashtag = hashtag;
        this.comentario = comentario;
        this.latitud = latitud;
        this.longitud = longitud;
        this.distancia = distancia;
    }

    public int getId_reporte() {
        return id_reporte;
    }

    public void setId_reporte(int id_reporte) {
        this.id_reporte = id_reporte;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
