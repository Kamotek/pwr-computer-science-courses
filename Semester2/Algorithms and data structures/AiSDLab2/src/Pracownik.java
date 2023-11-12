import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public abstract class Pracownik {
    private String nazwisko;
    private String imie;
    private long pesel;
    private String stanowisko;
    private  int staz;

    public Pracownik(){
        this.nazwisko = "nazwisko";
        this.imie = "imie";
        this.pesel = 123456789;
        this.stanowisko = "stanowisko";
        this.staz = 0;
    }

    public Pracownik(String nazwisko, String imie, long pesel, String stanowisko, int staz) {
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.pesel = pesel;
        this.stanowisko = stanowisko;
        this.staz = staz;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public long getPesel() {
        return pesel;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public int getStaz() {
        return staz;
    }

    void wyswietl(){
        System.out.printf("%-12s %-12s %-12d %-12d, %-12s" , getImie(),getNazwisko(),getStaz(),getPesel(),getStanowisko());
    }

    @Override
    public String toString() {
        return String.format("%-12s %-12s %-12d %-12d, %-12s" , getImie(),getNazwisko(),getStaz(),getPesel(),getStanowisko());
    }

    public abstract double pensja();
}
