import java.util.Comparator;
import java.util.Objects;

public class Ksiazka implements Comparable<Ksiazka> {
    String tytul;

    Autor autor;
    int liczbaStron;
    int numerEwidencyjny;

    public Ksiazka(String imie, String nazwisko, String tytul, int liczbaStron, int numerEwidencyjny ){
        this.tytul = tytul;
        this.liczbaStron = liczbaStron;
        this.numerEwidencyjny = numerEwidencyjny;
        this.autor = new Autor(nazwisko, imie);
    }

    @Override
    public String toString() {
        return "Ksiazka{" +
                "tytul='" + tytul + '\'' +
                ", autor=" + autor +
                ", liczbaStron=" + liczbaStron +
                ", numerEwidencyjny=" + numerEwidencyjny +
                '}';
    }

    @Override
    public int compareTo(Ksiazka o) {
      int x = o.liczbaStron;
      int y = this.liczbaStron;

      return y-x;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ksiazka ksiazka = (Ksiazka) o;

        return Objects.equals(tytul, ksiazka.tytul);
    }

    @Override
    public int hashCode() {
        return tytul != null ? tytul.hashCode() : 0;
    }
}
