import java.util.*;

public class Main {

    public static OneWayLinkedListWithHead<Karta> karty = new OneWayLinkedListWithHead<>();
    static boolean[] tablicaPowtorzen = new boolean[52];


    public static void wyczyscTablice()
    {
        for (int i=0; i<52; i++)
            tablicaPowtorzen[i] = false;
    }

    public static int znajdzIndeks(int kolor, int wartosc)
    {

        int i = 0;
      /*  for (Karta x: karty
             ) {if(wartosc <= x.getWartosc()){break;}
                if(wartosc> x.getWartosc()){
                    i++;
                }else{
                    if(kolor<=x.getKolor()){
                        break;
                    }else{i++;}
                }
            }
        return i; */
        int temp = Integer.parseInt(Integer.toString(wartosc) + Integer.toString(kolor));
        for (Karta x: karty
             ) {
            if(Integer.parseInt(Integer.toString(x.getWartosc()) + Integer.toString(x.getKolor())) < temp) i++;
            if(Integer.parseInt(Integer.toString(x.getWartosc()) + Integer.toString(x.getKolor())) >= temp) break;


        }
        return i;
    }

    public static void arraySet(){
        Random random = new Random();
        int wartosc = 14;
        int kolor = 0;
        int n = 0;

        wyczyscTablice();
        karty.clear();

        while(wartosc!=0){

            wartosc = random.nextInt(15);
            if(wartosc == 0) break;
            kolor = random.nextInt(4);

            if(wartosc == 14) {
                karty.add(new Karta(kolor, wartosc, false));
                n++;
            }else{
                if(!tablicaPowtorzen[(wartosc-1)+13*kolor]){
                    if(n==0){
                        karty.add(new Karta(kolor, wartosc, true));
                    }else{
                        karty.add(znajdzIndeks(kolor, wartosc), new Karta(kolor, wartosc, true));
                    }
                    tablicaPowtorzen[(wartosc-1)+13*kolor] = true;
                    n++;
                }
            }
        }
    }

    public static void wyswietlElementy(){
        for(Karta x : karty){
            System.out.println(x);
        }
    }

    public static void policzElementy()
    {
        int z=0, o=0;
        for (Karta k : karty)
        {
            if (k.isPowtorzenie())
                o++;
            else
                z++;
        }
        System.out.println("Liczba elementów listy: " + (o+z));
        System.out.println("Kart zakrytych: " + z);
        System.out.println("Kart odkrytych: " + o);
    }

    public static  void kartaOWartosci(int wartosc)
    {
        for (Karta k : karty)
        {
            if (k.getWartosc()==wartosc && k.isPowtorzenie())
                System.out.println(k);
        }
    }

    public static void kartaOKolorze(int kolor)
    {
        for (Karta k : karty)
        {
            if (k.getKolor()==kolor && k.isPowtorzenie())
                System.out.println(k);
        }
    }

    public static void usunZakryte()
    {
        for (Karta k : karty)
        {
            if (!k.isPowtorzenie())
                karty.remove(k);
        }
    }


    public static void main(String[] args) {
        boolean[] obecnoscKart = new boolean[52];
        Scanner input = new Scanner(System.in);
        int wartoscKolor;
        ArrayList<Karta> talia = null;
        while(true){
            System.out.println("===Menu===");
            System.out.println("1. Utwórz talie.");
            System.out.println("2. Wyświetl wszystkie karty w talii.");
            System.out.println("3. Wyswietl liczbe kart.");
            System.out.println("4. Wyswietl karty o podanej wartosci.");
            System.out.println("5. Wyswietl karty o podanym kolorze.");
            System.out.println("6. Usuń zakryte karty.");
            System.out.println("=======");
            System.out.print("Wybierz numer opcji: ");
            int wybor = input.nextInt();

            switch (wybor){
                case 1: {
                    arraySet();
                    break;
                }
                case 2: {
                    wyswietlElementy();
                    break;
                }
                case 3: {
                    policzElementy();
                    break;
                }
                case 4: {
                    do {
                        System.out.print("Wybierz wartość 1-13");
                        wartoscKolor = input.nextInt();
                    }while(wartoscKolor<1 && wartoscKolor > 13);
                    kartaOWartosci(wartoscKolor);
                    break;
                }
                case 5: {
                    do {
                        System.out.print("Wybierz kolor 0-3");
                        wartoscKolor = input.nextInt();
                    }while(wartoscKolor<0 && wartoscKolor > 3);
                    kartaOKolorze(wartoscKolor);
                    break;
                }
                case 6: {
                    usunZakryte();
                }
            }

        }
    }
}