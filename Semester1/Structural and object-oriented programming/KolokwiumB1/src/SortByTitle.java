import java.util.Comparator;

public class SortByTitle implements Comparator<Ksiazka> {
    @Override
    public int compare(Ksiazka o1, Ksiazka o2) {
        return o1.tytul.compareTo(o2.tytul);
    }
}
