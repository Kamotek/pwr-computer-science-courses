public class Utwor {
    String tytul;
    Kompozytor kompozytor;
    double czasWykonania;

    public Utwor(String tytul, Kompozytor kompozytor, double czasWykonania) {
        this.tytul = tytul;
        this.kompozytor = kompozytor;
        this.czasWykonania = czasWykonania;
    }

    @Override
    public String toString() {
        return "Utwor{" +
                "tytul='" + tytul + '\'' +
                ", kompozytor=" + kompozytor +
                ", czasWykonania=" + czasWykonania +
                '}';
    }
}
