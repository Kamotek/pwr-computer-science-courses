public class Ksiazka {
    Autor autor;
    String tytul;
    String wydawnictwo;
    int rokWydania;

    public Ksiazka(Autor autor, String tytul, String wydawnictwo, int rokWydania) {
        this.autor = autor;
        this.tytul = tytul;
        this.wydawnictwo = wydawnictwo;
        this.rokWydania = rokWydania;
    }

    @Override
    public String toString() {
        return "Ksiazka " +
                "autor = " + autor +
                ", tytul = '" + tytul + '\'' +
                ", wydawnictwo = '" + wydawnictwo + '\'' +
                ", rokWydania = " + rokWydania +
                ']';
    }
}
