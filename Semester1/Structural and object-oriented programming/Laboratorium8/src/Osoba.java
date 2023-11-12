public abstract class Osoba {
    protected String nazwisko;
    protected String pesel;

    public Osoba(){
        this.nazwisko = "";
        this.pesel = "";
    }

    public Osoba(String nazwisko, String pesel){
        this.nazwisko = nazwisko;
        this.pesel = pesel;
    }

    @Override
    public String toString() {
        return nazwisko + " " + pesel + " ";
    }
    public abstract double Oblicz();
}
