

// sredni czas wykonania jednego procesu
// ile procesow obsluzymy w ograniczonym czasie


// sredni czas oczekiwania procesu na rozpoczecie
// sredni czas oczekiwania procesu na zakonczenie
// ilosc zaglodzonych procesow
// zabezpieczyc na wszystko

import java.util.ArrayList;

public class Main {

    static ArrayList<Boolean> wystapienieProcesu = new ArrayList<>();
    public static void generowanieProcesow(int minimalnaDlugosc, int maksymalnaDlugosc, int iloscProcesow, int ziarno, int szanse) {
        GeneratorProcesow generator =  new GeneratorProcesow(minimalnaDlugosc, maksymalnaDlugosc, iloscProcesow, ziarno);
        generator.serializatorProcesow();
        wystapienieProcesu = generator.generatorCzasuDodaniaProcesu(szanse);
    }
    public static Wynik algorytmFCFS(int zegar, boolean czyDynamiczny){
        if(czyDynamiczny){ return new AlgorytmFCFS(zegar).obliczIloscWykonanychProcesowDynamicznie(wystapienieProcesu);
        }else {
            return new AlgorytmFCFS(zegar).obliczIloscWykonanychProcesowStalych();
        }
    }

    public static Wynik algorytmRR(int zegar, int kwantCzasu, boolean czyDynamiczny){
        if(czyDynamiczny){ return new AlgorytmRR(zegar, kwantCzasu).obliczIloscWykonanychProcesowDynamicznie(wystapienieProcesu);
        }else {
            return new AlgorytmRR(zegar, kwantCzasu).obliczIloscWykonanychProcesowStalych();
        }    }

    public static Wynik algorytmSJF(int zegar, boolean czyDynamiczny){
        if(czyDynamiczny){ return new AlgorytmSJF(zegar).obliczIloscWykonanychProcesowDynamicznie(wystapienieProcesu);
        }else {
            return new AlgorytmSJF(zegar).obliczIloscWykonanychProcesowStalych();
        }
        }


    public static void main(String[] args) {

        int zegar = 25000;



        int minimalnaDlugosc = 1;
        int maksymalnaDlugosc = 10;
        int ziarno = 0;
        int szanse = 2;


        if(szanse<1 || szanse > 100)
            szanse = 1;
        if(ziarno<0 || ziarno > 3)
            ziarno = 0;
        if(maksymalnaDlugosc < minimalnaDlugosc)
            minimalnaDlugosc = maksymalnaDlugosc-1;

        int kwantCzasu = minimalnaDlugosc;



        // minimalna dlugosc powinna wynosic kwant czasu
        generowanieProcesow(minimalnaDlugosc,maksymalnaDlugosc, zegar, ziarno,szanse);

        System.out.println("---");
        System.out.println("Prosty pomiar, x wykonanych procesów w określonym czasie: ");
        System.out.println("---");
       System.out.println("Ilosc wykonanych procesow dla algorytmu FCFS, statycznie, "+algorytmFCFS(zegar, false));
        System.out.println("---");
        System.out.println("Ilosc wykonanych procesow dla algorytmu RR, statycznie, " + algorytmRR(zegar, kwantCzasu, false));
        System.out.println("---");
        System.out.println("Ilość wykonanych procesow dla algorytmu SJF, statycznie, " + algorytmSJF(zegar, false));
        System.out.println("---");

        System.out.println("Dynamiczny pomiar, sredni czas oczekiwania, ilosc zaglodzonych procesow etc");

       System.out.println("Ilosc wykonanych procesow dla algorytmu FCFS, dynamicznie, "+algorytmFCFS(zegar, true));
        System.out.println("---");
        System.out.println("Ilosc wykonanych procesow dla algorytmu RR, dynamicznie, " + algorytmRR(zegar, kwantCzasu, true));
        System.out.println("---");
        System.out.println("Ilość wykonanych procesow dla algorytmu SJF, dynamicznie, " + algorytmSJF(zegar, true));
    }
}