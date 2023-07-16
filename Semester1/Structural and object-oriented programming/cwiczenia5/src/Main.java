public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

       // Pokoj[] tablica = new Pokoj[5];




        Osoba[] ludzie = new Osoba[5];

        ludzie[0] = new Osoba("Bogumił", "Nowobogacki");
        ludzie[1] = new Osoba("Zygfryd", "Biedny");
        ludzie[2] = new Osoba("Alfred", "Sołtys");


        Pokoj[] pokoje = new Pokoj[3];

        pokoje[0] = new Pokoj();
        pokoje[1] = new Pokoj();
        pokoje[2] = new Pokoj(ludzie[0].imie, ludzie[0].nazwisko);

        System.out.println(pokoje[2].czyJestWynajety());

        for(int i = 0;i<3;i++)
        {
            if(pokoje[i].czyJestWynajety())
            {
                System.out.println("Pokoj numer " + pokoje[i].numerPokoju + " jest wynajety");
            }
            else
            {
                System.out.println("Pokoj numer " + pokoje[i].numerPokoju + " jest pusty");
            }
        }
    }
}
