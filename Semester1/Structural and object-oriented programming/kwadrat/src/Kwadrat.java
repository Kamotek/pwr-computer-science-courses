/*
Znaki konwersji
%d - dla liczb calkowitych
%s - dla liczb
%t
%n
printf()

%nd np %5d - bedzie 5 spacji
%n.mf np %8.2f

Do ogarniecia!
 */

public class Kwadrat {
   private static void Jeden(){
        int bokA = 8;

        int wynikPole = bokA * bokA;
        int wynikObwod = 4 * bokA;
        double wynikPrzekatna = (bokA * (Math.sqrt(2)));

        System.out.println("Obwód tego kwadratu wynosi: " + wynikObwod);
        System.out.println("Pole tego kwadratu wynosi: " + wynikPole);
        System.out.println("Przekątna tego kwadratu wynosi: " + wynikPrzekatna);

    }
    private static void Dwa(){

        int bokProstokataA = 4;
        int bokProstokataB = 5;

        int poleProstokata = bokProstokataA*bokProstokataB;
        int obwodProstokata = 2*bokProstokataA + 2*bokProstokataB;
        double przekatnaProstokata = Math.sqrt(Math.pow(bokProstokataA, 2) + Math.pow(bokProstokataB, 2));

        System.out.println("Pole tego prostokata jest rowne: " + poleProstokata);
        System.out.println("Obwod tego prostokata jest rowne: " + obwodProstokata);
        System.out.println("Przekatna tego prostokata jest rowne: " + przekatnaProstokata);
    }
    private static void Trzy(){
       int a = 4;
       int b = 10;

        System.out.printf("%-2s %8d %n", "a  = " ,   a , "\n");
        System.out.printf("%-2s %9d %n", "b  = " ,   b , "\n");
        System.out.printf("- - - - - - - - - - - - - - " + "\n");
        System.out.printf("%-2s %6d %n", "Suma  = " ,   (a+b) , "\n");


    }
    private static void Cztery(){
       /* Oblicz a,b
       if a>=b  2a+|b| - 1
       if a<b  sina - b^2
        */

        int a = 4, b = 5;

        if(a>=b){
            double wynik = (2*a + Math.abs(b) - 1);
            System.out.println("A jest większe lub równe B a wynik to: " + wynik);
        }
        else{
            double wynik = Math.sin(a) - Math.pow(b,2);
            System.out.println("A jest mniejsze od B a wynik to: " + wynik);

        }
    }
    private static void Piec(){
       /*
       Dane a b c
       Oblicz pole

       | b - c | < a < b + c to itsnieje
       p = (a + b + c)/2
       S = sqrt(p(p-a)(p-b)(p-c))
        */
        int a = 4, b = 5, c = 6;

        if((a>(Math.abs(b-c))) && (a < (b+c)))
        {
            double p = (a+b+c)/2;

            double wynik = Math.sqrt(p*(p-a)*(p-b)*(p-c));

            System.out.println("Ten trojkat istnieje i jego pole wynosi: " + wynik);

        }
        else
        {
            System.out.println("Ten trojkat nie istnieje");
        }
    }
    public static void main(String[] args) {


        /*Jeden(); // liczy kwadrat
        System.out.println("--------------------------");
        Dwa();*/ //liczy prostokat
        //Trzy(); // liczy sume a i b
        //Cztery();
        Piec();




    }
}