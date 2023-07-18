import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class AlgorytmFCFS implements AlgorytmDostepu{
    private int zegar;
    ArrayList<Proces> dynamicznaTablicaProcesowFCFS;
    ArrayList<Proces> statycznaTablicaProcesowFCFS;

    public AlgorytmFCFS(int zegar) {
        this.zegar = zegar;
        this.dynamicznaTablicaProcesowFCFS = new ArrayList<>();
        this.statycznaTablicaProcesowFCFS = new ArrayList<>();


        try {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("procesy.txt"));

                Proces proces;
                while ((proces = (Proces) ois.readObject()) != null) {
                    this.statycznaTablicaProcesowFCFS.add(proces.clone());
                    this.dynamicznaTablicaProcesowFCFS.add(proces.clone());
                }

                ois.close();
            }catch(ClassNotFoundException y){System.out.println("Klasa nie znaleziona FCFS");}
        }catch(IOException x){}

    }

    public  int wypisz(){
       return statycznaTablicaProcesowFCFS.size() - dynamicznaTablicaProcesowFCFS.size();
    }

    @Override
    public Wynik obliczIloscWykonanychProcesowStalych() {
        int i = 0;
        int iloscWykonanychProcesow = 0;
        int czasOczekiwania = 0;
        int czasObslugiProcesu = 0;
        int iloscZmianRzadania = 0;
        int iloscZastosowanychSortowan = 0;
        int iloscWszystkichProcesow = statycznaTablicaProcesowFCFS.size();

        while(zegar > 0){
            statycznaTablicaProcesowFCFS.get(i).wykonaj(1);
            for (Proces x: statycznaTablicaProcesowFCFS
            ) {
                if(x.isRozpoczety()) czasObslugiProcesu++;
                if(!x.isRozpoczety()) czasOczekiwania++;
            }
            if(statycznaTablicaProcesowFCFS.get(i).getProcesDlugosc() == 0) {
                iloscWykonanychProcesow++;
                i++;
                iloscZmianRzadania++;
            }
            zegar--;
        }

        int sredniCzasOczekiwania = czasOczekiwania/(iloscWykonanychProcesow);
        int sredniCzasObslugiProcesu = czasObslugiProcesu/iloscWykonanychProcesow;

        return new Wynik(iloscWykonanychProcesow, sredniCzasOczekiwania, iloscZmianRzadania, iloscZastosowanychSortowan, sredniCzasObslugiProcesu);
    }

    @Override
    public Wynik obliczIloscWykonanychProcesowDynamicznie(ArrayList<Boolean> wystapienieProcesu) {
        int indexWystapieniaProcesu = 0;
        int indexKolejnegoProcesu = 0;
        int iloscWykonanychProcesow = 0;
        int czasOczekiwania = 0;
        int czasObslugiProcesu = 0;
        int iloscZmianRzadania = 0;
        int iloscZastosowanychSortowan = 0;
        int iloscZaglodzonych = 0;
        int iloscWszystkichProcesow=0;
        ArrayList<Proces> wykonywanaKolejkaFCFS = new ArrayList<>();

        while(zegar > 0){
            if(wystapienieProcesu.get(indexWystapieniaProcesu) || wykonywanaKolejkaFCFS.size() == 0) {
                wykonywanaKolejkaFCFS.add(dynamicznaTablicaProcesowFCFS.get(indexKolejnegoProcesu));
                indexKolejnegoProcesu++;
                iloscWszystkichProcesow++;
            }
            indexWystapieniaProcesu++;
            if(wykonywanaKolejkaFCFS.get(0)!=null){
                wykonywanaKolejkaFCFS.get(0).wykonaj(1);
                for (Proces x: wykonywanaKolejkaFCFS
                ) {
                    if(x.isRozpoczety()) czasObslugiProcesu++;
                    if(!x.isRozpoczety()) czasOczekiwania++;
                }
                zegar--;
                if(wykonywanaKolejkaFCFS.get(0).isWykonany()){
                    wykonywanaKolejkaFCFS.remove(0);
                    iloscZmianRzadania++;
                    iloscWykonanychProcesow++;
                }
            }
            if(indexWystapieniaProcesu == wystapienieProcesu.size())
                indexWystapieniaProcesu = 0;
        }

        int sredniCzasOczekiwania = czasOczekiwania/(iloscWszystkichProcesow);
        int sredniCzasObslugiProcesu = czasObslugiProcesu/iloscWykonanychProcesow;


        for (Proces x: wykonywanaKolejkaFCFS
        ) {
            if(x.isZaglodzony())
                iloscZaglodzonych++;
        }


        return new Wynik(iloscWykonanychProcesow, sredniCzasOczekiwania, iloscZmianRzadania, iloscZastosowanychSortowan, sredniCzasObslugiProcesu, iloscZaglodzonych);
    }
}
