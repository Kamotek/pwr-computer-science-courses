import java.io.Serializable;

public class PracownikGodzinowy extends Pracownik implements Serializable {
    private double stawka;
    private int liczba_godz;

    public PracownikGodzinowy(){
        this.stawka = 0;
        this.liczba_godz  = 0;
    }

    public PracownikGodzinowy(double stawka, int liczba_godz) {
        this.stawka = stawka;
        this.liczba_godz = liczba_godz;
    }

    public PracownikGodzinowy(String nazwisko, String imie, long pesel, String stanowisko, int staz, double stawka, int liczba_godz) {
        super(nazwisko, imie, pesel, stanowisko, staz);
        this.stawka = stawka;
        this.liczba_godz = liczba_godz;
    }

    public double getStawka() {
        return stawka;
    }

    public int getLiczba_godz() {
        return liczba_godz;
    }

    @Override
    public double pensja() {
        return getStawka()*getLiczba_godz();
    }

    @Override
    public String toString() {
        return String.format("%-20s | %-20s %-20f | %-20s  %-20d | %-20s %-20f", "Pracownik godzinowy", "Stawka",getStawka(),"Liczba Godzin", getLiczba_godz(), "Pensja", pensja());
    }
}
