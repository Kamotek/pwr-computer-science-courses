import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        
        int[] unsortedArray = {76,71, 5, 57,12,50,20,93,20,55,62,3};
    
/*
        for (int i = 0; i < unsortedArray.length; i++) {
            int current = unsortedArray[i];
            int j = i - 1;

            while (j >= 0 && unsortedArray[j] < current) {
                unsortedArray[j + 1] = unsortedArray[j];
                j = j - 1;
            }
            unsortedArray[j + 1] = current;
            
            for (int x : unsortedArray) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
*/

        for (int i = unsortedArray.length-1; i >= 0; i--) {
            int current = unsortedArray[i];
            int j = i + 1;

            while (j <= unsortedArray.length-1 && unsortedArray[j] > current) {
                unsortedArray[j - 1] = unsortedArray[j];
                j = j + 1;
            }
            unsortedArray[j - 1] = current;
            
            for (int x : unsortedArray) {
                System.out.print(x + " ");
            }
            System.out.println();
        }

      }

}
