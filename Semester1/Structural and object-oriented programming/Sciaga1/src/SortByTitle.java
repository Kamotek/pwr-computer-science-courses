import java.util.Comparator;

public class SortByTitle implements Comparator<Ksiazka> {

    public SortByTitle(){}
    public  int compare(Ksiazka a, Ksiazka b){
        return a.tytul.compareTo(b.tytul);
    }
}
