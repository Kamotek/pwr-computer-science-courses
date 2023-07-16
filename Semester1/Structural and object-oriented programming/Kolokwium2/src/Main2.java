import java.util.Random;

public class Main2 {
    public static void main(String[] args) {

        Random random = new Random();

        Klasa1 klasa = new Klasa1(5,random.nextInt(10));

        int z = 10; // zakres liczb
        klasa.metoda1(z);

        klasa.metoda2();

    }
}