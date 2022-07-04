package com.example.essentialallinone.activity;

public class PalabraConjugada
{
    private String libro="";
    private String unidad="";
    private String termino="";

    public PalabraConjugada()
    {
    }
    public PalabraConjugada(String[] lista)
    {
        libro =  lista[0];
        unidad = lista[1];
        termino = lista[2];

    }

    public String getLibro() {
        return libro;
    }

    public String getUnidad() {
        return unidad;
    }

    public String getTermino() {
        return termino;
    }
}
