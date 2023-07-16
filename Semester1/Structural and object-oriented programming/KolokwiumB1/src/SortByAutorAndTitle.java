import java.util.Comparator;

public class SortByAutorAndTitle implements Comparator<Ksiazka> {
    @Override
    public int compare(Ksiazka o1, Ksiazka o2) {
        String x = o1.autor.nazwisko + o1.tytul;
        String y = o2.autor.nazwisko + o2.tytul;


        return x.compareTo(y);
    }
}
