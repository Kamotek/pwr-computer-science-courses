public abstract class KartaKlienta {
    protected int numer;
    protected String nazwisko;

    public KartaKlienta(){
        this.numer = 0;
        this.nazwisko = null;
    }

    public KartaKlienta(int numer, String nazwisko){
        this.numer = numer;
        this.nazwisko = nazwisko;
    }

    public abstract String typ();
    @Override
    public String toString() {
        return "KartaKlienta{" +
                "numer=" + numer +
                ", nazwisko='" + nazwisko + '\'' +
                '}';
    }
    public abstract double rabat();
}
