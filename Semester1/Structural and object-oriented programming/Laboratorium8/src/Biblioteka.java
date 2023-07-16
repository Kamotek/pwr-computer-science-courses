public class Biblioteka {
    static protected int n=20;
    static protected Osoba[] tablicaOsob = new Osoba[n];

    public static int iluJestBibliotekarzy(Osoba[] tablicaOsob){
        int liczbaBibliotekarzy = 0;
        for (Osoba o: tablicaOsob) {
            if(o!=null) {
                if (o instanceof Pracownik) {
                    if(((Pracownik) o).jestBibliotekarzem())
                        liczbaBibliotekarzy++;
                }
            }
        }
        return liczbaBibliotekarzy;
    }

    public static void wyswietlanieWszystkichElementowTablicy(Osoba[] tablicaOsob){
        for (Osoba o: tablicaOsob) {
            if(o!=null)
            System.out.println(o);
        }
    }
    public static void wyswietlaniePracownikowINadgodzin(Osoba[] tablicaOsob){
        for (Osoba o: tablicaOsob) {
            if(o!=null) {
                if (o instanceof Pracownik) {
                    System.out.println(o);
                    ((Pracownik) o).wyswietlLiczbeNadgodzin();
                }
            }
        }
    }
    public static void wyswietlanieCzytelnikowIWypozyczen(Osoba[] tablicaOsob){
        for (Osoba o: tablicaOsob){
            if(o!=null){
                if(o instanceof Czytelnik){
                    System.out.println(o);
                    ((Czytelnik) o).wyswietlWypozyczenia();
                }
            }
        }
    }
    public static Osoba szukanieBogacza(Osoba[] tablicaOsob){
        Osoba bogacz = tablicaOsob[0];
        for(int i=0;i< tablicaOsob.length;i++){
            if(tablicaOsob[i]!=null){
              if(tablicaOsob[i] instanceof Pracownik) {
                  if (((Pracownik )tablicaOsob[i]).Oblicz() > bogacz.Oblicz()){
                      bogacz = tablicaOsob[i];
              }
              }
            }
        }
        return bogacz;
    }
    public static void main(String[] args) {

        tablicaOsob[0] = new Czytelnik();
        tablicaOsob[1] = new Czytelnik("Kowalski", "18276987", 5);
        tablicaOsob[2] = new Czytelnik("Zygmuntowski", "65784320", 6);
        tablicaOsob[3] = new Czytelnik("Szczesny", "12321537",5);
        tablicaOsob[4] = new Czytelnik("Hulak","0222121212",9);

        tablicaOsob[5] = new Pracownik();
        tablicaOsob[6] = new Pracownik("Maj","2345678765",100,80,"Bibliotekarz");
        tablicaOsob[7] = new Pracownik("Nazwiskowa","777666555444",3200,100,"Konserwator");

        System.out.println("-------------------------");
        wyswietlanieWszystkichElementowTablicy(tablicaOsob);
        System.out.println("-------------------------");
        wyswietlaniePracownikowINadgodzin(tablicaOsob);
        System.out.println("-------------------------");
        wyswietlanieCzytelnikowIWypozyczen(tablicaOsob);
        System.out.println("-------------------------");
        System.out.println(szukanieBogacza(tablicaOsob));
        System.out.println("-------------------------");
        System.out.println(iluJestBibliotekarzy(tablicaOsob));
    }
}