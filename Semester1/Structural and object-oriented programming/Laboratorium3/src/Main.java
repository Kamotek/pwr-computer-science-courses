import java.util.Scanner; // import biblioteki do obslugi inputów użytkownika

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj liczby w kolejności a, b, c");

        //wprowdzenie wartości
        int a = input.nextInt();
        int b = input.nextInt();
        int c = input.nextInt();
        int x = a;

        // wyliczenie najwiekszej z 3 liczb
        //int x = Math.max(Math.max(a,b),c);

        if(a>b){
            if(a>c){
                x = a;
            }
            else {
                x = c;
            }
        }
        else{
            if(b>c){
                x = b;
            }
            else{
                x = c;
            }
        }

        System.out.println("X przyjmuje wartość: " + x);

    }
}