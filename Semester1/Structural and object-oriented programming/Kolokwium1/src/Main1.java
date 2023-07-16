public class Main1 {

    public static double metoda1(double h){
        int n = 5; //dlugosc ciagu
        double wynik = 1;
        double silnia = 2;

        for(double i=0;i<n;i++)
        {
            wynik+=(Math.pow(h,2*i))/(silnia)*(Math.pow(1,i));
            silnia = (silnia+1)*(silnia+2);
        }


        return wynik;
    }

    public static void metoda2(double a, double b, double h){

        for(double i = a;i<=b;i+=h) {

            System.out.printf("%s %.2f %s %.4f \n","Dla argumentu ", i , " Funkcja przyjmmuje wartosc ", metoda1(i));
        }
    }

    public static void main(String[] args) {

        double a = 3; //pierwsza liczba
        double b = 6;// ostatnia liczba
        double h = 2; // krok



        metoda2(a,b,h);

    }
}