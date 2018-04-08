package com.example.zaira.futbol;

import java.io.Serializable;

public class ListaPartidos implements Serializable {
    private String equipo1;
    private int img1;
    private String equipo2;
    private int img2;

    public ListaPartidos(String equipo1, int img1, String equipo2, int img2) {
        this.equipo1 = equipo1;
        this.img1 = img1;
        this.equipo2 = equipo2;
        this.img2 = img2;
    }

    public String getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    public int getImg1() {
        return img1;
    }

    public void setImg1(int img1) {
        this.img1 = img1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    public int getImg2() {
        return img2;
    }

    public void setImg2(int img2) {
        this.img2 = img2;
    }
}
