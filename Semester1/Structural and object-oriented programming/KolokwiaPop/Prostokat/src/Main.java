import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        int n = 10;

        int[][] tablica = new int[n][2];

        for(int i = 0; i<n;i++){
            tablica[i][0] = random.nextInt(20)-10;
            tablica[i][1] = random.nextInt(20)-10;
        }
        for(int i = 0; i<n;i++){
            System.out.println("Punkt " + i + ". ma wspolrzedne: " + tablica[i][0] + ", " + tablica[i][1]);
        }

        double temp1 =Math.sqrt(tablica[0][0]*tablica[0][0]+tablica[0][1]*tablica[0][1]);
        int indexNajwiekszejOdleglosci =0;
        for(int i = 0; i<n;i++){
            if(temp1<Math.sqrt(tablica[i][0]*tablica[i][0]+tablica[i][1]*tablica[i][1]))
            {
                temp1 = Math.sqrt(tablica[i][0] * tablica[i][0] + tablica[i][1] * tablica[i][1]);
                indexNajwiekszejOdleglosci = i;
            }
        }
        System.out.println("Punkt o najwiekszej odleglosci to punkt o numerze " + indexNajwiekszejOdleglosci + " o wspolrzednych " + tablica[indexNajwiekszejOdleglosci][0] + ", " + tablica[indexNajwiekszejOdleglosci][1]);
        System.out.println("Jego odleglosc to: " + temp1);

        int xMin = tablica[0][0];
        int xMax = tablica[0][0];
        int yMin = tablica[0][1];
        int yMax = tablica[0][1];
        for(int i=0;i<n;i++) {
            if (xMin > tablica[i][0]) {
                xMin = tablica[i][0];
            }
            if (xMax < tablica[i][0]) {
                xMax = tablica[i][0];
            }
            if (yMin > tablica[i][1]) {
                yMin = tablica[i][1];
            }
            if (yMax < tablica[i][1]) {
                yMax = tablica[i][1];

            }
        }
        System.out.println("Punkty narozne prostokata to: " +
                "(" + xMax + ", " + yMax + ")" + "(" + xMax + ", " + yMin + ")(" + xMin + ", "
                + yMin + ")(" + xMin + ", " + yMax + ")");
    }
}