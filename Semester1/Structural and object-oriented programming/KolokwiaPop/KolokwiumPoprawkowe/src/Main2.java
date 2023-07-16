public class Main2 {
    public static void main(String[] args) {

        int m = 3;
        int n = 3;

        Krytyk krytyk1 = new Krytyk("psed1", 5);
        Krytyk krytyk2 = new Krytyk("psed2", 5);
        Krytyk krytyk3 = new Krytyk("psed3", 5);
        Film film1 = new Film("tytul1", 5);
        Film film2 = new Film("tytul2", 5);
        Film film3 = new Film("tytul3", 5);
        Film film4 = new Film("tytul4", 5);
        Film film5 = new Film("tytul5", 5);
        krytyk1.wystawOcene(film1);
        krytyk1.wystawOcene(film2);
        krytyk1.wystawOcene(film3);




        System.out.println(krytyk1.wystawioneOceny[0]);
        System.out.println(film1.otrzymaneOceny[0]);
        System.out.println(film2.otrzymaneOceny[0]);
        System.out.println(film3.otrzymaneOceny[0]);

        System.out.println("Srednia ocen krytyka1: " + krytyk1.policzOceny());

    }
}