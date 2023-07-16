import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {

    public static void zapisywaniePliku(){
        try {
            File file = new File("lista.txt");
            InputStream is = new FileInputStream(file);
            FileWriter writer = new FileWriter(file);

            BufferedWriter bw = new BufferedWriter(writer);
            Scanner input = new Scanner(System.in);
            String linia = "";
            bw.write("Wykaz towarow nr  01/2023");
            bw.newLine();
            bw.write("Nazwa_towaru  ilosc    cena");
            bw.newLine();
            for (int i = 0; i < 2; i++) {
                linia = input.nextLine();
                bw.write(linia);
                linia = input.nextLine();
                bw.write("  " + linia);
                linia = input.nextLine();
                bw.write("  " + linia);
                linia = input.nextLine();
                bw.write("  " + linia);

                bw.newLine();

            }
            bw.close();
        }catch(IOException e){System.out.println(e);}
    }

    public static void odczytPliku(){
        try{
        FileReader fr = new FileReader("lista.txt");
        BufferedReader br = new BufferedReader(fr);

        ArrayList<String> tablica = new ArrayList<>();

        String bufor = "";
        while(bufor !=null){
                tablica.add(bufor);
                bufor = br.readLine();
        }



            br.close();

            File file = new File("nowaLista.txt");
            FileWriter writer = new FileWriter(file);

            BufferedWriter bw = new BufferedWriter(writer);

            bw.write("Wykaz towarow dostepnych");
            bw.newLine();
            bw.write("Lp.   Nazwa   Ilosc   Cena    Wartosc");
            bw.newLine();
            int i = 0;

            ArrayList<String[]> tabliceSlow = new ArrayList<>();
            for (String s: tablica
            ) {
                if(i>3){

                    tabliceSlow.add(s.split("   "));
                    bw.write(s);
                    bw.newLine();

                }

                for (String[] p: tabliceSlow
                     ) {
                    for (String w: p
                         ) {
                        System.out.println(w);
                    }

                }

                i++;
            }


            bw.close();

        }catch(IOException e){System.out.println(e);}
    }

    public static void main(String[] args) {
        zapisywaniePliku();
        odczytPliku();
    }
}