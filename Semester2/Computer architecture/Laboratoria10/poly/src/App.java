import java.util.Scanner;

public class App {

    public static double eval_poly(int degree, float[] coefs, int x){

        double y = 0;
        while(degree >= 0){

            y = coefs[degree] + y*x;
            degree--;
                System.out.println("Wartosc wielomianu jest rowna: "+eval_poly(degree, coefs, x));
            
            }
            if(option == 2) break;

        }
    }
}
