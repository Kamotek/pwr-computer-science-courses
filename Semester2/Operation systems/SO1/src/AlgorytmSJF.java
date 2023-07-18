import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;

public class AlgorytmSJF implements AlgorytmDostepu{

    private int zegar;
    ArrayList<Proces> dynamicznaTablicaProcesowSJF;
    ArrayList<Proces> statycznaTablicaProcesowSJF;

    public AlgorytmSJF(int zegar) {
        this.zegar = zegar;
        this.dynamicznaTablicaProcesowSJF = new ArrayList<>();
        this.statycznaTablicaProcesowSJF = new ArrayList<>();

        try {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("procesy.txt"));

                Proces proces;
                while ((proces = (Proces) ois.readObject()) != null) {
                    this.statycznaTablicaProcesowSJF.add(proces.clone());
                    this.dynamicznaTablicaProcesowSJF.add(proces.clone());
                }

                ois.close();
            }catch(ClassNotFoundException y){System.out.println("Klasa nie znaleziona ");}
        }catch(IOException x){}
    }

    @Override
    public Wynik obliczIloscWykonanychProcesowStalych() {
        Collections.sort(statycznaTablicaProcesowSJF, new ComparatorSJF());
        int iloscWykonanychProcesow = 0;
        int czasOczekiwania = 0;
        int czasObslugiProcesu = 0;
        int iloscZmianRzadania = 0;
        int iloscZastosowanychSortowan = 1;
        int iloscWszystkichProcesow = statycznaTablicaProcesowSJF.size();
        int i = 0;
        while(zegar > 0){
            statycznaTablicaProcesowSJF.get(i).wykonaj(1);
            for (Proces x: statycznaTablicaProcesowSJF
            ) {
                if(x.isRozpoczety()) czasObslugiProcesu++;
                if(!x.isRozpoczety()) czasOczekiwania++;
            }
            if(statycznaTablicaProcesowSJF.get(i).getProcesDlugosc() == 0) {
                iloscZmianRzadania++;
                iloscWykonanychProcesow++;
                i++;
            }
            zegar--;
        }

        int sredniCzasObslugiProcesu = czasObslugiProcesu/iloscWykonanychProcesow;
        int sredniCzasOczekiwania = czasOczekiwania/iloscWykonanychProcesow;

        return new Wynik(iloscWykonanychProcesow, sredniCzasOczekiwania, iloscZmianRzadania, iloscZastosowanychSortowan, sredniCzasObslugiProcesu);
    }

    @Override
    public Wynik obliczIloscWykonanychProcesowDynamicznie(ArrayList<Boolean> wystapienieProcesu) {
        int indexWystapieniaProcesu = 0;
        ArrayList<Proces> wykonywanaKolejkaSJF = new ArrayList<>();
        int indexKolejnegoProcesu = 0;
        int iloscWykonanychProcesow = 0;
        int iloscZastosowanychSortowan = 0;
        int czasOczekiwania = 0;
        int czasObslugiProcesu = 0;
        int iloscZmianRzadania = 0;
        int iloscZaglodzonych = 0;
        int iloscWszystkichProcesow = 0;

        while(zegar > 0){
            if(wykonywanaKolejkaSJF.size() == 0) {
                wykonywanaKolejkaSJF.add(dynamicznaTablicaProcesowSJF.get(indexKolejnegoProcesu));
                indexKolejnegoProcesu++;
                iloscWszystkichProcesow++;
            }
            if(wystapienieProcesu.get(indexWystapieniaProcesu)){

                wykonywanaKolejkaSJF.add(dynamicznaTablicaProcesowSJF.get(indexKolejnegoProcesu));
                indexKolejnegoProcesu++;
                Collections.sort(wykonywanaKolejkaSJF, new ComparatorSJF());
                iloscZastosowanychSortowan++;

            }
                wykonywanaKolejkaSJF.get(0).wykonaj(1);
            for (Proces x: wykonywanaKolejkaSJF
            ) {
                if(x.isRozpoczety()) czasObslugiProcesu++;
                if(!x.isRozpoczety()) czasOczekiwania++;

            }
                zegar--;

                if(wykonywanaKolejkaSJF.get(0).isWykonany()){
                    wykonywanaKolejkaSJF.remove(0);
                    iloscZmianRzadania++;
                    iloscWykonanychProcesow++;
                }
            if(indexWystapieniaProcesu == wystapienieProcesu.size())
                indexWystapieniaProcesu = 0;
            if(indexKolejnegoProcesu == dynamicznaTablicaProcesowSJF.size())
                indexKolejnegoProcesu = 0;

        }

        int sredniCzasObslugiProcesu = czasObslugiProcesu/iloscWykonanychProcesow;
        int sredniCzasOczekiwania = czasOczekiwania/iloscWszystkichProcesow;

        for (Proces x: wykonywanaKolejkaSJF
        ) {
            if(x.isZaglodzony())
                iloscZaglodzonych++;
        }


        return new Wynik(iloscWykonanychProcesow, sredniCzasOczekiwania, iloscZmianRzadania, iloscZastosowanychSortowan, sredniCzasObslugiProcesu, iloscZaglodzonych);

    }
}
