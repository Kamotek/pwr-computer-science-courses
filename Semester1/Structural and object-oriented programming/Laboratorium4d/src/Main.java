import java.util.Scanner; // import biblioteki do obslugi inputów użytkownika

public class Main {
    static double licznik(double liczbaCiagu){
        double liczba = 2;
        double suma = 0;
        for(int  i = 0; i<liczbaCiagu;i++){
            suma = suma + liczba;
            System.out.println("Element ciagu licznika o indeksie " + i + " jest rowny " + liczba);
            liczba = liczba + 3;

        }

        return suma;

    }

    static double mianownik(double liczbaCiagu){
        int liczbaPomocnicza = 3;
        double liczba = 3;
        double wynik = 1;

        for(int i = 0; i<liczbaCiagu;i++){
            liczba = liczbaPomocnicza*(Math.pow((-1),(i)));
            liczbaPomocnicza = liczbaPomocnicza + 4;
            wynik = wynik*liczba;
            System.out.println("Element ciagu mianownika o indeksie " + i + " jest rowny " + liczba); 

        }
        return wynik;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //wprowdzenie wartości
        System.out.println("Wypisz liczbę całkowitą n");
        double n = input.nextInt();

        double licznik = licznik(n);
        double mianownik = mianownik(n);

        System.out.println("Licznik jest rowny: " + licznik);
        System.out.println("Mianownik jest rowny: " + mianownik);

        System.out.println("Cale wyrażenie jest rowne liczbie: " + (licznik/mianownik)) ;



    }
}