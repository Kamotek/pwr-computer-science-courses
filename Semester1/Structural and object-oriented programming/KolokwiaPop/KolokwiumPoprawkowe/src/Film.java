public class Film {
    private String tytul;
    int[] otrzymaneOceny;

    static int licznik=0;

    public Film(String tytul, int m){
        this.tytul = tytul;
        this.otrzymaneOceny = new int[m];
    }

    public String getTytul() {
        return tytul;
    }

    public int[] getOtrzymaneOceny() {
        return otrzymaneOceny;
    }

    public static int getLicznik() {
        return licznik;
    }

    public void otrzymajOcene(int ocena){
        otrzymaneOceny[licznik] = ocena;
        licznik++;
    }
}
