import java.util.Comparator;

public class SortByAge implements Comparator<Ksiazka> {
    @Override
    public int compare(Ksiazka o1, Ksiazka o2) {
        return o2.rokWydania-o1.rokWydania;
    }
}
