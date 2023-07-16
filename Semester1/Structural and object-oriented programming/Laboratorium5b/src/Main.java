import java.util.Random;
import java.util.Scanner;

public class Main {

    public static int x = wielkoscTablicy();
    public static int[] tablica = new int[x];

    static int wielkoscTablicy(){
        Scanner input = new Scanner(System.in);

        System.out.println("Podaj wielkość tablicy");
         int n = input.nextInt();

        return n;
    }

    static void generatorLiczb(){
        Random rand = new Random();
        for(int i=0;i<x;i++)
            tablica[i] = rand.nextInt(100);
    }
    static void wypisywanieLiczb(int n){
        for(int i=0;i<n;i++)
            System.out.print(tablica[i] + " ");
        System.out.println("");
    }

    static int wyszukiwanieWartosciMinimalnej(){
        int min = tablica[0];

        for(int i=0;i<x;i++){
            if(min>tablica[i])
            {
                min = tablica[i];
            }
        }
        return min;
    }
    static int obliczanieIloczynuLiczbNieparzystych(){
        int iloczyn = 1;
        for(int i=0;i<x;i++){
            if(tablica[i]%2!=0) {
                iloczyn *= tablica[i];
            }
        }
        return iloczyn;
    }
    static int obliczanieIlosciLiczbNieparzystych(){
        int licznik = 0;
        for(int i=0;i<x;i++){
            if(tablica[i]%2!=0) {
                licznik++;
            }
        }
        return licznik;
    }
    public static void main(String[] args) {

        System.out.println("Generator pracuje...");
        generatorLiczb();

        System.out.print("Wszystkie liczby wygenerowane w tablicy: ");
        wypisywanieLiczb(x);

        System.out.print("Najmniejsza liczba wygenerowana w tablicy: ");
        System.out.println(wyszukiwanieWartosciMinimalnej());

        System.out.print("Iloczyn wygenerowanych liczb nieparzystych jest rowny: ");
        System.out.println(obliczanieIloczynuLiczbNieparzystych());

        System.out.print("Ilość liczb nieparzystych jest równa: ");
        System.out.println(obliczanieIlosciLiczbNieparzystych());
    }
}