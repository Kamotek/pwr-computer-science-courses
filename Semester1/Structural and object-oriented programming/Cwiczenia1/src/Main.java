public class Main {
    /*public static void cyfry(int a){
        int maksymalna = 0;
        int minimalna = 9;
        while(a > 0){
            System.out.println(a%10);
                    if(maksymalna < a%10){
                        maksymalna = a%10;
                    }
                    if(minimalna > a%10){
                        minimalna = a%10;
                    }
            a = a/10;
        }
        System.out.println(minimalna);
        System.out.println(maksymalna);
    }
*/
    public static double cyfry(double a){
       double wynik = 0;
       double i = 0;
       while(a!=0){
           wynik +=(a%10)*Math.pow(2,i);
           a = Math.floor(a/10);
           i++;
       }
       System.out.println(wynik);
       return wynik;
    }
    public static void odwrocenie(int a){
        while(a!=0){
            System.out.print(a%10);
            a = a/10;
        }

    }

    public static void main(String[] args) {
        //System.out.println("Hello world!");

        // dostaje w parametrze liczbe calkowita i oblicza minimalna i maksymalna cyfre
        // e^x = 1 + x/1 + x^2/!2 + x^3/3!
        // zamienic liczbe binarna na dziesietna
        int a = 11111;
        cyfry(a);

        int b = 123456789;
        odwrocenie(b);
    }
}