import Atak.*;
import Bohaterzy.*;
import Ruch.*;
import Skok.*;

import java.io.*;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);


    static Atak wyborAtaku(){
        System.out.println("Wybierz rodzaj ataku: ");
        System.out.println("==========================");
        System.out.println("1. Atak fizyczny");
        System.out.println("2. Atak dystansowy");
        System.out.println("3. Atak magiczny");
        System.out.println("==========================");
        System.out.println("Wprowadź numer opcji: ");
        int nrAtaku = input.nextInt();

        Atak nowyAtak = new AtakPodstawowy();

        switch(nrAtaku){
            case 1:{
                nowyAtak = new FizycznyAtak();
                break;
            }
            case 2:{
                nowyAtak = new AtakDystansowy();
                break;
            }
            case 3:{
                nowyAtak = new MagicznyAtak();
                break;
            }
        }
        return nowyAtak;
    }

    static Skok wyborSkoku(){
        System.out.println("Wybierz rodzaj skoku: ");
        System.out.println("==========================");
        System.out.println("1. Krotki Skok");
        System.out.println("2. Dlugi Skok");
        System.out.println("==========================");
        System.out.println("Wprowadź numer opcji: ");
        int nrSkoku = input.nextInt();

        Skok nowySkok = new PodstawowySkok();

        switch(nrSkoku){
            case 1: {
                nowySkok = new KrotkiSkok();
                break;
            }
            case 2: {
                nowySkok = new DlugiSkok();
                break;
            }
        }
    return nowySkok;
    }

    static Ruch wyborChodu(){
        System.out.println("Wybierz rodzaj chodu: ");
        System.out.println("==========================");
        System.out.println("1. Wolny chod");
        System.out.println("2. Szybki chod");
        System.out.println("==========================");
        System.out.println("Wprowadź numer opcji: ");
        int nrChodu = input.nextInt();

        Ruch nowyRuch = new PodstawowyChod();

        switch(nrChodu){
            case 1: {
                nowyRuch = new Chod();
                break;
            }
            case 2: {
                nowyRuch = new Bieg();
                break;
            }
        }
        return nowyRuch;
    }

    public static void display(Bohater[] bohaterzy){
        for(int i=0;i< bohaterzy.length;i++) {
            System.out.println("-----" + bohaterzy[i].name() + "-----");
            System.out.println(bohaterzy[i]);
        }
    }

    public static void zapisStrumienia(Bohater[] bohaterzy){
        try {
            FileOutputStream fwyjscie = new FileOutputStream("plik.txt");
            ObjectOutputStream owyjscie = new ObjectOutputStream(fwyjscie);
            for(int i=0;i< bohaterzy.length;i++) {
                owyjscie.writeObject(bohaterzy[i]);
                owyjscie.flush();
            }
            owyjscie.close();
            System.out.println("Hurra! Udalo sie!");
        }catch(Exception e){System.out.println(e);}
    }

    public static void odczytPliku(){
        try {
            File plik = new File("plik.txt");
          /*  Scanner podczyt = new Scanner(plik);
            System.out.println(podczyt.hasNextLine());
            System.out.println(podczyt.nextLine());
            while (podczyt.hasNextLine()) {
                String data = podczyt.nextLine();
                System.out.println(data);
                System.out.println("Trololo");
            }
            podczyt.close();*/

            BufferedReader br = new BufferedReader(new FileReader(plik));
            String data;
            while((data = br.readLine())!=null){
                System.out.println(data);
                System.out.println(".");
            }
            System.out.println("Koniec odczytu");
        }catch (IOException e){System.out.println(e);}
    }

    public static Bohater[] odczytStrumienia(int dlugoscTablicy){
        Bohater[] bohaterzyNowi = new Bohater[dlugoscTablicy];
        try {
            ObjectInputStream fwejscie = new ObjectInputStream(new FileInputStream("plik.txt"));
            for(int i=0;i<dlugoscTablicy;i++){
                bohaterzyNowi[i] = (Bohater) fwejscie.readObject();
            }
            fwejscie.close();
        }catch(Exception e){System.out.println(e);}

        return bohaterzyNowi;
    }

    public static void main(String[] args) {

            System.out.println("Podaj ilu bohaterów chcesz stworzyć: ");
            int n = input.nextInt();
            Bohater[] bohaterzy = new Bohater[n];
        for(int i=0;i<n;i++){
            System.out.println("Wybierz swojego bohatera: ");
            System.out.println("==========================");
            System.out.println("1. Bohater Zygmunt");
            System.out.println("2. Bohater Władek");
            System.out.println("3. Bohater Czarek");
            System.out.println("==========================");
            System.out.println("Wprowadź numer opcji: ");
            int nrBohatera = input.nextInt();
            switch(nrBohatera){
                case 1:
                    bohaterzy[i] = new Zygmunt(wyborAtaku(),wyborSkoku(),wyborChodu());
                    break;
                case 2:
                    bohaterzy[i] = new Wladek(wyborAtaku(),wyborSkoku(),wyborChodu());
                    break;
                case 3:
                    bohaterzy[i] = new Czarek(wyborAtaku(),wyborSkoku(),wyborChodu());
            }
        }
       // display(bohaterzy);
        zapisStrumienia(bohaterzy);

        odczytPliku();

        display(odczytStrumienia(bohaterzy.length));
    }
}