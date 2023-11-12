import java.util.Random;

public class Krytyk {
    private String pseudonim;
    private int filmyDoOcenienia;
    int[] wystawioneOceny;

    static int licznik = 0;

    public Krytyk(String pseudonim, int n){
        this.pseudonim = pseudonim;
        this.wystawioneOceny = new int[n];
        this.filmyDoOcenienia = n;
    }

    public void wystawOcene(Film film){
        Random random = new Random();
        int ocena = random.nextInt(7);

        film.otrzymajOcene(ocena);
        wystawioneOceny[licznik] = ocena;
        licznik++;
    }

    public String getPseudonim() {
        return pseudonim;
    }

    public int getFilmyDoOcenienia() {
        return filmyDoOcenienia;
    }

    public int[] getWystawioneOceny() {
        return wystawioneOceny;
    }

    public static int getLicznik() {
        return licznik;
    }

    public double policzOceny(){
        double suma = 0;
        for(int i = 0;i<licznik;i++){
            suma+=wystawioneOceny[i];
        }

        return suma/licznik;
    }
}
