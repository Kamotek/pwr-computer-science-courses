import java.util.Random;

public class Pokoj {

        Random random = new Random();
    int numerPokoju;
    String imieWynajemcy;
    String nazwiskoWynajemcy;

    public Pokoj(){
        numerPokoju = random.nextInt(50);
        imieWynajemcy = "";
        nazwiskoWynajemcy = "";
    }

    public Pokoj(String imieWynajemcy, String nazwiskoWynajemcy){
        numerPokoju = random.nextInt(50);
        this. imieWynajemcy = imieWynajemcy;
        this.nazwiskoWynajemcy = nazwiskoWynajemcy;
    }

    public String getImieWynajemcy() {
        return imieWynajemcy;
    }

    public String getNazwiskoWynajemcy() {
        return nazwiskoWynajemcy;
    }

    public boolean czyJestWynajety(){
        return (getImieWynajemcy() != "" && getNazwiskoWynajemcy() != "");
    }
}
