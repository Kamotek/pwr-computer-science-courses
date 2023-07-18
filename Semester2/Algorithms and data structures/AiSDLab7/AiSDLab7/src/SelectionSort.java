public class SelectionSort extends Alghorithms{

public SelectionSort(){}

public double execute(int[] array){
        
    int n = array.length;
    double preTime = System.nanoTime();

    for(int i = 0; i < n-1; i++){
        int min = i;
        for (int j = i+1; j < n; j++){
            if (array[j] < array[min]){
                min = j;
            }
        }
        int temp = array[min];
        array[min] = array[i];
        array[i] = temp;
    }
    double postTime = System.nanoTime();

    double avgTime = postTime-preTime;


    return avgTime;
}

    
}
