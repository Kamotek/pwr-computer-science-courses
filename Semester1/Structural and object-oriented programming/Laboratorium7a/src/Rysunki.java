public class Rysunki {
    public static Bryla[] tablicaZBrylami = new Bryla[20];
    static int n; //liczba bryl w tablicy

    public static int liczbaFaktycznychElementowTablicy(){
        int licznik=0;
        for(int i=0;i<tablicaZBrylami.length;i++) {
            if(tablicaZBrylami[i]!=null){
                licznik++;
            }
        }
        return licznik;
    }

    public static void metodaSzukajacaNajwiekszegoPola(){
        Bryla wynik = tablicaZBrylami[0];
        int pozycja=0;
        for(int i=0;i<tablicaZBrylami.length;i++){
            if(tablicaZBrylami[i]!=null){
                if(tablicaZBrylami[i].Pole()>wynik.Pole()) {
                    wynik = tablicaZBrylami[i];
                    pozycja = i;
                }
            }
        }

        System.out.println("Pozycja figury o najwiekszym polu w tablicy to: [" + pozycja + "] nazwa tej figury natomiast to: " + wynik.getNazwa());
    }

    public static void WyswietlanieCalejTablicy(){
        for(int i=0;i< tablicaZBrylami.length;i++){
            if(tablicaZBrylami[i]!=null){
                System.out.println(tablicaZBrylami[i]);
            }
        }
    }
    public static void WyswietlanieWalcow(){
        for(int i=0;i< tablicaZBrylami.length;i++){
            if(tablicaZBrylami[i]!=null){
                if(tablicaZBrylami[i] instanceof Walec)
                System.out.println(tablicaZBrylami[i]);
            }
        }
    }

    public static void WyswietlanieProstopadloscianow() {
        for (int i = 0; i < tablicaZBrylami.length; i++) {
            if (tablicaZBrylami[i] != null) {
                if (tablicaZBrylami[i] instanceof Prostopadloscian)
                    System.out.println(tablicaZBrylami[i]);
            }
        }
    }
    public static int WyswietlanieIleJestSzescianow(){
        int licznik=0;
        for (int i = 0; i < tablicaZBrylami.length; i++) {
            if (tablicaZBrylami[i] != null) {
                if (tablicaZBrylami[i] instanceof Prostopadloscian){
                    if(((Prostopadloscian) tablicaZBrylami[i]).jestSzescianem()){
                        licznik++;
                    }
                }
            }
        }
        return licznik;
    }
    public static int WyswietlanieIleJestWalcow(){
        int licznik=0;
        for (int i = 0; i < tablicaZBrylami.length; i++) {
            if (tablicaZBrylami[i] != null) {
                if (tablicaZBrylami[i] instanceof Walec){
                    if(((Walec) tablicaZBrylami[i]).obrotKwadratu()){
                        licznik++;
                    }
                }
            }
        }
        return licznik;
    }

    public static void main(String[] args) {

        tablicaZBrylami[0] = new Prostopadloscian();
        tablicaZBrylami[1] = new Prostopadloscian("Prostopadloscian1", 10, 5, 7);
        tablicaZBrylami[2] = new Prostopadloscian("Prostopadloscian2",5,5,5);
        tablicaZBrylami[3] = new Prostopadloscian("Prostopadloscian3",7,7,7);
        tablicaZBrylami[4] = new Prostopadloscian("Prostopadloscian4",4,7,7);

        tablicaZBrylami[5] = new Walec();
        tablicaZBrylami[6] = new Walec("Walec1",5,5);
        tablicaZBrylami[7] = new Walec("Walec2",10,12);
        tablicaZBrylami[8] = new Walec("Walec3",7,6);
        tablicaZBrylami[9] = new Walec("Walec4",4,8);


        n = liczbaFaktycznychElementowTablicy();

        metodaSzukajacaNajwiekszegoPola();

        System.out.println("-------------------------------");
        System.out.println("Wszystkie elementy w tablicy: ");
        WyswietlanieCalejTablicy();

        System.out.println("---------------------------------");
        System.out.println("Wszystkie walce w tablicy: ");
        WyswietlanieWalcow();

        System.out.println("------------------------------------");
        System.out.println("Wszystkie prostopadlosciany w tablicy: ");
        WyswietlanieProstopadloscianow();

        System.out.println("--------------------------------------");
        System.out.println("Wszystkie szesciany w tablicy: ");
        System.out.println("liczba szescianow " + WyswietlanieIleJestSzescianow());

        System.out.println("---------------------------------------- ");
        System.out.println("Wszystkie walce powstale przez obrot kwadratu w tablicy: ");
        System.out.println("liczba walcow " + WyswietlanieIleJestWalcow());

        System.out.println("Liczba faktycznych elementow w tablicy to: " + n);
    }
}