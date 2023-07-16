import java.util.Comparator;

public class SortByPages implements Comparator<Ksiazka> {
    public int compare(Ksiazka a, Ksiazka b){
        return a.liczbaStron - b.liczbaStron;
    }
}
