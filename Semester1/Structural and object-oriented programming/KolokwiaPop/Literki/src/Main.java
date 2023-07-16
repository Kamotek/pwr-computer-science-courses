import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Random random = new Random();

        //int numer = random.nextInt(25)+65;
        //char literka = (char) numer;

        //System.out.println(literka);

        int n = 10;
        char[] litery = new char[n];
        int[][] liczby = new int[n][2];

        char xd = (char) (random.nextInt(25)+65);
        System.out.println(xd);

        for(int i=0;i<n;i++){
            litery[i] = (char) (random.nextInt(25)+65);
            }
        for(int i=0;i<n;i++){
            liczby[i][0] = random.nextInt(10)-5;
            liczby[i][1] = random.nextInt(10)-5;
        }

        for(int i=0;i<n;i++){
            System.out.println(litery[i] + "=(" + liczby[i][0]+", " + liczby[i][1] + ")");
        }
        double[] odleglosci = new double[n];
        for(int i = 0; i<n; i++){
            odleglosci[i] = Math.sqrt(((liczby[i][0])*(liczby[i][0]))+(liczby[i][1]*liczby[i][1]));
        }


        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                double temp = 0;
                if(odleglosci[i]>odleglosci[j]){
                    temp = odleglosci[i];
                    odleglosci[i] = odleglosci[j];
                    odleglosci[j] = temp;
                }
            }
        }

        for(int i = 0; i<n; i++){
           // Arrays.sort(odleglosci);
            System.out.println(odleglosci[i]);
        }



    }
}
