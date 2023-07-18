public class BubbleSort extends Alghorithms{
    public BubbleSort(){}

    public double execute(int[] array){

        int n = array.length;

        double preTime = System.nanoTime();
        for(int i = 0; i<n-1;i++){
            for(int j = 0; j<n-i-1;j++){
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        double postTime = System.nanoTime();

        double avgTime = postTime-preTime;

        return avgTime;
    }
}
