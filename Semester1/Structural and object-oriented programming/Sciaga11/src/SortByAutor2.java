import java.util.Comparator;

public class SortByAutor2 implements Comparator<Utwor> {
    @Override
    public int compare(Utwor o1, Utwor o2) {
        return o1.kompozytor.nazwisko.compareTo(o2.kompozytor.nazwisko);
    }
}
