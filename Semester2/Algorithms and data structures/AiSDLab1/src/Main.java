import java.util.*;

public class Main {
    public static ArrayList<Karta> arraySet(){
        ArrayList<Karta> talia = new ArrayList<>();
        Random random = new Random();

        int wartosc = random.nextInt(14);
        int kolor = random.nextInt(4);

        while(wartosc!=0) {
            dodawanie(talia, wartosc, kolor);
            wartosc = random.nextInt(14);
            kolor = random.nextInt(4);
        }
        return talia;
    }
    public static void dodawanie(ArrayList<Karta> talia, int wartosc, int kolor){
        int temp = Integer.parseInt(Integer.toString(wartosc) + Integer.toString(kolor));
        for(int i =0; i<talia.size();i++){
            if(Integer.parseInt(Integer.toString(talia.get(i).getWartosc()) + Integer.toString(talia.get(i).getKolor())) < temp) continue;
            if(Integer.parseInt(Integer.toString(talia.get(i).getWartosc()) + Integer.toString(talia.get(i).getKolor())) > temp) {talia.add(i, new Karta(kolor, wartosc)); return;}
        }
        talia.add(new Karta(kolor, wartosc));
    }
    public static void wyswietlElementy(ArrayList<Karta> talia){
        /*for (Karta x: talia
             ) {
            System.out.println(x);
        }*/

        Iterator iterator = talia.iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public static int policzElementy(ArrayList<Karta> talia){
        int i = 0;
        for (Karta x: talia
             ) {
            i++;
        }

        return i;
    }

    public static void kartaOWartosci(int wartosc, ArrayList<Karta> talia){
        for (Karta x: talia
             ) {
            if(x.getWartosc() == wartosc){
                System.out.println(x);
            }
        }
    }


    public static void kartaOKolorze(int kolor, ArrayList<Karta> talia){
        for (Karta x: talia
        ) {
            if(x.getKolor() == kolor){
                System.out.println(x);
            }
        }
    }

  /*  public static void usunPowtorzenia(ArrayList<Karta> talia){
        for(int i = 0;i<3;i++)
            talia.add(i, new Karta(0,1));

        TreeSet<Karta> nowaKolekcja = new TreeSet<Karta>();

        for (Karta x: talia
             ) {
            nowaKolekcja.add(x);
        }
        talia.removeAll(talia);
        for(Karta x : nowaKolekcja){
            dodawanie(talia, x.getWartosc(), x.getKolor());
        }

    }
*/
    public static void usunPowtorzenia(ArrayList<Karta> talia){

        ArrayList<Karta> nowyArray = new ArrayList<>();

        for(int i = 0;i<3;i++)
            talia.add(i, new Karta(0,1));

        Karta kartaTemp = talia.get(0);
        Iterator<Karta> iterator = talia.iterator();

        while(iterator.hasNext()){
           Karta kartaTemp2 = iterator.next();
           if(!kartaTemp.equals(kartaTemp2))
               nowyArray.add(kartaTemp2);
           kartaTemp = kartaTemp2;
        }

        talia.removeAll(talia);
        talia.add(0, new Karta(0,1));

        for (Karta x: nowyArray
             ) {
            talia.add(x);
        }


    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int wartoscKolor = 0;
        ArrayList<Karta> talia = null;
        while(true){
            System.out.println("===Menu===");
            System.out.println("1. Utwórz talie.");
            System.out.println("2. Wyświetl wszystkie karty w talii.");
            System.out.println("3. Wyswietl liczbe kart.");
            System.out.println("4. Wyswietl karty o podanej wartosci.");
            System.out.println("5. Wyswietl karty o podanym kolorze.");
            System.out.println("6. Usuń powtarzające się karty.");
            System.out.println("=======");
            System.out.print("Wybierz numer opcji: ");
            int wybor = input.nextInt();

            switch (wybor){
                case 1: {
                    talia = arraySet();
                    break;
                }
                case 2: {
                    wyswietlElementy(talia);
                    break;
                }
                case 3: {
                    System.out.println(policzElementy(talia));
                    break;
                }
                case 4: {
                    do {
                        System.out.print("Wybierz wartość 1-13");
                        wartoscKolor = input.nextInt();
                    }while(wartoscKolor<1 && wartoscKolor > 13);
                    kartaOWartosci(wartoscKolor, talia);
                    break;
                }
                case 5: {
                    do {
                        System.out.print("Wybierz kolor 0-3");
                        wartoscKolor = input.nextInt();
                    }while(wartoscKolor<0 && wartoscKolor > 3);
                    kartaOKolorze(wartoscKolor, talia);
                    break;
                }
                case 6: {
                    usunPowtorzenia(talia);
                }
            }

        }
    }
}