import java.util.ArrayList;

public class Plyta {
    int numerKatalogowy;
    ArrayList<Utwor> utwory = new ArrayList<>();

    public Plyta(int numerKatalogowy, ArrayList<Utwor> utwory) {
        this.numerKatalogowy = numerKatalogowy;
        this.utwory = utwory;
    }
}
