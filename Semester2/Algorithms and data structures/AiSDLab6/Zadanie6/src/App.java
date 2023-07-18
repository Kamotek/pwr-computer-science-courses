public class App {
    public static void main(String[] args) throws Exception {

        Magazyn magazyn = new Magazyn();
        Magazyn magazyn2 = new Magazyn();
        Magazyn magazyn3 = new Magazyn();

        Firma firma = new Firma();

        firma.getMagazyny().add(magazyn);
        firma.getMagazyny().add(magazyn2);
        firma.getMagazyny().add(magazyn3);


        Klient klient1 = new Klient("klientPierwszy");
        Klient klient2 = new Klient("klientDrugi");
        Klient klient3 = new Klient("klientTrzeci");

        Klient klient4 = new Klient("klientCzwarty");
        Klient klient5 = new Klient("klientPiaty");
        Klient klient6 = new Klient("klientSzosty");

        Klient klient7 = new Klient("klientSiodmy");
        Klient klient8 = new Klient("klientOsmy");
        Klient klient9 = new Klient("klientDziewiaty");

        klient1.getKolejkaZamowien().enqueue(new Zamowienie("Jabłko", 10, 2));
        klient1.getKolejkaZamowien().enqueue(new Zamowienie("Gruszka", 15, 5));
        klient1.getKolejkaZamowien().enqueue(new Zamowienie("Jajko", 30, 1));

        klient2.getKolejkaZamowien().enqueue(new Zamowienie("Laptop", 3, 1200));
        klient2.getKolejkaZamowien().enqueue(new Zamowienie("Smartwatch",1, 2200));
        klient2.getKolejkaZamowien().enqueue(new Zamowienie("Smartphone", 10, 3200));
   
   
        klient3.getKolejkaZamowien().enqueue(new Zamowienie("Jeansy", 8, 330));
        klient3.getKolejkaZamowien().enqueue(new Zamowienie("Buty",2, 400));
        klient3.getKolejkaZamowien().enqueue(new Zamowienie("Tshirt", 30, 200));

        klient4.getKolejkaZamowien().enqueue(new Zamowienie("Jabłko", 10, 2));
        klient4.getKolejkaZamowien().enqueue(new Zamowienie("Gruszka", 15, 5));
        klient4.getKolejkaZamowien().enqueue(new Zamowienie("Jajko", 30, 1));

        klient5.getKolejkaZamowien().enqueue(new Zamowienie("Laptop", 3, 1200));
        klient5.getKolejkaZamowien().enqueue(new Zamowienie("Smartwatch",1, 2200));
        klient5.getKolejkaZamowien().enqueue(new Zamowienie("Smartphone", 10, 3200));
   
   
        klient6.getKolejkaZamowien().enqueue(new Zamowienie("Jeansy", 8, 330));
        klient6.getKolejkaZamowien().enqueue(new Zamowienie("Buty",2, 400));
        klient6.getKolejkaZamowien().enqueue(new Zamowienie("Tshirt", 30, 200));

        klient7.getKolejkaZamowien().enqueue(new Zamowienie("Jabłko", 10, 2));
        klient7.getKolejkaZamowien().enqueue(new Zamowienie("Gruszka", 15, 5));
        klient7.getKolejkaZamowien().enqueue(new Zamowienie("Jajko", 30, 1));

        klient8.getKolejkaZamowien().enqueue(new Zamowienie("Laptop", 3, 1200));
        klient8.getKolejkaZamowien().enqueue(new Zamowienie("Smartwatch",1, 2200));
        klient8.getKolejkaZamowien().enqueue(new Zamowienie("Smartphone", 10, 3200));
   
   
        klient9.getKolejkaZamowien().enqueue(new Zamowienie("Jeansy", 8, 330));
        klient9.getKolejkaZamowien().enqueue(new Zamowienie("Buty",2, 400));
        klient9.getKolejkaZamowien().enqueue(new Zamowienie("Tshirt", 30, 200));

        magazyn.getKolejkaKlientow().enqueue(klient1);
        magazyn.getKolejkaKlientow().enqueue(klient2);
        magazyn.getKolejkaKlientow().enqueue(klient3);

        magazyn2.getKolejkaKlientow().enqueue(klient4);
        magazyn2.getKolejkaKlientow().enqueue(klient5);
        magazyn2.getKolejkaKlientow().enqueue(klient6);

        magazyn3.getKolejkaKlientow().enqueue(klient7);
        magazyn3.getKolejkaKlientow().enqueue(klient8);
        magazyn3.getKolejkaKlientow().enqueue(klient9);

        magazyn.obsluga();
        magazyn2.obsluga();
        magazyn3.obsluga();

        System.out.println("---");
        firma.przychodyFirmy();

        




        
    }
}