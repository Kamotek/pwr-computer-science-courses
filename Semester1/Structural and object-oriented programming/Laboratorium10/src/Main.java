import Atak.*;
import Bohaterzy.*;
import Ruch.*;
import Skok.*;

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

    public static void display(Bohater bohater){
        System.out.println("----"+bohater.name()+"----");
        System.out.println(bohater);
    }
    public static void main(String[] args) {
        //while(true){
            System.out.println("Wybierz swojego bohatera: ");
            System.out.println("==========================");
            System.out.println("1. Bohater Zygmunt");
            System.out.println("2. Bohater Władek");
            System.out.println("3. Bohater Czarek");
            System.out.println("==========================");
            System.out.println("Wprowadź numer opcji: ");
            int nrBohatera = input.nextInt();
            Bohater bohater = new Zygmunt();
            switch(nrBohatera){
                case 1:
                    bohater = new Zygmunt(wyborAtaku(),wyborSkoku(),wyborChodu());
                    break;
                case 2:
                    bohater = new Wladek(wyborAtaku(),wyborSkoku(),wyborChodu());
                    break;
                case 3:
                    bohater = new Czarek(wyborAtaku(),wyborSkoku(),wyborChodu());
            }

            display(bohater);

        }
}