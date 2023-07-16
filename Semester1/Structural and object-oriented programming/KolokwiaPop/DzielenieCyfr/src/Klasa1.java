public class Klasa1 {
    private int liczba;

    public Klasa1(int liczba) {
        this.liczba = liczba;
    }

    public int getLiczba() {
        return liczba;
    }


    public int metoda1(int liczba) {
        int suma = 0;
        while (!(liczba == 0)) {
            suma += liczba % 10;
            liczba /= 10;
        }
        return suma;
    }

    public void metoda2(){
        int licznik=0;
        for(int i=1000;i<10000;i++) {
                if (metoda1(i) % 2 == 0) {
                    licznik++;
                    System.out.print(i + "  ");
                    if(licznik%10==0)
                        System.out.println("");
                }
        }
        System.out.println("Było takich liczb: " + licznik);
    }
}
