package com.example.NewAllinOne;

public class Essential
{
    private  int order;
    private int stRead;
    private int stListen;
    private int stMultiChoice;
    private int stTranslationMc;
    private int stMatch;
    private int stTranslation;
    private int stExample;

    private int stDefinition;
    private int stComplete;
    private int stReadComplex;
    private int stHanged;
    private int stActivate;
    private int stCardinal;
    private int puntuation;
    private int failed;
    private String date;

    private String example;
    private String definition;
    private String book;
    private String unit;
    private String word;
    private String type;
    private String wordTranslated;
    private String definitionTranslate;

    public Essential() {}
    public Essential(String[] NoStatic, String[] estatica)
    {
        this.order              = Integer.parseInt(NoStatic[0]);
        this.stRead             = Integer.parseInt(NoStatic[1]);
        this.stListen           = Integer.parseInt(NoStatic[2]);
        this.stMultiChoice      = Integer.parseInt(NoStatic[3]);
        this.stTranslationMc    = Integer.parseInt(NoStatic[4]);
        this.stMatch            = Integer.parseInt(NoStatic[5]);
        this.stTranslation      = Integer.parseInt(NoStatic[6]);
        this.stExample          = Integer.parseInt(NoStatic[7]);
        this.stDefinition       = Integer.parseInt(NoStatic[8]);
        this.stComplete         = Integer.parseInt(NoStatic[9]);
        this.stReadComplex      = Integer.parseInt(NoStatic[10]);
        this.stHanged           = Integer.parseInt(NoStatic[11]);
        this.stActivate         = Integer.parseInt(NoStatic[12]);
        this.stCardinal         = Integer.parseInt(NoStatic[13]);
        this.puntuation         = Integer.parseInt(NoStatic[14]);
        this.failed             = Integer.parseInt(NoStatic[15]);
        this.date               = NoStatic[16];

        this.example             = estatica[1];
        this.definition          = estatica[2];
        this.book                = estatica[3];
        this.unit                = estatica[4];
        this.word                = estatica[5];
        this.type                = estatica[6];
        this.wordTranslated      = estatica[7];
        this.definitionTranslate = estatica[8];
    }

    public int getOrder() { return order; }

    public int getStRead() {
        return stRead;
    }

    public int getStListen() {
        return stListen;
    }

    public int getStMultiChoice() {
        return stMultiChoice;
    }

    public int getStTranslationMc() {
        return stTranslationMc;
    }

    public int getStMatch() {
        return stMatch;
    }

    public int getStTranslation() {
        return stTranslation;
    }

    public int getStExample() {
        return stExample;
    }

    public int getStDefinition() {
        return stDefinition;
    }

    public int getStComplete() {
        return stComplete;
    }

    public int getStReadComplex() {
        return stReadComplex;
    }

    public int getStHanged() {
        return stHanged;
    }

    public int getStActivate() {
        return stActivate;
    }

    public int getStCardinal() {
        return stCardinal;
    }

    public int getPuntuation() {
        return puntuation;
    }

    public int getFailed() {
        return failed;
    }

    public String getDate() {
        return date;
    }

    public String getExample() {
        return example;
    }

    public String getDefiniton() {
        return definition;
    }

    public String getBook() {
        return book;
    }

    public String getUnit() {
        return unit;
    }

    public String getWord() {
        return word;
    }

    public String getType() {
        return type;
    }

    public String getWordTranslated() {
        return wordTranslated;
    }

    public String getDefinitionTranslate() {
        return definitionTranslate;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setStRead(int stRead) {
        this.stRead = stRead;
    }

    public void setStListen(int stListen) {
        this.stListen = stListen;
    }

    public void setStMultiChoice(int stMultiChoice) {
        this.stMultiChoice = stMultiChoice;
    }

    public void setStTranslationMc(int stTranslationMc) {
        this.stTranslationMc = stTranslationMc;
    }

    public void setStMatch(int stMatch) {
        this.stMatch = stMatch;
    }

    public void setStTranslation(int stTranslation) {
        this.stTranslation = stTranslation;
    }

    public void setStExample(int stExample) {
        this.stExample = stExample;
    }

    public void setStDefinition(int stDefinition) {
        this.stDefinition = stDefinition;
    }

    public void setStComplete(int stComplete) {
        this.stComplete = stComplete;
    }

    public void setStReadComplex(int stReadComplex) {
        this.stReadComplex = stReadComplex;
    }

    public void setStHanged(int stHanged) {
        this.stHanged = stHanged;
    }

    public void setStActivate(int stActivate) {
        this.stActivate = stActivate;
    }

    public void setStCardinal(int stCardinal) {
        this.stCardinal = stCardinal;
    }

    public void setPuntuation(int puntuation) {
        this.puntuation = puntuation;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString ()
    {
        String cadena="";
        cadena = order+","+
                 stRead+","+
                stListen+","+
                stMultiChoice+","+
                stTranslationMc+","+
                stMatch+","+
                stTranslation+","+
                stExample+","+
                stDefinition+","+
                stComplete+","+
                stReadComplex+","+
                stHanged+","+
                stActivate+","+
                stCardinal+","+
                puntuation+","+
                failed+","+
                date+",";

        return cadena;
    }

}
