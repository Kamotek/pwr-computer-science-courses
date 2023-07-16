import java.util.Comparator;

public class SortByAutor implements Comparator<Ksiazka> {
    public int compare(Ksiazka a, Ksiazka b){
        return a.autor.nazwisko.compareTo(b.autor.nazwisko);
    }
}
