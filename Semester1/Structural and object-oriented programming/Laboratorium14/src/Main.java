import java.util.Scanner;

public class Main {
    public static void przewijanieMenu(){
        for(int i=0;i<50;i++)
            System.out.println();
    }

    public static void dodajAuto(Samochody auta){
        Scanner input = new Scanner(System.in);
        System.out.println("Wprowadź markę");
        String marka = input.nextLine();
        System.out.println("Wprowadź liczbę przejechanych kilometrów");
        int lk = input.nextInt();
        System.out.println("Wprowadź numer nadwozia");
        long nrNadwozia = input.nextLong();
        System.out.println("Wprowadź model auta");
        input.nextLine();
        String model = input.nextLine();
        System.out.println("Wprowadź zuzycie paliwa na 100km");
        double zuzycie = input.nextDouble();
        System.out.println("Wprowadź wartość true dla diesela, false dla benzyny");
        boolean diesel = input.nextBoolean();
        auta.dodajAuto(marka, lk, nrNadwozia, diesel, model, zuzycie);

    }

    public static void wyswietlanieListy(Samochody auta){
        for (Auto k: auta.getAuta()
             ) {
            System.out.println(k);
        }
    }

    public static void dodajCiezarowke(Samochody auta){
        Scanner input = new Scanner(System.in);
        System.out.println("Wprowadź markę");
        String marka = input.nextLine();
        System.out.println("Wprowadź liczbę przejechanych kilometrów");
        int lk = input.nextInt();
        System.out.println("Wprowadź numer nadwozia");
        long nrNadwozia = input.nextLong();
        System.out.println("Wprowadź model auta");
        input.nextLine();
        String model = input.nextLine();
        System.out.println("Wprowadź zuzycie paliwa na 100km");
        double zuzycie = input.nextDouble();
        System.out.println("Wprowadź wartość true dla kratki, false dla jej braku");
        boolean kratka = input.nextBoolean();
        System.out.println("Wprowadź wartość true dla diesela, false dla benzyny");
        boolean diesel = input.nextBoolean();
        auta.dodajCiezarowke(marka, lk, nrNadwozia, diesel, model, zuzycie, kratka);

    }
        public static void sortByMarka(Samochody auta){
        auta.sortByMarka();
        }

        public static void sortByKilometry(Samochody auta){
        auta.sortByKilometry();
        }

        public static void sortByMarkaIKilometry(Samochody auta){
        auta.sortByMarkaThenKilometry();
        }

        public static void najwiekszeZuzycie(Samochody auta){
        Auto auto = auta.getAuta().get(0);
        int index=0;
            for (Auto k: auta.getAuta()
                 ) {
                if(auto.getZuzycie()<k.getZuzycie())
                    auto = k;
                    index = auta.getAuta().indexOf(k);
            }

            System.out.println("Auto o najwiekszym zuzyciu to: " + auto + "a jego pozycja to: " + index);
        }

    public static void main(String[] args) {
        Samochody auta = new Samochody();
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Wybierz numer operacji, którą chcesz wykonać: ");
            System.out.println("--------------------------------------------------");
            System.out.println("1. Dodaj auto");
            System.out.println("2. Dodaj ciezarowke");
            System.out.println("3. Wyswietl wszystkie auta");
            System.out.println("4. Sortuj po marce");
            System.out.println("5. Sortuj po kilometrach");
            System.out.println("6. Sortuj po marce i kilometrach");
            System.out.println("7. Wyświetl auto o największym zuzyciu paliwa");
            System.out.println("--------------------------------------------------");
            System.out.println("Wybierz opcje:");
            int numerOpcji = input.nextInt();
            if(numerOpcji > 0 && numerOpcji < 8){
                switch(numerOpcji) {
                    case 1: {
                        przewijanieMenu();
                        dodajAuto(auta);
                        break;
                    }
                    case 2: {
                        przewijanieMenu();
                        dodajCiezarowke(auta);
                        break;
                    }
                    case 3: {
                        przewijanieMenu();
                        wyswietlanieListy(auta);
                        break;
                    }
                    case 4: {
                        przewijanieMenu();
                        sortByMarka(auta);
                        break;
                    }
                    case 5: {
                        przewijanieMenu();
                        sortByKilometry(auta);
                        break;
                    }
                    case 6: {
                        przewijanieMenu();
                        sortByMarkaIKilometry(auta);
                        break;
                    }
                    case 7:{
                        przewijanieMenu();
                        najwiekszeZuzycie(auta);
                        break;
                    }
                }
            }
        }
    }
}