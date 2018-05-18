
package com.pablo.pse5.batch;

public class SuscripcionesOfertaBatch {
    private String nombreEmpresa;
    private String nombreOferta;
    private String emailEmpresa;
    private long numSuscripciones;
    private int idOferta;

    public SuscripcionesOfertaBatch() {
    }

    public SuscripcionesOfertaBatch(String nombreEmpresa, String nombreOferta, String emailEmpresa, long numSuscripciones, int idOferta) {
        this.nombreEmpresa = nombreEmpresa;
        this.nombreOferta = nombreOferta;
        this.emailEmpresa = emailEmpresa;
        this.numSuscripciones = numSuscripciones;
        this.idOferta = idOferta;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNombreOferta() {
        return nombreOferta;
    }

    public void setNombreOferta(String nombreOferta) {
        this.nombreOferta = nombreOferta;
    }

    public String getEmailEmpresa() {
        return emailEmpresa;
    }

    public void setEmailEmpresa(String emailEmpresa) {
        this.emailEmpresa = emailEmpresa;
    }

    public long getNumSuscripciones() {
        return numSuscripciones;
    }

    public void setNumSuscripciones(long numSuscripciones) {
        this.numSuscripciones = numSuscripciones;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }
    
}
