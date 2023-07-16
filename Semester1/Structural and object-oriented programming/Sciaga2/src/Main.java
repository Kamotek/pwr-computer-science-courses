import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void odczytPliku(){
        try{

            Scanner input = new Scanner(System.in);
            String nazwaListy = input.nextLine();
            FileReader fr = new FileReader(nazwaListy);
            BufferedReader br = new BufferedReader(fr);

            ArrayList<String> tablica = new ArrayList<>();
            String buffor = "";
            while(buffor!=null) {
                buffor = br.readLine();
                if(buffor!=null)
                    tablica.add(buffor);

            }

            ArrayList<String[]> podzielone = new ArrayList<>();
            for (String t: tablica
                 ) {
              podzielone.add(t.split(" "));
            }
            int k = 0;
            double sredniaOcen = 0;
            double[] srednieOcen = new double[podzielone.size()-2];
            for (String[] slowaWlinii: podzielone)
            {if(k>1) {
                for (int i = 0; i < slowaWlinii.length; i++) {
                    if (slowaWlinii.length == 7 && i>3)
                       sredniaOcen += Double.parseDouble(slowaWlinii[i]);
                }
                srednieOcen[k-2] = sredniaOcen/3;
                sredniaOcen = 0;
                System.out.println();
            }
                k++;
            }

        String outputName = input.nextLine();
        File output = new File(outputName);
        FileWriter writer = new FileWriter(output);
        BufferedWriter bwriter = new BufferedWriter(writer);

        bwriter.write(tablica.get(0));
        bwriter.newLine();
        bwriter.write(tablica.get(1) + " Średnia ocen");
        bwriter.newLine();
        for(int i = 2; i< tablica.size();i++){
            bwriter.write(tablica.get(i) + " " + srednieOcen[i-2]);
            bwriter.newLine();
        }

        bwriter.close();

        }catch(IOException e){System.out.println(e);}
    }

    public static void main(String[] args) {

        odczytPliku();
    }
}