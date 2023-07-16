import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int x = 6;
        int y = 3;

        int n = 20;
        double odleglosc = 0;

        Random random = new Random();
        int ruch=0;
        int kroki=0;

        while(odleglosc < n){
            ruch = random.nextInt(4);
            if(ruch==0)
                x++;
            if(ruch==1)
                x--;
            if(ruch==2)
                y++;
            if(ruch==3)
                y--;

            odleglosc=Math.sqrt((x*x)+(y*y));

            kroki++;
        }
        System.out.println("Odleglosc wynosi: " + odleglosc + " w " + kroki + " krokach");
    }
}