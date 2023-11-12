import java.util.Random;

public class Pracownik extends Osoba {
    protected String stanowisko;
    protected int pensja;
    protected int stawkaNadgodzin;

    protected int[] liczbaNadgodzin = new int[6];

    public Pracownik(){
        super();
        this.stanowisko = "Brak";
        this.pensja = 0;
        this.stawkaNadgodzin = 0;
        this.liczbaNadgodzin = null;
    }
    public Pracownik(String nazwisko, String pesel, int pensja, int stawkaNadgodzin,String stanowisko){
        super(nazwisko, pesel);
        this.pensja = pensja;
        this.stawkaNadgodzin = stawkaNadgodzin;
        Random random = new Random();
        for (int i=0;i<liczbaNadgodzin.length;i++) {
            liczbaNadgodzin[i] = (random.nextInt(5));
        }
        this.stanowisko = stanowisko;
    }
    public double Oblicz(){
        double sumaNadgodzin = 0;
        if(liczbaNadgodzin!=null) {
            for (int i = 0; i < liczbaNadgodzin.length; i++) {
                sumaNadgodzin += liczbaNadgodzin[i];
            }
        }
            return pensja+(stawkaNadgodzin*sumaNadgodzin);

    }

    @Override
    public String toString() {
        return "Pracownik " + nazwisko + " " + pesel +
                " [stanowisko=" + stanowisko + ", pensja="
                + pensja + ", stawka=" + stawkaNadgodzin + "]";
    }
    public void wyswietlLiczbeNadgodzin(){
        if(liczbaNadgodzin!=null){
            for (int i=0;i<liczbaNadgodzin.length;i++) {
                System.out.println("Dla dnia tygodnia nr." + i +" liczba nadgodzin wynosi:" + liczbaNadgodzin[i]);
            }

        }
        else{System.out.println("Brak nadgodzin");}

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pracownik pracownik = (Pracownik) o;

        return stanowisko.equals(pracownik.stanowisko);
    }

    public boolean jestBibliotekarzem(){
        return stanowisko.equals("Bibliotekarz");
    }
}