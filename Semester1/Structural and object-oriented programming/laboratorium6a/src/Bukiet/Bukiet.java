package Bukiet;

import Florysta.*;

public class Bukiet {
    private Kwiat kwiat;
    private boolean dostepny;

    public Bukiet(){
        this.kwiat = new Kwiat();
        this.dostepny = false;
    }
    public Bukiet(Kwiat kwiat, boolean dostepny) {
        this.kwiat = kwiat;
        this.dostepny = dostepny;
    }

    public Kwiat getKwiat() {
        return kwiat;
    }

    public void setKwiat(Kwiat kwiat) {
        this.kwiat = kwiat;
    }

    public boolean isDostepny() {
        return dostepny;
    }

    public void setDostepny(boolean dostepny) {
        this.dostepny = dostepny;
    }


    public void getStan(){
        System.out.println("kwiat=" + getKwiat().getNazwaKwiatu() + " dostepny=" + isDostepny());
    }

    public void jestwKwiaciarni(){
        //Florysta florysta = new Florysta();
        Florysta.tworzBukiet(this);
    }

    public void brakwKwiaciarni(){
        //Florysta florysta = new Florysta();
        Florysta.sprzedajBukiet(this);
    }
}
