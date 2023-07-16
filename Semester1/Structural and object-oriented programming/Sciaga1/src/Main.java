import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

public class Main {
    public static ArrayList<Ksiazka> biblioteka= new ArrayList<>();
    public static void metoda1(String imie, String nazwisko, String tytul, int liczbaStron, int numerEwidencyjny){
        biblioteka.add(new Ksiazka(imie, nazwisko, tytul, liczbaStron, numerEwidencyjny));
    }

    public static void metoda2(ArrayList<Ksiazka> biblioteka){
//        biblioteka.sort(((o1, o2) -> o1.autor.nazwisko.compareTo(o2.autor.nazwisko)));

        Collections.sort(biblioteka, new SortByAutor());
        for (Ksiazka e: biblioteka
             ) {
            System.out.println(e);
        }
    }

    public static void metoda3(ArrayList<Ksiazka> biblioteka){
     //   biblioteka.sort(((o1, o2) -> o1.liczbaStron));
        Collections.sort(biblioteka, new SortByPages());
        for (Ksiazka o: biblioteka
             ) {
            System.out.println(o);
        }
    }

    public static void metoda4(ArrayList<Ksiazka> biblioteka, String nazwiskoAutora){



        TreeSet<Ksiazka> zbiorKsiazek = new TreeSet<>(new SortByTitle());
        zbiorKsiazek.addAll(biblioteka);
                        for (Ksiazka k: zbiorKsiazek
             ) {
            if(k.autor.nazwisko.equals(nazwiskoAutora)){
                System.out.println(k.tytul);
            }
        }
    }
    public static void metoda5(ArrayList<Ksiazka> biblioteka){
        Collections.sort(biblioteka, new SortByAutor());
        for (Ksiazka k: biblioteka
             ) {
            System.out.println(k.autor);

        }
    }
    public static void main(String[] args) {

        Ksiazka xd = new Ksiazka("Imie", "Nazwisko", "Tytul", 105, 1);
        Ksiazka lol = new Ksiazka("Zygu", "Woro", "Kapusta", 125, 2);
        Ksiazka lmao = new Ksiazka("Alex", "Bradany", "Erpeg", 500, 3);

        metoda1("Imie", "Nazwisko", "Tytul", 105, 1);
        metoda1("Zygu", "Aoro", "Kapusta", 125, 2);
        metoda1("Alex", "Bradany", "Erpeg", 500, 3);
        metoda1("Zygu", "Aoro", "Kapusta", 125, 2);
        metoda1("Zygu", "Aoro", "Salata", 1225, 5);




        metoda2(biblioteka);
        System.out.println("---");
        metoda3(biblioteka);
        System.out.println("---");
        String nazwiskoAutora = "Aoro";
        metoda4(biblioteka, nazwiskoAutora);
        System.out.println("---");
        metoda5(biblioteka);
    }
}