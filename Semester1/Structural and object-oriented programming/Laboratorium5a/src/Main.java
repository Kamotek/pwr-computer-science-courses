public class Main {
    public static void main(String[] args) {

        Zegar obj = new Zegar(3,4,5);

        obj.Wypisz();
        obj.Przesun(-1,-3,32);
        obj.Wypisz();
        obj.Przesun(24,43,58);
        obj.Wypisz();
        obj.Przesun(10,9,0);
        obj.Wypisz();

    }
}