package com.example.zaira.reporteciudadano;

import java.util.List;

public class ResponseReportes {
    List<Reporte> reportes;

    public ResponseReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }

    public List<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }
}
