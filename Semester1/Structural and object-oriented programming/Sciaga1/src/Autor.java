public class Autor {
    String nazwisko;
    String imie;

    public Autor(String nazwisko, String imie){
        this.nazwisko = nazwisko;
        this.imie = imie;
    }
    public Autor(){
        this.nazwisko = "Nazwisko";
        this.imie = "Imie";
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nazwisko='" + nazwisko + '\'' +
                ", imie='" + imie + '\'' +
                '}';
    }
}
