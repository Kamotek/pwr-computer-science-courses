public class Pracownik {
    String nazwisko;
    String imie;
    int wiek;
    String sposobWynagradzania;

    double kwota;
    double pensja;
    int godziny;
    double stawka;
    double koszta;

    double wyplata;

    public Pracownik(){}

    public Pracownik(String nazwisko, String imie, int wiek, String sposobWynagradzania, double pensja, double stawka, double koszta, int godziny) {
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.wiek = wiek;
        this.sposobWynagradzania = sposobWynagradzania;
        this.kwota = kwota;
        this.pensja = pensja;
        this.godziny = godziny;
        this.stawka = stawka;
        this.wyplata = wyplata;
        this.koszta = koszta;
    }

    public void ustawWyplate(){
        if(sposobWynagradzania.equals("etat"))
            this.wyplata = new Etat(pensja).obliczanieWynagrodzenia();
        if(sposobWynagradzania.equals("zlecenie"))
            this.wyplata = new Zlecenie(godziny, stawka, koszta).obliczanieWynagrodzenia();
    }

    public double getWyplata() {
        return wyplata;
    }

    public void setSposobWynagradzania(String sposobWynagradzania) {
        this.sposobWynagradzania = sposobWynagradzania;
    }
}

