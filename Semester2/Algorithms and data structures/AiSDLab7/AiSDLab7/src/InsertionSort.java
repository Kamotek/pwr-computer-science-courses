public class InsertionSort extends Alghorithms{
    public InsertionSort(){}

    public double execute(int[] array){


        int n = array.length;
        double preTime = System.nanoTime();
        
        for (int i = 1; i < n; ++i) {
            int current = array[i];
            int j = i - 1;
 
            while (j >= 0 && array[j] > current) {
                array[j+1] = array[j];
                j = j - 1;
            }
            array[j+1] = current;
        }
        double postTime = System.nanoTime();

        double avgTime = postTime-preTime;

        return avgTime;
 }
}
