package Bukiet;

public class Kwiat {
    private int nazwaKwiatu;

    public Kwiat(){
        this.nazwaKwiatu = 0;
    }

    public Kwiat(int nazwaKwiatu) {
        this.nazwaKwiatu = nazwaKwiatu;
    }

    public int getNazwaKwiatu() {
        return nazwaKwiatu;
    }

    public void setNazwaKwiatu(int nazwaKwiatu) {
        this.nazwaKwiatu = nazwaKwiatu;
    }
}
