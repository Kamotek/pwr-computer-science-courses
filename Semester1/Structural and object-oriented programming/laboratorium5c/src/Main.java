public class Main {
    public static void main(String[] args) {

        int n = 20;
        int[][] tablica = new int[n+1][];

        for(int i = 0; i<n+1; i++)
        tablica[i] = new int[i];

        int temp = 1;
        for(int i = 0;i<n+1;i++){
            for(int j = 0;j<tablica[i].length;j++){
                tablica[i][j] = temp;
                temp++;
            }
        }

        for(int i = 0;i<n+1;i++){
            for(int j = 0;j<tablica[i].length;j++){
                System.out.printf("%6d",tablica[i][j]);
            }
            System.out.println("");
        }
    }
}