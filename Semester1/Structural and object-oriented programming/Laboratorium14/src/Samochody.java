import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Samochody {
    private ArrayList<Auto> auta;

    public class ComparatorMarka{
        public Comparator<Auto> markaCompare = new Comparator<Auto>() {
            @Override
            public int compare(Auto o1, Auto o2) {
                return o1.getMarka().compareTo(o2.getMarka());
            }
        };
    }

    public class ComparatorKilometry{
        private Comparator<Auto> kilometryCompare = new Comparator<Auto>() {
            @Override
            public int compare(Auto o1, Auto o2) {
                return (o1.getLk())-(o2.getLk());
            }
        };
    }

    public class KilometryMarkaCompare{
        private Comparator<Auto> markaKilometryCompare = new Comparator<Auto>() {
            @Override
            public int compare(Auto o1, Auto o2) {
                String temp1 = o1.getMarka()+o1.getLk();
                String temp2 = o2.getMarka()+o2.getLk();
                return o1.getMarka().compareTo(o2.getMarka());
            }
        };

    }





    public Samochody() {
        this.auta = new ArrayList<>();
    }

    public Comparator<Auto> getMarkaCompare() {
        return new ComparatorMarka().markaCompare;
    }

    public Comparator<Auto> getKilometryCompare() {
        return new ComparatorKilometry().kilometryCompare;
    }

    public Comparator<Auto> getMarkaKilometryCompare() {
        return new KilometryMarkaCompare().markaKilometryCompare;
    }

    public ArrayList<Auto> getAuta() {
        return auta;
    }

    public void dodajAuto(String marka, int lk, long nrNadwozia, boolean diesel, String model, double zuzycie){
        auta.add(new AutoOsobowe(marka,lk,nrNadwozia,diesel,model,zuzycie));
    }
    public void dodajCiezarowke(String marka, int lk, long nrNadwozia, boolean diesel, String model, double zuzycie, boolean kratka){
        auta.add(new AutoCiezarowe(marka,lk,nrNadwozia,diesel,model,zuzycie,kratka));
    }

    public void sortByMarka(){
        Collections.sort(auta, getMarkaCompare());
    }

    public void sortByKilometry(){
        Collections.sort(auta, getKilometryCompare());
    }

    public void sortByMarkaThenKilometry(){
        Collections.sort(auta, getMarkaKilometryCompare());
    }

}
