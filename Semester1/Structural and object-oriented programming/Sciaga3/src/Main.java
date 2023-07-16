public class Main {

    public static void zmianaZatrudnienia(Pracownik pracownik, String zmiana){

        pracownik.setSposobWynagradzania(zmiana);
    }


    public static void main(String[] args) {

        String imie = "Jan";
        String nazwisko = "Jankowski";
        int wiek = 50;
        String rodzajZatrudnienia = "zlecenie";
        double pensja = 1800;
        double stawka = 30;
        double koszta = 500;
        int godziny = 16;
        Pracownik pracownik1 = new Pracownik();

        if(rodzajZatrudnienia.equals("etat")){
            pracownik1 = new Pracownik(imie, nazwisko, wiek, rodzajZatrudnienia, pensja, stawka, koszta, godziny) ;
        }
        if(rodzajZatrudnienia.equals("zlecenie")){
            pracownik1 = new Pracownik(imie, nazwisko, wiek, rodzajZatrudnienia,pensja, stawka, koszta, godziny);
        }

        pracownik1.ustawWyplate();
        System.out.println(pracownik1.getWyplata());
        System.out.println(pracownik1.sposobWynagradzania);


        zmianaZatrudnienia(pracownik1, "etat");

        pracownik1.ustawWyplate();
        System.out.println(pracownik1.getWyplata());
        System.out.println(pracownik1.sposobWynagradzania);


    }
}