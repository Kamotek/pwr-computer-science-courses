public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Czas zegarek = new Czas(5,67,156);

        System.out.println("Godzina: " + zegarek.getGodzina());
        System.out.println("Minuta: " + zegarek.getMinuta());
        System.out.println("Sekunda: " + zegarek.getSekunda());

        zegarek.przesunMinuty(59);

        System.out.println("Godzina: " + zegarek.getGodzina());
        System.out.println("Minuta: " + zegarek.getMinuta());
        System.out.println("Sekunda: " + zegarek.getSekunda());

        Czas zegarek2 = new Czas(1,1,1);

        System.out.println(zegarek.compareTo(zegarek2));
    }
}