import java.util.Scanner;

public class Main3 {

    public static void odgadywanie(String odgadywanyWyraz){
        Scanner input = new Scanner(System.in);
        char[] odgadnietyWyraz = new char[odgadywanyWyraz.length()];
        int counter = 0;
        int counterCalkowity = 0;
        char aktualnaLitera = ' ';
        while(counter < odgadywanyWyraz.length()) {
            System.out.println("Zgadnij litere o numerze: " + counter);
            aktualnaLitera = input.nextLine().charAt(0);
            if (aktualnaLitera == odgadywanyWyraz.charAt(counter)) {
                odgadnietyWyraz[counter] = aktualnaLitera;
                counter++;
                counterCalkowity++;
            }else{
                counterCalkowity++;
                System.out.println("Probuj dalej!");
            }
            for(int i = 0; i<counter;i++){
                System.out.print(odgadnietyWyraz[i]);
            }
            System.out.println();
        }
        if(counter == odgadywanyWyraz.length()){
            System.out.println("Gratulacje, udalo ci sie za " + counterCalkowity + " razem!");
        }

        }



    public static void main(String[] args) {

        String odgadywanyWyraz = "wyraz";

        odgadywanie(odgadywanyWyraz);
    }
}