import java.util.Comparator;

public class SortByAutor implements Comparator<Kompozytor>{
    public int compare(Kompozytor a, Kompozytor b){
        return b.nazwisko.compareTo(a.nazwisko);
    }
}
