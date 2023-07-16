import java.util.Comparator;

public class SortBySurname implements Comparator<Autor> {
    @Override
    public int compare(Autor o1, Autor o2) {
        return o1.nazwisko.compareTo(o2.nazwisko);
    }
}
