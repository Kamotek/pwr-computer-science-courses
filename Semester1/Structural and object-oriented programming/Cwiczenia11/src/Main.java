import java.io.*;

public class Main {

   public static int division(int a, int b){
        int wynik=0;
        try {
             wynik = a/b;
        }
        catch(ArithmeticException ex){
            Testowanie.dzieleniePrzezZero();
            wynik = 0;
        }
        finally{
            return wynik;
        }
    }


    public static void stworzPlik(){
       int i = 0;
        String nazwa = "Plik";
       do{
            nazwa = "Plik" + String.valueOf(i);
            i++;
       }while((czyPlikIstnieje(nazwa)));

       try{
       File myFile = new File(nazwa);
        if (myFile.createNewFile()) {
            System.out.println("Stworzono plik o nazwie: " + myFile.getName());
        } else {
            System.out.println("Plik już istnieje!");
        }
       }catch(IOException wyjatek){
           Testowanie.errorPliku();
       }

    }

    public void zapisywanieDoPliku(String nazwa,int a, int b){
       try{
           FileWriter pisarz = new FileWriter(nazwa);
           pisarz.write(division(a,b));
           pisarz.close();
       }catch(IOException wyjatek){
           Testowanie.errorPliku();
       }
    }
    public static void stream(){
       try {
           FileInputStream strumyk = new FileInputStream("pliczek.txt");
       }
       catch(IOException e){
           Testowanie.errorPliku();
       }
    }
    public static boolean czyPlikIstnieje(String nazwa){
       File Plik = new File(nazwa);
        return Plik.exists();
    }
    public static void main(String[] args) {

        System.out.println(division(10,0));
        stworzPlik();
    }
}