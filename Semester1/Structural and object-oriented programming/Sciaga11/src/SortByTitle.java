import java.util.Comparator;

public class SortByTitle implements Comparator<Utwor> {
    @Override
    public int compare(Utwor a, Utwor b){
        return a.tytul.compareTo(b.tytul);
    }
}
