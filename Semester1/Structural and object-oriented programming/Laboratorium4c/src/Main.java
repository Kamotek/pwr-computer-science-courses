public class Main {

    static double Funkcja(int n, int x){
        double wynik = 0;
        double temp = 1;

        if(x<0)
            wynik = 2;
        if(x<=2 && x>=0)
            wynik = (Math.cos(x) + x);
        if(x>2){
            for(int i = 1;i<=n;i++)
            {
                //wynik += Math.pow(x,i)/i;

                wynik += (x*temp)/i;
                temp = x*temp;

                //wynik += (x*x)temp;
                // x^1/1 + x^2/2 + x^3/3 + x^4/4 + x^5/5
            }
        }
        return wynik;
    }
    public static void main(String[] args) {
        System.out.print("Wynik jest rowny: ");

        int n = 3;
        int x = 7;
// 3^1/1 + 3^2/2
        System.out.println(Funkcja(n, x));

        /* funkcja (n,x)

         */
    }
}