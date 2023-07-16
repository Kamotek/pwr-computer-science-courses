import java.util.Scanner; // import biblioteki do obslugi inputów użytkownika

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //wprowdzenie wartości
        System.out.println("Wypisz kolejno liczbę całkowitą n i liczbę całkowitą m");
        int n = input.nextInt();
        int m = input.nextInt();
        int suma = 0;

        for(int i = n; i<m;i++){
            if(i%2!=0){
             suma = suma + i;
            }
        }
        System.out.println("Suma liczb nieparzystych w przedziale od n do m jest rowna: " + suma);

    }
}