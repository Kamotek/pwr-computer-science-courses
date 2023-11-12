public class Wynik {
    int iloscWykonanychProcesow; // ilosc procesow jaki wykonal algorytm
    int sredniCzasOczekiwania; // sredni czas ile proces czekal na obsluzenie
    int iloscZmianRzadania; // ile razy przelaczylismy wykonywanie kolejnego procesu
    int iloscZastosowanychSortowan; // ile razy ingerowalismy w kolejnosc procesow w kolejce
    int sredniCzasObslugiProcesu; // ile srednio uplynelo od rozpoczecia do zakonczenia procesu

    int iloscZaglodzonych;

    public Wynik(int iloscWykonanychProcesow, int sredniCzasOczekiwania, int iloscZmianRzadania, int iloscZastosowanychSortowan, int sredniCzasObslugiProcesu) {
        this.iloscWykonanychProcesow = iloscWykonanychProcesow;
        this.sredniCzasOczekiwania = sredniCzasOczekiwania;
        this.iloscZmianRzadania = iloscZmianRzadania;
        this.iloscZastosowanychSortowan = iloscZastosowanychSortowan;
        this.sredniCzasObslugiProcesu = sredniCzasObslugiProcesu;
        this.iloscZaglodzonych = 0;
    }

    public Wynik(int iloscWykonanychProcesow, int sredniCzasOczekiwania, int iloscZmianRzadania, int iloscZastosowanychSortowan, int sredniCzasObslugiProcesu, int iloscZaglodzonych) {
        this.iloscWykonanychProcesow = iloscWykonanychProcesow;
        this.sredniCzasOczekiwania = sredniCzasOczekiwania;
        this.iloscZmianRzadania = iloscZmianRzadania;
        this.iloscZastosowanychSortowan = iloscZastosowanychSortowan;
        this.sredniCzasObslugiProcesu = sredniCzasObslugiProcesu;
        this.iloscZaglodzonych = iloscZaglodzonych;
    }

    @Override
    public String toString() {
        return "Wynik:  \n" +
                " Ilość wykonanych procesów: " + iloscWykonanychProcesow +
                "\n Średni czas oczekiwania: " + sredniCzasOczekiwania +
                "\n Ilość zmian rządania: " + iloscZmianRzadania +
                "\n Ilość zastosowanych sortowań: " + iloscZastosowanychSortowan +
                "\n Średni czas obsługi jednego procesu: " + sredniCzasObslugiProcesu +
                "\n Ilość niewykonanych procesów z listy: " + iloscZaglodzonych;
    }
}
