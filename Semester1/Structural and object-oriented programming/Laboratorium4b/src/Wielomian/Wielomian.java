package Wielomian;

public class Wielomian{
    /*static double skladnikWielomianu(){
   // H(n) = 2x * H(n-1)  - 2(n-1) * H(n-2)

        double wynik =

        return wynik;
    }*/

    public static double Hermite(double x, double i){
        double wynik = 0;
        double temp = 2*x;
        double temp2 = 1;
            if (i == 0)
                wynik = 1;
            if (i == 1)
                wynik = 2 * x;
            if (i > 1) {
                for (int j = 0; j<=i;j++)
                wynik = 2*x*temp-2*(j-2) * temp2;
                temp2 = temp;
                temp = wynik;

            }

        return wynik;

        /*
   H(0) = 1
   H(1) = 2x
   H(n) = 2x * H(n-1)  - 2(n-1) * H(n-2)



                 1x^0
                 2x^1
                 4x^2 - 2x^0
                 8x^3 - 12x^1
                 16x^4 - 48x^2 + 12x^0
                 32x^5 - 160x^3 + 120x^1
                 64x^6 - 480x^4 + 720x^2 - 120x^0
                 128x^7 - 1344x^5 + 3360x^3 - 1680x^1
                 256x^8 - 3584x^6 + 13440x^4 - 13440x^2 + 1680x^0 */

    }
}