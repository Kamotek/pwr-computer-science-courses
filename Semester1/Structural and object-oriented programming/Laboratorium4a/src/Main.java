import java.util.Scanner;

public class Main {

    public static double kalkulator(double pierwszaLiczba, double drugaLiczba, int znakDzialania){
        double wynik = 0;
        switch(znakDzialania){
            case 1:
                wynik =  pierwszaLiczba - drugaLiczba;
                break;
            case 2:
                wynik = pierwszaLiczba + drugaLiczba;
                break;
            case 3:
                wynik = pierwszaLiczba*drugaLiczba;
                break;
            case 4:
                if(drugaLiczba==0)
                {System.out.println("Dzielenie przez zero");}
                else {
                    wynik = pierwszaLiczba / drugaLiczba;
                }
        }
        return wynik;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int zmiennaPomocniczaDzialania = 0;

        System.out.println("Wprowadź pierwszą liczbę: ");
        double pierwszaLiczba = input.nextDouble();
        System.out.println("Wprowadź drugą liczbę: ");
        double drugaLiczba = input.nextDouble();
        System.out.println("Wprowadź jaki typ działania chcesz wykonać: ");
        char znakDzialania = input.next().charAt(0);

        if(znakDzialania == '-')
            zmiennaPomocniczaDzialania = 1;
        if(znakDzialania == '+')
            zmiennaPomocniczaDzialania = 2;
        if(znakDzialania == '*')
            zmiennaPomocniczaDzialania = 3;
        if(znakDzialania == '/')
            zmiennaPomocniczaDzialania = 4;



        System.out.println("Wynikiem działania jest: " + kalkulator(pierwszaLiczba, drugaLiczba, zmiennaPomocniczaDzialania));

        //czterodzialaniowy kalkulator
        // 3 inputy w argumencie funkcji
        //metoda double i switch


    }
}