import java.util.Random;

public class Czytelnik extends Osoba{
    protected int n;
    protected Ksiazka[] tablicaWypozyczen;

    public Czytelnik(){
        super();
        this.n = 0;
        this.tablicaWypozyczen = null;
    }
    public Czytelnik(String nazwisko, String pesel, int n){
        super(nazwisko, pesel);
        this.n = n;
        this.tablicaWypozyczen = new Ksiazka[this.n];
        Random random = new Random();
        for(int i =0;i< tablicaWypozyczen.length;i++
             ) {
            tablicaWypozyczen[i] = new Ksiazka(random.nextInt(), Math.round(random.nextDouble()*100), random.nextBoolean());
        }
    }
    public double Oblicz(){
        double sumaKar=0;
        if(tablicaWypozyczen!=null) {
            for (int i = 0; i < tablicaWypozyczen.length; i++) {
                sumaKar += tablicaWypozyczen[i].kara;
            }
        }
        return sumaKar;
    }

    @Override
    public String toString(){
      return "Czytelnik: " + nazwisko + " " + pesel + " [n=" + n + "]";
    }

    public void wyswietlWypozyczenia(){
        if (tablicaWypozyczen != null) {
            for (int i = 0; i < tablicaWypozyczen.length; i++) {
                System.out.println(tablicaWypozyczen[i]);
            }
        }
        else{
            System.out.println("Brak wypożyczeń");
        }

    }
}
