public class Main {

    static double metoda1(int n, double x){
        double wynik=0;
        for (int i=0;i<n;i++)
        {
            wynik+=(Math.pow((x-1),i+1))/((i+1)*Math.pow(x,(i+1)));

        }
        return wynik;
    }

    static void metoda2(double x, int n){
        System.out.printf("xdxdxdxdx %s %.2f %s %.3f %s","x=", x ," fx=",metoda1(n,x), "\n");
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");

         int x = 5; //dlugosc tablicy
        int n = 5; //ilosc elementow ciagu

        double[] tab = {1,5,7};

        for(int i=0;i<tab.length;i++)
            metoda2(tab[i],n);
    }
}