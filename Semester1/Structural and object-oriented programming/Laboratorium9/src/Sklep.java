import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Sklep {
    static Scanner input = new Scanner(System.in);
    static Transakcja[] tablicaTransakcji = new Transakcja[100];

    public static void przewijanieMenu(){
        for(int i=0;i<50;i++)
            System.out.println();
    }
    public static void metoda1(int typKarty){

        System.out.print("Wprowadź kwotę transakcji: ");
        double kwotaTransakcji = input.nextDouble();
        System.out.println("--------------------------------------------------");
        System.out.println("Czy chcesz wprowadzic dane posiadacza karty?");
        System.out.println("--------------------------------------------------");
        System.out.println("1. Tak");
        System.out.println("2. Nie");
        System.out.println("--------------------------------------------------");
        System.out.print("Wybierz opcję: ");
        int numerOpcji = input.nextInt();
        przewijanieMenu();
        if(numerOpcji==1) {
            System.out.print("Wprowdź nazwisko posiadacza karty: ");
            input.nextLine();
            String nazwisko = input.nextLine();
            System.out.print("Wprowadź numer karty: ");
            int numerKarty = input.nextInt();
            if(typKarty==1){tablicaTransakcji[Transakcja.numerTransakcji] = new Transakcja(kwotaTransakcji, new KartaPodstawowa(numerKarty, nazwisko));}
            if(typKarty==2){tablicaTransakcji[Transakcja.numerTransakcji] = new Transakcja(kwotaTransakcji, new KartaStudenta(numerKarty, nazwisko));}
            if(typKarty==3){tablicaTransakcji[Transakcja.numerTransakcji] = new Transakcja(kwotaTransakcji, new KartaSeniora(numerKarty, nazwisko));}
            System.out.println("Transakcja została dodana!");
        }
        else{
            if(typKarty==1){tablicaTransakcji[Transakcja.numerTransakcji] = new Transakcja(kwotaTransakcji, new KartaPodstawowa());}
            if(typKarty==2){tablicaTransakcji[Transakcja.numerTransakcji] = new Transakcja(kwotaTransakcji, new KartaStudenta());}
            if(typKarty==3){tablicaTransakcji[Transakcja.numerTransakcji] = new Transakcja(kwotaTransakcji, new KartaSeniora());}
            System.out.println("Transakcja została dodana!");
        }
    }
    public static void dodajTransakcje(Transakcja[] tablicaTransakcji){
        System.out.println("Wybierz typ transakcji: ");
        System.out.println("--------------------------------------------------");
        System.out.println("1. Transakcja z użyciem Karty Podstawowa");
        System.out.println("2. Transakcja z użyciem Karty Studenta");
        System.out.println("3. Transakcja z użyciem Karty Seniora");
        System.out.println("4. Transakcja pusta");
        System.out.println("5. Wyjdź");
        System.out.println("--------------------------------------------------");
        System.out.print("Wprowadź numer opcji: ");
        int typOpcji = input.nextInt();
        przewijanieMenu();
        switch(typOpcji){
            case 5:{
                break;
            }
            case 4: {
                tablicaTransakcji[Transakcja.numerTransakcji] = new Transakcja();
                System.out.println("Transakcja została dodana!");
                break;
            }
            case 3: {
                metoda1(3);
                break;
            }
            case 2: {
                metoda1(2);
                break;
            }
            case 1:{
                metoda1(1);
                break;
            }
        }

    }

        public static void wyswietlWszystkieTransakcje(Transakcja[] tablicaTransakcji){
            for (Transakcja t: tablicaTransakcji) {
                if(t!=null) {
                    System.out.println(t);
                }
            }
        }
        public static int  wyswietlTransakcjeZUzyciemKartySeniora(Transakcja[] tablicaTransakcji ){
        int licznik=0;
            for (Transakcja t: tablicaTransakcji) {
                if(t!=null && t.karta instanceof KartaSeniora) {
                    licznik++;
                }
            }
            return licznik;
        }
        
        public static Transakcja wyswietlStudentaKtoryZaplacilNajwiecej(Transakcja[] tablicaTransakcji){
            double maxKwota = 0;
            int indeks=0;
            for(int i=0;i<tablicaTransakcji.length;i++) {
                if(tablicaTransakcji[i]!=null && tablicaTransakcji[i].karta instanceof KartaStudenta){
                    if(maxKwota<tablicaTransakcji[i].kwotaDoZaplaty()){
                        maxKwota = tablicaTransakcji[i].kwotaDoZaplaty();
                        indeks = i;
                    }
                }
            }
            return tablicaTransakcji[indeks];
        }

        public static void zapisanieDoPlikuTransakcjiStudentow(Transakcja[] tablicaTransakcji){
            try {
                FileWriter writer = new FileWriter("lista.txt");
                BufferedWriter output = new BufferedWriter(writer);

                for (Transakcja t: tablicaTransakcji) {
                    if(t!=null){
                        if(t.karta instanceof KartaStudenta){
                            output.write(t.toString());
                            output.newLine();
                            }
                    }
                }
                output.close();
                System.out.println("Pomyślnie zapisano plik!");
            }catch (IOException e) {
                System.out.println("error");
                e.printStackTrace();
            }
        }
    public static void main(String[] args) {
        while(true){
            System.out.println("Wybierz numer operacji, którą chcesz wykonać: ");
            System.out.println("--------------------------------------------------");
            System.out.println("1. Dodaj transakcję");
            System.out.println("2. Dodaj kilka transakcji");
            System.out.println("3. Wyświetl wszystkie transakcje");
            System.out.println("4. Wyświetl liczbę transakcji z użyciem Karty Seniora");
            System.out.println("5. Wyświetl dane klienta z Kartą Studenta, który zapłacił najwięcej");
            System.out.println("6. Zapisz do pliktu tekstowego transakcje używające Karty Studenta");
            System.out.println("--------------------------------------------------");
            System.out.print("Wprowadź numer opcji: ");
            int numerOpcji = input.nextInt();
            if(numerOpcji > 0 && numerOpcji < 7){
                switch(numerOpcji) {
                    case 1: {
                        przewijanieMenu();
                        dodajTransakcje(tablicaTransakcji);
                        break;
                    }
                    case 2: {
                        przewijanieMenu();
                        System.out.print("Podaj ile chcesz wprowadzić transakcji: ");
                        int iloscTransakcji = input.nextInt();
                        for(int i=0;i<iloscTransakcji;i++){
                            System.out.println("Transakcja " + i);
                            dodajTransakcje(tablicaTransakcji);
                        }
                        break;
                    }

                    case 3: {
                        przewijanieMenu();
                        wyswietlWszystkieTransakcje(tablicaTransakcji);
                        break;
                    }
                    case 4: {
                        przewijanieMenu();
                       System.out.println("Jest łącznie " +wyswietlTransakcjeZUzyciemKartySeniora(tablicaTransakcji)+" transakcji z użyciem Karty Seniora");
                        break;
                    }
                    case 5: {
                        przewijanieMenu();
                       System.out.println("Transakcja z użyciem Karty Studenta o najwyższej kwocie to: " + wyswietlStudentaKtoryZaplacilNajwiecej(tablicaTransakcji));
                        break;
                    }
                    case 6: {
                        przewijanieMenu();
                        zapisanieDoPlikuTransakcjiStudentow(tablicaTransakcji);
                        break;
                    }
                }
            }
        }
    }
}