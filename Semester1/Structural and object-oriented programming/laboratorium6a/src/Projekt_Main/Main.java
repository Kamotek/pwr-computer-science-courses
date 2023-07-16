package Projekt_Main;

import Florysta.Florysta;
import Bukiet.*;
import java.util.Random;


public class Main {

   // static Bukiet[] kwiaciarnia = new Bukiet[6];
    static void metodaWyswietlajacaKwiaciarnie(Bukiet[] kwiaciarnia){
        for(int i=0;i< kwiaciarnia.length;i++) {
            System.out.print("Bukiet o indeksie=" + i + " ");
            kwiaciarnia[i].getStan();
        }
        System.out.println("_____");
    }
    public static void main(String[] args) {

        Random random = new Random();

        //Florysta florysta = new Florysta();

        int n = 3;

        Kwiat[] kwiaty = new Kwiat[n];

        for(int i=0;i< kwiaty.length;i++)
            kwiaty[i] = new Kwiat(random.nextInt(6)+1);

        Bukiet[] kwiaciarnia = new Bukiet[n];

        for(int i=0;i< kwiaciarnia.length;i++)
            kwiaciarnia[i] = new Bukiet(kwiaty[i], false);

        metodaWyswietlajacaKwiaciarnie(kwiaciarnia);

        kwiaciarnia[0].jestwKwiaciarni();

        metodaWyswietlajacaKwiaciarnie(kwiaciarnia);

        kwiaciarnia[0].brakwKwiaciarni();

        metodaWyswietlajacaKwiaciarnie(kwiaciarnia);

        /*florysta.sprzedajBukiet(kwiaciarnia[0]);

        metodaWyswietlajacaKwiaciarnie(kwiaciarnia);

        florysta.tworzBukiet(kwiaciarnia[0]);

        metodaWyswietlajacaKwiaciarnie(kwiaciarnia);*/
    }
}