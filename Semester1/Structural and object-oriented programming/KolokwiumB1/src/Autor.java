public class Autor {
    String nazwisko;
    String imie;

    public Autor(String nazwisko, String imie) {
        this.nazwisko = nazwisko;
        this.imie = imie;
    }

    @Override
    public String toString() {
        return "Autor [" +
                "nazwisko = '" + nazwisko + '\'' +
                ", imie = '" + imie + '\'' +
                ']';
    }
}
