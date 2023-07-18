public class App {
    public static void main(String[] args) throws Exception {
       int[] unsortedArray = {76,71, 5, 57,12,50,20,93,20,55,62,3};


       for(int i = 0; i<unsortedArray.length-1;i++){
        for(int j = 0; j<unsortedArray.length-i-1;j++){
        if(unsortedArray[j] < unsortedArray[j+1]){
            int temp = unsortedArray[j];
            unsortedArray[j] = unsortedArray[j+1];
            unsortedArray[j+1] = temp;
        }

                    for (int x : unsortedArray) {
                System.out.print(x + " ");
            }
            System.out.println();


        }
       }
    }
}
