package Prog;
import java.util.Scanner;

import Wielomian.Wielomian;

public class Prog{
    public static double main(){
    Scanner input = new Scanner(System.in);

    System.out.println("Podaj wartość współczynnika x");
    double x = input.nextDouble();
    System.out.println("Podaj wartość liczbową n");
    double n = input.nextDouble();

    double wynik = Wielomian.Hermite(x, n);

    return wynik;

}
}