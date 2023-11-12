import java.util.Random;

public class Klasa1 {
    private int[][] tablica;

    public Klasa1(int x, int y) {
        this.tablica = new int[y][x];
    }


    public void metoda1(int z){
        Random random = new Random();

        for(int i=0;i<this.tablica.length;i++){
            for(int j = 0;j<this.tablica[i].length;j++){
                this.tablica[i][j]=random.nextInt(z);
            }
        }


    }
    public void metoda2(){
        for(int i=0;i<this.tablica.length;i++){
            for(int j = 0;j<this.tablica[i].length;j++){
                System.out.printf("%d10", tablica[i][j]);
            }
            System.out.println();
        }
    }
}
