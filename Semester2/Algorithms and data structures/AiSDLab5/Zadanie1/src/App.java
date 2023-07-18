import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class App {

    static TwoWayCycledListWithSentinel<Pracownik> bazaDanych;

    static ListStack<Pracownik> zaparkowaneSamochody = new ListStack<>();
    static ListStack<Pracownik> samochodyDoPrzestawienia = new ListStack<>();
    static ListStack<Pracownik> tymczasowe = new ListStack<>();

    


    public static void menu() throws EmptyStackException{
        while(true){
            System.out.println("=========Menu=========");
            System.out.println("Wybierz opcje: ");
            System.out.println("1. Utworzenie nowej bazy danych");
            System.out.println("2. Odczyt bazy z pliku");
            System.out.println("3. Wyświetlenie wszystkich rekordów");
            System.out.println("4. Wyświetlenie danych jednego pracownika");
            System.out.println("5. Dopisanie nowego pracownika");
            System.out.println("6. Usunięcie pracownika z bazy");
            System.out.println("7. Aktualizowanie danych pracownika");
            System.out.println("8. Obliczanie średniej pensji w firmie");
            System.out.println("9. Obliczanie ilu pracowników zarabia poniżej średniej");
            System.out.println("10. Zapis bazy do pliku");
            System.out.println("11. Zarządzanie parkingiem");
            System.out.print("Opcja numer: ");
            Scanner input = new Scanner(System.in);
            int opcja = input.nextInt();
            

            switch(opcja){
                case 1:{
                    utworzenieBazyDanych();
                    break;
                }
                case 2: {
                    odczytBazy();
                    break;
                }
                case 3: {
                    wyswietlRekordy();
                    break;
                }
                case 4: {
                    System.out.print("Podaj pesel: ");
                    int pesel = input.nextInt();
                    System.out.println(wyswietlDanePracownika(pesel));
                    break;
                }
                case 5: {
                    dodajPracownika();
                    break;
                }
                case 6: {
                    System.out.print("Podaj pesel: ");
                    int pesel = input.nextInt();
                    usunPracownika(pesel);
                    break;
                }
                case 7: {
                    System.out.print("Podaj pesel: ");
                    int pesel = input.nextInt();
                    aktualizujPracownika(pesel);
                    break;
                }
                case 8: {
                    obliczSredniaPensje();
                    break;
                }
                case 9: {
                    iluPracownikowZarabiaMniej();
                    break;
                }
                case 10: {
                    zapiszBaze();
                    break;
                }
                case 11: {
                    parkingoweMenu();
                    break;
                }
            }
        }
    }

    private static void parkingoweMenu() throws EmptyStackException{
        System.out.println("=======ParkingMenu=======");
        System.out.println("Opcje:");
        System.out.println("1. Pracownik przyjechał");
        System.out.println("2. Pracownik chce odjechać");

        Scanner inputParking = new Scanner(System.in);
        int opcjaParking = inputParking.nextInt();

        switch(opcjaParking){
            case 1: {
                System.out.print("Wprowadź pesel pracownika: ");
                inputParking.nextLine();
                int pesel = inputParking.nextInt();
                przyjazdPracownika(pesel);
                break;
            }
            case 2: {
                System.out.print("Wprowadź pesel pracownika: ");
                inputParking.nextLine();
                int pesel = inputParking.nextInt();
                odjazdPracownika(pesel);
                break;
            }
        }
        
    }

    static void przyjazdPracownika(int pesel){
            Pracownik pracownik = null;
            for (Pracownik p : bazaDanych) {
                if(p.getPesel() == pesel){
                    pracownik = p;
                }
             }
             if(pracownik != null){
             zaparkowaneSamochody.push(pracownik);
             System.out.println("Przyjechał pracownik o numerze pesel: " + pracownik.getPesel());
             }else{System.out.println("Przyjechał ktoś o nieznanym peselu, więc nie wjedzie");}
    }

    static void odjazdPracownika(int pesel) throws EmptyStackException{
        Pracownik pracownik = null;
        for (Pracownik p : bazaDanych) {
            if(p.getPesel() == pesel){
                pracownik = p;
            }
         }


         if(pracownik!=null){
            while(zaparkowaneSamochody.top().getPesel()!=pesel){
                if(zaparkowaneSamochody.top().getPesel()==pesel) break; 
                samochodyDoPrzestawienia.push(zaparkowaneSamochody.pop());
            }
            tymczasowe = odwroc(samochodyDoPrzestawienia);
            System.out.println("Pracownicy o numerach pesel kolejno: ");
            while(!tymczasowe.isEmpty()){
                if(tymczasowe.top().getPesel()==pesel){zaparkowaneSamochody.pop();}
                System.out.println(tymczasowe.top().getPesel() + ", ");
                zaparkowaneSamochody.push(tymczasowe.pop());
            }
            System.out.println("\n są proszeni o przestawienie swoich samochodów");

         }
    }

    static ListStack<Pracownik> odwroc(ListStack<Pracownik> samochodyDoPrzestawienia) throws EmptyStackException{
        ListStack<Pracownik> temp1 = new ListStack<>();
        ListStack<Pracownik> temp2 = new ListStack<>();

        while(!samochodyDoPrzestawienia.isEmpty())
            temp1.push(samochodyDoPrzestawienia.pop());
        while(!temp1.isEmpty())
            temp2.push(temp1.pop());
        while(!temp2.isEmpty())
            samochodyDoPrzestawienia.push(temp2.pop());

        return samochodyDoPrzestawienia;
    } 
    private static void zapiszBaze() {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("bazaDanych.ser")))  {
            os.writeObject(bazaDanych);
            System.out.println("Bazę zapisano pomyślnie!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Błąd podczas zapisu do pliku!");
        }
    }
    private static void iluPracownikowZarabiaMniej() {
        double srednia = obliczSredniaPensje();
        int counter = 0;

        for (Pracownik p : bazaDanych) {
            if((p.getPensja()+p.getPremia())<srednia)
                counter++;
        }
        System.out.println(counter + " pracownikow zarabia mniej niz srednia");
    }
    private static double obliczSredniaPensje() {
        double suma = 0;
        for (Pracownik p : bazaDanych) {
            suma+=(p.getPensja()+p.getPremia());
        }
        System.out.println("Srednia pensja wynosi: " + suma/bazaDanych.size() + " zl");

        return suma/bazaDanych.size();
    }
    private static void aktualizujPracownika(int pesel) {

        if(bazaDanych.size()!=0){
            for (Pracownik p : bazaDanych) {
                if(p.getPesel() == pesel){
                    aktualizacja(p);
                }
             }
        }
    }

    private static void aktualizacja(Pracownik p){
        System.out.println("Co chcesz zaktualizować?");
        System.out.println("1. Pesel");
        System.out.println("2. Imie");
        System.out.println("3. Nazwisko");
        System.out.println("4. Data urodzenia");
        System.out.println("5. Pensja");
        System.out.println("6. Staz");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Wprowadź opcję: ");
        int opcja = scanner.nextInt();

        switch(opcja){
            case 1: {
                System.out.print("Wprowadź nowy pesel: ");
                scanner.nextLine();
                int pesel = scanner.nextInt();
                p.setPesel(pesel);
                break;
            }

            case 2: {
                System.out.print("Wprowadź nowe imie: ");
                scanner.nextLine();
                String imie = scanner.nextLine();
                p.setImie(imie);
                break;
            }

            case 3: {
                System.out.print("Wprowadź nowe nazwisko: ");
                scanner.nextLine();
                String nazwisko = scanner.nextLine();
                p.setNazwisko(nazwisko);
                break;
            }

            case 4: {
                System.out.print("Wprowadź nowa date urodzenia: ");
                scanner.nextLine();
                String dataUrodzenia = scanner.nextLine();
                p.setData(dataUrodzenia);
                break;
            }

            case 5: {
                System.out.print("Wprowadź nowa pensje: ");
                scanner.nextLine();
                double pensja = scanner.nextDouble();
                p.setPensja(pensja);
                break;
            }

            case 6: {
                System.out.print("Wprowadź nowy staz: ");
                scanner.nextLine();
                int staz = scanner.nextInt();
                p.setStaz(staz);
                break;
            }
            
        }
        p.update();

        Comparator<Pracownik> comparator = new Comparator<Pracownik>() {
            public int compare(Pracownik a, Pracownik b) {
                return a.getPesel() - b.getPesel();
            }
        };
        Collections.sort(bazaDanych, comparator);
    }

    private static void usunPracownika(int pesel) {

        if(bazaDanych.size()!=0){
            for (Pracownik p : bazaDanych) {
                if(p.getPesel() == pesel){
                    bazaDanych.remove(p);
                }
             }
        }

    }
    private static void dodajPracownika() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj pesel: ");
        int pesel = scanner.nextInt();
        System.out.print("\nPodaj imie: ");
        scanner.nextLine();
        String imie = scanner.nextLine();
        System.out.print("\nPodaj nazwisko: ");
        String nazwisko = scanner.nextLine();
        System.out.print("\nPodaj date urodzenia: ");
        String data = scanner.nextLine();
        System.out.print("\nPodaj pensje: ");
        double pensja = scanner.nextDouble();
        System.out.print("\nPodaj staz: ");
        int staz = scanner.nextInt();
        bazaDanych.add(new Pracownik(pesel, imie, nazwisko, data, pensja, staz));

        Comparator<Pracownik> comparator = new Comparator<Pracownik>() {
            public int compare(Pracownik a, Pracownik b) {
                return a.getPesel() - b.getPesel();
            }
        };
        Collections.sort(bazaDanych, comparator);
    }
    private static String wyswietlDanePracownika(int pesel) {

        if(bazaDanych.size()!=0){
            for (Pracownik p : bazaDanych) {
                if(p.getPesel() == pesel){
                    return p.toString();
                }
            }
        }
        return "Nie ma takiego pracownika";
    }
    private static void wyswietlRekordy() {
        if(bazaDanych.size()!=0){
            for (Pracownik p : bazaDanych) {
                System.out.println(p.toString());
            }
        }
    }
    private static void odczytBazy() {  {
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("bazaDanych.ser")))
            {
                Object obj = is.readObject();
                bazaDanych = (TwoWayCycledListWithSentinel<Pracownik>) obj;
                System.out.println("Baza została poprawnie odczytana!" + "\n");
            }
            catch (IOException | ClassNotFoundException e)
            {
                e.printStackTrace();
                System.out.println("Błąd podczas odczytu z pliku!");
            }
        }

    }
    private static void utworzenieBazyDanych() {
        bazaDanych = new TwoWayCycledListWithSentinel<Pracownik>();
    }
    public static void main(String[] args) throws Exception {
        
        menu();



    }
}
