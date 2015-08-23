package com.itesm.avril.cartelera;

/**
 * Created by Avril on 20/08/2015.
 */
public class Cartelera {

    private String cine, pelicula, duracion, genero;

    public Cartelera(String cine, String pelicula, String duracion, String genero) {
        super();
        this.cine = cine;
        this.pelicula = pelicula;
        this.duracion = duracion;
        this.genero = genero;
    }

    public String getCine() {
        return cine;
    }

    public void setCine(String cine) {
        this.cine = cine;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
