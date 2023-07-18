import java.text.DecimalFormat;
import java.util.Random;

public class App {

    private static final DecimalFormat df = new DecimalFormat("0.000000");

    public static int[] prepareArray(int parameter, int length){


        int[] array = new int[length];
        Random random = new Random();

        if(parameter == 0){
            for(int i = 0; i<length;i++){
                array[i] = random.nextInt(length);
            }
        }
        if(parameter == 1){
            for(int i = 0; i<length;i++){
                array[i] = i;
            }
        }
        if(parameter == 2){
            for(int i = 0; i<length;i++){
                array[i] = length - i;
            }
        }

        return array;
    }



    public static void quickSort(int[] array, int low, int high){

        if (low < high) {
 
            int pi = partition(array, low, high);
 
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }
    
    public static int partition(int[] array, int low, int high){
    
        int pivot = array[high];

        int i =(low - 1);

        for (int j = low; j <= high - 1; j++) {

            if (array[j] < pivot) {
                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i+1];
        array[i+1] = array[high];
        array[high] = temp;


        return (i + 1);
    }


    //
 
    public static void merge(int arr[], int l, int m, int r){
        int n1 = m - l + 1;
        int n2 = r - m;
 
        int L[] = new int[n1];
        int R[] = new int[n2];
 
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
 
 
        int i = 0, j = 0;
 
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
 
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
 
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
 
    public static void mergeSort(int arr[], int l, int r){
        if (l < r) {
            int m = l + (r - l) / 2;
 
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
 
            merge(arr, l, m, r);
        }
    }
 

    public static void executeAlgorithm(Alghorithms x, String name){

        int length = 100000;
        double avgTimeRandom = x.execute(prepareArray(0, length));
        double avgTimeGrowing = x.execute(prepareArray(1, length));
        double avgTimeShrinking = x.execute(prepareArray(2, length));


        System.out.println("");
        System.out.println("---");
        System.out.println("Czas posortowania jednej nieposortowanej tablicy dla algorytmu " + name + " wynosi: " + df.format(avgTimeRandom/1000000000));
        System.out.println("Czas posortowania jednej posortowanej rosnąco tablicy dla algorytmu " + name + " wynosi: "  + df.format(avgTimeGrowing/1000000000));
        System.out.println("Czas posortowania jednej posortowanej malejąco tablicy dla algorytmu " + name + " wynosi: " +  df.format(avgTimeShrinking/1000000000));
        System.out.println("---");
        System.out.println("");
    }



    public static void main(String[] args) throws Exception {



        InsertionSort insertion = new InsertionSort();

        executeAlgorithm(insertion,"InsertionSort");

        SelectionSort selection =  new SelectionSort();

        executeAlgorithm(selection, "SelectionSort");

        BubbleSort bubble = new BubbleSort();

        executeAlgorithm(bubble,"BubbleSort");

        HeapSort heap = new HeapSort();

        executeAlgorithm(heap, "HeapSort");


        System.out.println("");
        System.out.println("---");
        double quickSortPreTime = System.nanoTime();
        quickSort(prepareArray(0, 100000), 0, 5000);
        double quickSortPostTime = System.nanoTime();
        double quickSortAvgTime = quickSortPostTime-quickSortPreTime;
        System.out.println("Czas posortowania jednej nieposortowanej tablicy dla algorytmu QuickSort wynosi: " + df.format(quickSortAvgTime/1000000000));

        quickSortPreTime = System.nanoTime();
        quickSort(prepareArray(1, 100000), 0, 5000);
        quickSortPostTime = System.nanoTime();
        quickSortAvgTime = quickSortPostTime-quickSortPreTime;
        System.out.println("Czas posortowania jednej posortowanej rosnąco tablicy dla algorytmu QuickSort wynosi: " + df.format(quickSortAvgTime/1000000000));

        quickSortPreTime = System.nanoTime();
        quickSort(prepareArray(2, 100000), 0, 5000);
        quickSortPostTime = System.nanoTime();
        quickSortAvgTime = quickSortPostTime-quickSortPreTime;
        System.out.println("Czas posortowania jednej posortowanej malejąco tablicy dla algorytmu QuickSort wynosi: " + df.format(quickSortAvgTime/1000000000));
        System.out.println("---");
        System.out.println("");
   
         System.out.println("");
         System.out.println("---");
         double mergeSortPreTime = System.nanoTime();
         mergeSort(prepareArray(0, 100000), 0, 99999);
         double mergeSortPostTime = System.nanoTime();
         double mergeSortAvgTime = mergeSortPostTime-mergeSortPreTime;
         System.out.println("Czas posortowania jednej nieposortowanej tablicy dla algorytmu MergeSort wynosi: " + df.format(mergeSortAvgTime/1000000000));
 
         mergeSortPreTime = System.nanoTime();
         mergeSort(prepareArray(1, 100000), 0, 99999);
         mergeSortPostTime = System.nanoTime();
         mergeSortAvgTime = mergeSortPostTime-mergeSortPreTime;
         System.out.println("Czas posortowania jednej posortowanej rosnąco tablicy dla algorytmu MergeSort wynosi: " + df.format(mergeSortAvgTime/1000000000));
 
         mergeSortPreTime = System.nanoTime();
         mergeSort(prepareArray(2, 100000), 0, 99999);
         mergeSortPostTime = System.nanoTime();
         mergeSortAvgTime = mergeSortPostTime-mergeSortPreTime;
         System.out.println("Czas posortowania jednej posortowanej malejąco tablicy dla algorytmu MergeSort wynosi: " + df.format(mergeSortAvgTime/1000000000));
         System.out.println("---");
         System.out.println("");




    


   
    }

    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}

