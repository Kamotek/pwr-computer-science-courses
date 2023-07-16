public class Kompozytor {
    String nazwisko;
    String imie;

    public Kompozytor(String nazwisko, String imie) {
        this.nazwisko = nazwisko;
        this.imie = imie;
    }

    @Override
    public String toString() {
        return "Kompozytor{" +
                "nazwisko='" + nazwisko + '\'' +
                ", imie='" + imie + '\'' +
                '}';
    }
}
