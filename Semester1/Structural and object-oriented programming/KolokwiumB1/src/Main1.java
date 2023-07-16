import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class Main1 {

    public static void metoda1(ArrayList<Ksiazka> biblioteka){
        TreeSet<Ksiazka> bibliotekaNowa = new TreeSet<>(new SortByTitle());
        bibliotekaNowa.addAll(biblioteka);

        for (Ksiazka k: bibliotekaNowa
             ) {
            System.out.println(k);
        }
    }

    public static void metoda3(ArrayList<Ksiazka> biblioteka, String wskazanyAutor){
        TreeSet<Ksiazka> bibliotekaNowa = new TreeSet<>(new SortByTitle());
        bibliotekaNowa.addAll(biblioteka);

        for (Ksiazka k: bibliotekaNowa
        ) {
            if(k.autor.nazwisko.equals(wskazanyAutor))
            System.out.println(k);
        }
    }

    public static void metoda4(ArrayList<Ksiazka> biblioteka){
        TreeSet<Autor> bazaAutorow = new TreeSet<>(new SortBySurname());
        for (Ksiazka k: biblioteka
             ) {
            bazaAutorow.add(k.autor);
        }
        for (Autor a: bazaAutorow
             ) {
            System.out.println(a);
        }
    }

    public static void metoda5(ArrayList<Ksiazka> biblioteka, String wskazaneWydawnictwo){

        Collections.sort(biblioteka, new SortByAge());

        for (Ksiazka k: biblioteka
             ) {
            if(k.wydawnictwo.equals(wskazaneWydawnictwo))
            System.out.println(k);
        }
    }

    public static void metoda2(ArrayList<Ksiazka> biblioteka){
        TreeSet<Ksiazka> bibliotekaNowa = new TreeSet<>(new SortByAutorAndTitle());
        bibliotekaNowa.addAll(biblioteka);

        for (Ksiazka k: bibliotekaNowa
        ) {
            System.out.println(k);
        }
    }

    public static void main(String[] args)
    {
        ArrayList<Ksiazka> biblioteka = new ArrayList<>();

        Ksiazka ksiazka1 = new Ksiazka(new Autor("nazwisko1", "imie1"), "tytul1", "wydawnictwo1", 1959);
        Ksiazka ksiazka2 = new Ksiazka(new Autor("nazwisko2", "imie2"), "tytul2", "wydawnictwo2", 1999);
        Ksiazka ksiazka3 = new Ksiazka(new Autor("nazwisko3", "imie3"), "tytul3", "wydawnictwo3", 1239);

        biblioteka.add(ksiazka1);
        biblioteka.add(ksiazka2);
        biblioteka.add(ksiazka3);

        System.out.println("Wyswietlanie wg tytulow ksiazek");
        metoda1(biblioteka);

        System.out.println("Wyswietlanie wg autorow i tytulow");
        metoda2(biblioteka);

        System.out.println("Wyswietlanie wg tytulow wskazanego autora");
        String nazwiskoAutora = "nazwisko1";
        metoda3(biblioteka, nazwiskoAutora);

        System.out.println("Wyswietlanie wszystkich autorow ksiazek");
        metoda4(biblioteka);

        System.out.println("Wyswietlanie wszystkich ksiazek wskazanego wydawnictwa");
        String wskazaneWydawnictwo = "wydawnictwo2";
        metoda5(biblioteka, wskazaneWydawnictwo);
    }
}