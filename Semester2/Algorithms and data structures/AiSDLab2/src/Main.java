import javax.sound.midi.SysexMessage;
import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static Scanner skaner = new Scanner(System.in);


    public static Pracownik tworzeniePracownika(){

        int wybor;
        do {
            System.out.println("Jeżeli pracownik etatowy to 1, a jeżeli godzinowy to 2");
            wybor = skaner.nextInt();
        }while(wybor != 1 && wybor != 2);

        System.out.println("Nazwisko (string):");
        skaner.nextLine();
        String nazwisko = skaner.nextLine();
        System.out.println("Imie (string):");
        String imie = skaner.nextLine();
        System.out.println("Pesel (long):");
        long pesel = skaner.nextLong();
        System.out.println("Stanowisko (string): ");
        skaner.nextLine();
        String stanowisko = skaner.nextLine();
        System.out.println("Staz (int):");
        int staz = skaner.nextInt();

        if(wybor == 1){
            return tworzeniePracownikaEtatowego(nazwisko, imie, pesel, stanowisko, staz);
        }
        if(wybor == 2) {
            return tworzeniePracownikaGodzinowego(nazwisko, imie, pesel, stanowisko, staz);
        }
        return null;
    }

    public static Pracownik tworzeniePracownikaGodzinowego(String nazwisko, String imie, long pesel, String stanowisko, int staz){
        System.out.println("Podaj stawke godzinowa (double): ");
        double stawka = skaner.nextDouble();
        System.out.println("Podaj liczbe przepracowanych godzin (int): ");
        int liczba_godz = skaner.nextInt();


        return new PracownikGodzinowy(nazwisko, imie, pesel, stanowisko, staz, stawka, liczba_godz){
            @Override
            void wyswietl() {
                System.out.printf("| %-12s | %-12s | %-12d | %-12d | %-12s | %-12f |" , getImie(),getNazwisko(),getStaz(),getPesel(),getStanowisko(), pensja());
            }
        };
    }

    public static Pracownik tworzeniePracownikaEtatowego(String nazwisko, String imie, long pesel, String stanowisko, int staz){
        System.out.println("Podaj etat (float): ");
        float etat = skaner.nextFloat();
        System.out.println("Podaj stawke (double): ");
        double stawka = skaner.nextDouble();

        return new PracownikEtatowy(nazwisko, imie, pesel, stanowisko, staz, etat, stawka){
            @Override
            void wyswietl() {
                System.out.printf("| %-12s | %-12s | %-12d | %-12d | %-12s | %-12f |" , getImie(),getNazwisko(),getStaz(),getPesel(),getStanowisko(), pensja());
            }
        };
    }

    public static void serializacjaPracownikow(Pracownik[] pracownicy){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pracownicy.bin"));
            for (Pracownik x: pracownicy
                 ) {
                oos.writeObject(x);
            }
            oos.writeChars("Ilość obiektów wynosi: " + pracownicy.length);
            oos.close();
        }catch(IOException x){System.out.println(x);}
    }

    public static void odczytSerializacjiPracownikow(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("pracownicy.bin"));
            Object pracownik;
            try {
                try {
                    while ((pracownik = ois.readObject()) != null) {
                        Pracownik zdeserializowanyPracownik = (Pracownik) pracownik;

                        System.out.println(zdeserializowanyPracownik);
                    }
                }catch(OptionalDataException z){System.out.println();}
                ois.close();
            }catch(ClassNotFoundException x){System.out.println(x);}
        }catch(IOException x){System.out.println(x);}
    }


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Podaj ilu pracowników chcesz utworzyć: ");
        int n = input.nextInt();

        Pracownik[] pracownicy = new Pracownik[n];

        for(int i = 0; i<n; i++){
            pracownicy[i] = tworzeniePracownika();
        }

        serializacjaPracownikow(pracownicy);

        odczytSerializacjiPracownikow();

        for(int i = 0; i<90;i++)
            System.out.print("-");
        System.out.println();
        System.out.printf("| %-12s | %-12s | %-12s | %-12s | %-12s | %-12s |", "Nazwisko", "Imie", "Pesel", "Stanowisko", "Staż", "Pensja");
        System.out.println();
        for(int i = 0; i<90;i++)
            System.out.print("-");
        System.out.println();

        Iterator<Pracownik> iterator = new IteratorPracownicy(pracownicy);
            while(iterator.hasNext()){
                iterator.next().wyswietl();
                System.out.println();
        }
        for(int i = 0; i<90;i++)
            System.out.print("-");
        System.out.println();
        System.out.println("Ilość pracownikow:" + n);



    }
}