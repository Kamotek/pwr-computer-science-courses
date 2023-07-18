import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class AlgorytmRR implements AlgorytmDostepu{

  ArrayList<Proces> dynamicznaTablicaProcesowRR;
  ArrayList<Proces> statycznaTablicaProcesowRR;
    private int zegar;
    private final int kwantCzasu;

    public ArrayList<Proces> getStatycznaTablicaProcesowRR() {
        return statycznaTablicaProcesowRR;
    }

    public AlgorytmRR(int zegar, int kwantCzasu) {
        this.dynamicznaTablicaProcesowRR = new ArrayList<>();
        this.statycznaTablicaProcesowRR = new ArrayList<>();

        this.zegar = zegar;
        this.kwantCzasu = kwantCzasu;

        try {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("procesy.txt"));

                Proces proces;
                while ((proces = (Proces) ois.readObject()) != null) {
                    this.dynamicznaTablicaProcesowRR.add(proces.clone());
                    this.statycznaTablicaProcesowRR.add(proces.clone());
                }
                ois.close();


            }catch(ClassNotFoundException y){System.out.println("Klasa nie znaleziona RR");}
        }catch(IOException x){}
    }

    public ArrayList<Proces> getDynamicznaTablicaProcesowRR() {
        return dynamicznaTablicaProcesowRR;
    }
    public int getZegar() {
        return zegar;
    }

    public void setZegar(int zegar) {
        this.zegar = zegar;
    }
    public int getKwantCzasu() {
        return kwantCzasu;
    }
    @Override
    public Wynik obliczIloscWykonanychProcesowStalych() {
        int i = 0;
        int iloscWykonanychProcesow = 0;
        int czasOczekiwania = 0;
        int czasObslugiProcesu = 0;
        int iloscZmianRzadania = 0;
        int iloscZastosowanychSortowan = 0;
        int iloscWszystkichProcesow = statycznaTablicaProcesowRR.size();
        Proces proces;

        while(getZegar()>0){
            proces = statycznaTablicaProcesowRR.get(i);
            for(int cykl = 0; cykl < getKwantCzasu(); cykl++) {
                for (Proces x: statycznaTablicaProcesowRR
                ) {
                    if(x.isRozpoczety()) czasObslugiProcesu++;
                    if(!x.isRozpoczety()) czasOczekiwania++;
                }
                zegar--;
                proces.wykonaj(1);
                if (proces.isWykonany()) {
                    iloscWykonanychProcesow++;
                    statycznaTablicaProcesowRR.remove(i);
                    i--;
                    break;
                }
            }
            iloscZmianRzadania++;
            i++;
            if (i >= statycznaTablicaProcesowRR.size())
                i = 0;
        }
        if(iloscWykonanychProcesow == 0) {
            iloscWykonanychProcesow++;
            System.out.println("Zaden proces w tym algorytmie nie zostal wykonany :(");
        }

        int sredniCzasOczekiwania = czasOczekiwania/(iloscWykonanychProcesow);
        int sredniCzasObslugiProcesu = czasObslugiProcesu/iloscWykonanychProcesow;

        return new Wynik(iloscWykonanychProcesow, sredniCzasOczekiwania, iloscZmianRzadania, iloscZastosowanychSortowan, sredniCzasObslugiProcesu);
    }

    @Override
    public Wynik obliczIloscWykonanychProcesowDynamicznie(ArrayList<Boolean> wystapienieProcesu) {
        ArrayList<Proces> wykonywanaKolejkaRR = new ArrayList<>();
        int indexWystapieniaProcesu = 0;
        int indexKolejnegoProcesu = 0;
        int indexWykonywanegoProcesu = 0;
        int iloscWykonanychProcesow = 0;
        int iloscZmianRzadania = 0;
        int iloscZastosowanychSortowan = 0;
        int czasObslugiProcesu = 0;
        int czasOczekiwania = 0;
        int iloscZaglodzonych = 0;
        int iloscWszystkichProcesow = 0;

        while(zegar > 0){
            if(wystapienieProcesu.get(indexWystapieniaProcesu)){
                wykonywanaKolejkaRR.add(dynamicznaTablicaProcesowRR.get(indexKolejnegoProcesu));
                indexKolejnegoProcesu++;
                iloscWszystkichProcesow++;
            }
            if(wykonywanaKolejkaRR.size()!=0) {
                for (int i = 0; i < kwantCzasu; i++) {
                    for (Proces x : wykonywanaKolejkaRR
                    ) {
                        if (x.isRozpoczety()) czasObslugiProcesu++;
                        if (!x.isRozpoczety()) czasOczekiwania++;
                    }
                    zegar--;
                    wykonywanaKolejkaRR.get(indexWykonywanegoProcesu).wykonaj(1);
                    if (wykonywanaKolejkaRR.get(indexWykonywanegoProcesu).isWykonany()) {
                        iloscWykonanychProcesow++;
                        wykonywanaKolejkaRR.remove(indexWykonywanegoProcesu);
                        indexWykonywanegoProcesu--;
                        break;
                    }
                }
                indexWykonywanegoProcesu++;
                iloscZmianRzadania++;
                if (indexWykonywanegoProcesu >= wykonywanaKolejkaRR.size())
                    indexWykonywanegoProcesu = 0;
            }
                if(indexKolejnegoProcesu>=wystapienieProcesu.size())
                    indexKolejnegoProcesu = 0;
        }



        for (Proces x: wykonywanaKolejkaRR
             ) {
            if(x.isZaglodzony())
                iloscZaglodzonych++;
        }


        if(iloscWykonanychProcesow == 0) {
            iloscWykonanychProcesow++;
            System.out.println("Zaden proces w tym algorytmie nie zostal wykonany :(");
        }

        int sredniCzasObslugiProcesu = czasObslugiProcesu/iloscWykonanychProcesow;
        int sredniCzasOczekiwania = czasOczekiwania/(iloscWszystkichProcesow);

        return new Wynik(iloscWykonanychProcesow, sredniCzasOczekiwania, iloscZmianRzadania, iloscZastosowanychSortowan, sredniCzasObslugiProcesu, iloscZaglodzonych);
    }
}
