import java.io.Serializable;

public class PracownikEtatowy extends Pracownik implements Serializable {
    private float etat;
    private double stawka;

    public PracownikEtatowy(){
        this.etat = 0;
        this.stawka = 0;
    }

    public PracownikEtatowy(float etat, double stawka) {
        this.etat = etat;
        this.stawka = stawka;
    }

    public PracownikEtatowy(String nazwisko, String imie, long pesel, String stanowisko, int staz, float etat, double stawka) {
        super(nazwisko, imie, pesel, stanowisko, staz);
        this.etat = etat;
        this.stawka = stawka;
    }

    public float getEtat() {
        return etat;
    }

    public double getStawka() {
        return stawka;
    }

    public double pensja(){
        return getEtat()*getStawka();
    }

    @Override
    public String toString() {
        return String.format("%-20s | %-20s %-20f | %-20s  %-20f | %-20s %-20f","Pracownik etatowy", "Etat:", getEtat(), "Stawka:", getStawka(), "Pensja:", pensja());
    }
}
