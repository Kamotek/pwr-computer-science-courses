import java.util.Scanner;

public class Main1 {

    public static int metoda1(int m, int n){
        int counter = 1;
        int dlugoscMax = 0;
        int pierwszaLiczba = m;
        int aktualnaLiczba = m;

        while(pierwszaLiczba<=n){
            aktualnaLiczba = pierwszaLiczba;
            while(aktualnaLiczba!=1){
                if(aktualnaLiczba%2==0){
                    System.out.print(aktualnaLiczba + ", ");
                    aktualnaLiczba = aktualnaLiczba/2;
                    counter++;
                }
                else {
                    System.out.print(aktualnaLiczba + ", ");
                    aktualnaLiczba = ((aktualnaLiczba*3)+1);
                    counter++;
                }
                if(aktualnaLiczba == 1){
                    System.out.println(1);
                }
            }
            if(counter>dlugoscMax){
                dlugoscMax = counter;
                counter = 0;
            }
            pierwszaLiczba++;
        }
        return dlugoscMax;
    }
    public static void main(String[] args) {



        Scanner input = new Scanner(System.in);
        System.out.println("Podaj m: ");
        int m = input.nextInt();
        System.out.println("Podaj n wieksze od m: ");
        int n;
        do {
            n = input.nextInt();
        }while(n<m);


        System.out.println("Dlugosc najdluzszego ciagu wynosi: " + metoda1(m,n));
    }
}