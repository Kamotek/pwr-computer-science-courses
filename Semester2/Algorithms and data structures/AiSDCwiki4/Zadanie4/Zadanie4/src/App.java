import java.util.Arrays;

public class App {
    public static void main(String[] args) throws Exception {
      
        int[] unsortedArray = {76,71, 5, 57,12,50,20,93,20,55,62,3};
        int[] sortedArray = new int[unsortedArray.length];
        boolean[] isUsed = new boolean[unsortedArray.length];
        Arrays.fill(isUsed, false);


        int j = unsortedArray.length;

        int max = 0;

        for (int i : unsortedArray) {
            if(i>max) max = i;
        }
        while(j>0){
            int min =  max;
            int minIndex = 0;
            for(int i = 0;i<sortedArray.length;i++){
                if(unsortedArray[i]<min && !isUsed[i]){
                    min = unsortedArray[i];
                    minIndex = i;
                }
            }
            isUsed[minIndex] = true;
            sortedArray[--j] = min;

            for (int i : sortedArray) {
                System.out.print(i + " ");
             }
             System.out.println();
         }

         
    /*     int[] unsortedArray = {76,71, 5, 57,12,50,20,93,20,55,62,3};
         int length = unsortedArray.length-1;

         int min = 0;
         int swap = 0;
         int index = 0;
         int max = 0;

         for (int i : unsortedArray) {
            if(max < i)
                max = i;
         }
        for(int slot = unsortedArray.length; slot>0;slot--){
            min = max;

            for(int i = 0;i<=length;i++){
                if(unsortedArray[i] < min){
                    min = unsortedArray[i];
                    index = i;
                }
            }
            swap = unsortedArray[length];
            unsortedArray[length] = min;
            unsortedArray[index] = swap;
            length--;
            for (int i : unsortedArray) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
*/

        
    }
}
