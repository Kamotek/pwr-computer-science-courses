import java.util.Random;

public class Main {
    public static void metoda1(int[] tablica1, int[] tablica2, int[] tablica3){
        int counter = 0;
        for(int i=0;i<tablica1.length;i++){
            tablica3[counter] = tablica1[i];
            counter++;
            tablica3[counter] = tablica2[i];
        }
    }

    public static void metoda2(int[] tablica){
        int max = tablica[0];
        int[] indexy = new int[tablica.length];
        for(int i=0;i< tablica.length;i++){
            if(max<tablica[i]){
                max = tablica[i];
            }
        }
        int j = 0;
        for(int i=0; i< tablica.length;i++){
            if(tablica[i]==max){
                indexy[j] = i;
                j++;
            }
        }
    }
    public static void main(String[] args) {
        int[] tablica1 = new int[20];
        int[] tablica2 = new int[20];
        int[] tablica3 = new int[tablica1.length+tablica2.length];

        Random random = new Random();
        for(int i = 0;i<tablica1.length;i++){
            tablica1[i] = random.nextInt();
        }

        for(int i = 0;i<tablica2.length;i++){
            tablica2[i] = random.nextInt();
        }

        metoda1(tablica1, tablica2, tablica3);

        metoda2(tablica3);

    }
}