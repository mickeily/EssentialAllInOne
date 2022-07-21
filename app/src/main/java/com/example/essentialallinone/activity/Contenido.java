package com.example.essentialallinone.activity;

import java.util.List;

public class Contenido
{
    private  int order;
    private int status;
    private int pointPrincipal;
    private String date;
    private String example;
    private String meaning;
    private String word;
    private String type;
    private String book;
    private String unit;
    private String complexity;
    private String wordTranslation;
    private String defTranslation;


    public Contenido() {}
    public Contenido(String[] dbStatic, String[] dbNoStatic)
    {
        this.order = Integer.parseInt(dbStatic[0]);
        this.example= dbStatic[1];
        this.meaning= dbStatic[2];
        this.book = dbStatic[3];
        this.unit = dbStatic[4];
        this.word = dbStatic[5];
        this.type = dbStatic[6];
        this.word = dbStatic[5];
        this.type = dbStatic[6];
        this.word = dbStatic[5];
        this.type = dbStatic[6];

        this.status = Integer.parseInt(dbNoStatic[1]);
        this.pointPrincipal= Integer.parseInt(dbNoStatic[2]);
        this.date = dbNoStatic[3];
        this.complexity = dbNoStatic[4];
    }

    public int getOrder() {
        return order;
    }

    public int getStatus() {
        return status;
    }

    public int getPointPrincipal() {
        return pointPrincipal;
    }

    public String getDate() {
        return date;
    }

    public String getExample() {
        return example;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getWord() {
        return word;
    }

    public String getType() {
        return type;
    }

    public String getBook() {
        return book;
    }

    public String getUnit() {
        return unit;
    }

    public String getComplexity() {
        return complexity;
    }


    public void setStatus(int status) {
        this.status = status;
    }

    public void setPointPrincipal(int pointPrincipal) {
        this.pointPrincipal = pointPrincipal;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public String getWordTranslation() {
        return wordTranslation;
    }

    public String getDefTranslation() {
        return defTranslation;
    }

    public   String toString ()
    {
        String cadena="";
        cadena =
                getOrder()+","+
                getStatus()+","+
                getPointPrincipal()+","+
                getDate()+","+
                getComplexity();

        return cadena;
    }

}
