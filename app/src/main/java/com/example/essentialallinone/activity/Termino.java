package com.example.essentialallinone.activity;

public class Termino
{
    private String palabra;
    private String orden;
    private String libro;
    private String tipo;
    private String unidad;

    public Termino()
    {
    }

    public Termino(String[] db)
    {
        this.orden= db[0];
        this.palabra= db[1];
        this.unidad= db[2];
        this.libro=db[3];
        this.tipo= db[4];


    }
    public String getOrden() {
        return orden;
    }

    public String getPalabra() {
        return palabra;
    }

    public String getLibro() {
        return libro;
    }

    public String getUnidad() {
        return unidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    @Override
    public String toString() {
        String cadena="";
        cadena = getOrden()+","+getPalabra()+","+getLibro()+","+getTipo()+","+getUnidad();
        return cadena;
    }
}
