package com.example.essentialallinone;

public class Essential
{
    private  int order;
    private int statusExample;
    private int statusDefinition;
    private int statusMultiChoise;
    private int statusRead;
    private int statusListen;
    private int statusMatch;
    private int statusHang;
    private int statusActive;
    private int statusPrincipal;
    private int pointPrincipal;
    private String date;
    private String example;
    private String meaning;
    private String word;
    private String type;
    private String book;
    private String unit;

    public Essential()
    {

    }
    public Essential(String[] db)
    {
        this.order = Integer.parseInt(db[0]);
        this.statusExample = Integer.parseInt(db[1]);
        this.statusDefinition = Integer.parseInt(db[2]);
        this.statusMultiChoise = Integer.parseInt(db[3]);
        this.statusRead = Integer.parseInt(db[4]);
        this.statusListen = Integer.parseInt(db[5]);
        this.statusMatch = Integer.parseInt(db[6]);
        this.statusHang = Integer.parseInt(db[7]);
        this.statusActive = Integer.parseInt(db[8]);
        this.statusPrincipal = Integer.parseInt(db[9]);
        this.pointPrincipal= Integer.parseInt(db[10]);
        this.date = db[11];
        this.example= db[12];
        this.meaning= db[13];
        this.book = db[14];
        this.unit = db[15];
        this.word = db[16];
        this.type = db[17];
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getStatusExample() {
        return statusExample;
    }

    public void setStatusExample(int statusExample) {
        this.statusExample = statusExample;
    }

    public int getStatusDefinition() {
        return statusDefinition;
    }

    public void setStatusDefinition(int statusDefinition) {
        this.statusDefinition = statusDefinition;
    }

    public int getStatusMultiChoise() {
        return statusMultiChoise;
    }

    public void setStatusMultiChoise(int statusMultiChoise) {
        this.statusMultiChoise = statusMultiChoise;
    }

    public int getStatusRead() {
        return statusRead;
    }

    public void setStatusRead(int statusRead) {
        this.statusRead = statusRead;
    }

    public int getStatusListen() {
        return statusListen;
    }

    public void setStatusListen(int statusListen) {
        this.statusListen = statusListen;
    }

    public int getStatusMatch() {
        return statusMatch;
    }

    public void setStatusMatch(int statusMatch) {
        this.statusMatch = statusMatch;
    }

    public int getStatusHang() {
        return statusHang;
    }

    public void setStatusHang(int statusHang) {
        this.statusHang = statusHang;
    }

    public int getStatusActive() {
        return statusActive;
    }

    public void setStatusActive(int statusActive) {
        this.statusActive = statusActive;
    }

    public int getStatusPrincipal() {
        return statusPrincipal;
    }

    public void setStatusPrincipal(int statusPrincipal) {
        this.statusPrincipal = statusPrincipal;
    }

    public int getPointPrincipal() {
        return pointPrincipal;
    }

    public void setPointPrincipal(int pointPrincipal) {
        this.pointPrincipal = pointPrincipal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

}
