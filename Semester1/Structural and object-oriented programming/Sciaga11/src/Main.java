import com.sun.source.tree.Tree;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.jar.JarEntry;

public class Main {

    public static void metoda1(Plyta plyta){
       // ArrayList<Utwor> utworyZPlyty = new ArrayList<>();

        for (Utwor k: plyta.utwory
             ) {
            System.out.println(k);
        }
    }

    public static void metoda2(HashSet<Plyta> ewidencjaPlyt){
        TreeSet<Utwor> noweUtwory = new TreeSet<>(new SortByTitle());
        for (Plyta p: ewidencjaPlyt
             ) {
            for (Utwor u: p.utwory
                 ) {
                noweUtwory.add(u);
            }
        }
        for (Utwor o: noweUtwory
             ) {
            System.out.println(o);
        }
    }

    public static void metoda4(HashSet<Plyta> ewidencjaPlyt, String wskazanyAutor){
        TreeSet<Utwor> ewidencje = new TreeSet<>(new SortByTitle());

        for (Plyta p: ewidencjaPlyt
             ) {
            ewidencje.addAll(p.utwory);
        }

        for (Utwor u: ewidencje
             ) {
            if(u.kompozytor.nazwisko.equals(wskazanyAutor))
                System.out.println(u);
        }

    }
    public static void metoda3(Plyta plyta, String nazwiskoAutora){
       Collections.sort(plyta.utwory, new SortByTitle());
        for (Utwor u: plyta.utwory
             ) {
            if(u.kompozytor.nazwisko.equals(nazwiskoAutora))
            System.out.println(u);
        }
    }

    public static void metoda5(Plyta plyta){
        TreeSet<Kompozytor> kompozytorzy = new TreeSet<>(new SortByAutor());

        for (Utwor u: plyta.utwory
             ) {
            kompozytorzy.add(u.kompozytor);
        }

        for (Kompozytor k: kompozytorzy
             ) {
            System.out.println(k);
        }
    }

    public static void metoda6(HashSet<Plyta> plyty){
        TreeSet<Utwor> wszystkieUtwory = new TreeSet<>(new SortByAutor2());
        for (Plyta p: plyty
             ) {
            for (Utwor u: p.utwory
                 ) {
                wszystkieUtwory.add(p.utwory);
            }
            }
        }


    public static void main(String[] args) {
        HashSet<Plyta> ewidencjaPlyt = new HashSet<>();
        ArrayList<Utwor> utwory1 = new ArrayList<>();
        utwory1.add(new Utwor("Muzyka1", new Kompozytor("Nazwiskowy", "Imionowy"), 346 ));
        utwory1.add(new Utwor("Muzyka2", new Kompozytor("Anon", "Bekinski"), 320));

        ArrayList<Utwor> utwory2 = new ArrayList<>();
        utwory2.add(new Utwor("Muzyka1", new Kompozytor("Nazwiskowy", "Imionowy"), 346 ));
        utwory2.add(new Utwor("Muzyka3", new Kompozytor("Zygfryd", "Xavier"), 120));

        ArrayList<Utwor> utwory3 = new ArrayList<>();
        utwory3.add(new Utwor("Muzyka4", new Kompozytor("Nazwiskowy", "Imionowy"), 306 ));
        utwory3.add(new Utwor("Muzyka6", new Kompozytor("Zygfryd", "Xavier"), 310));

        Plyta plyta1 = new Plyta(1, utwory1);
        Plyta plyta2 = new Plyta(2, utwory2);
        Plyta plyta3 = new Plyta(3, utwory3);

        ewidencjaPlyt.add(plyta1);
        ewidencjaPlyt.add(plyta2);
        ewidencjaPlyt.add(plyta3);

        System.out.println("Wyświetlenie wszystkich utworów");
        metoda1(plyta1);

        System.out.println("Wyświetlenie w kolejnoci alfabetycznej utworów");
        metoda2(ewidencjaPlyt);

        System.out.println("Wyświetlenie z danej płyty utworów danego autora ");
        String nazwiskoAutora = "Nazwiskowy";
        metoda3(plyta1, nazwiskoAutora);

        System.out.println("Wszystkie utwory wskazanego autora:");
        String wskazanyAutor = "Nazwiskowy";
        metoda4(ewidencjaPlyt, wskazanyAutor);

        System.out.println("Kompozytorzy z wybranej plyty");
        metoda5(plyta2);

        System.out.println("Kompozytorzy");
        metoda6(ewidencjaPlyt);
    }
}