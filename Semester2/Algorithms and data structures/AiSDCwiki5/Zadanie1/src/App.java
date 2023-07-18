import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class App {

    public static void bucketSort(int[] array, int numberOfBuckets){
        if (numberOfBuckets <= 0) return;

        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(numberOfBuckets);

        for(int i = 0; i<numberOfBuckets;i++){
            buckets.add(new ArrayList<Integer>());
        }

        int max = 100;
        int min = 0;

        int range = (int) (max-min)/numberOfBuckets;

        for(int i = 0; i<array.length;i++){
            buckets.get((array[i]-min)/range).add(array[i]);
        }

        System.out.println("Number of buckets: " + numberOfBuckets);

        for (ArrayList<Integer> x : buckets) {
            System.out.println("---");
            System.out.println("Next unsorted bucket");
            System.out.println("---");
            for(int i = 0; i<x.size();i++){
                System.out.println(x.get(i));
            }
        }
        System.out.println();

        for(int i = 0; i<buckets.size();i++){
        if(i%4 == 0){bubbleSort(buckets.get(i)); continue;}
        if(i%3 == 0){insertionSort(buckets.get(i)); continue;}
        if(i%2 == 0){selectionSort(buckets.get(i)); continue;}
        quickSort(buckets.get(i), 0, buckets.get(i).size()-1);
       }

       

    

       int z = 0;
       for (ArrayList<Integer> x : buckets) {
            System.out.println("---");
            System.out.println("Next SORTED bucket + " + z++);
            System.out.println("---");
            for(int i = 0; i<x.size();i++){
                System.out.println(x.get(i) + "");
            }
       }


       int arrayIterator = 0;

       for(ArrayList<Integer> x : buckets){
        for(int i = 0; i<x.size();i++){
            array[arrayIterator++] = x.get(i);
        }
       }

    }

    public static void quickSort(ArrayList<Integer> array, int low, int high){{

            if (low < high) {
     
                int pi = partition(array, low, high);
     
                quickSort(array, low, pi - 1);
                quickSort(array, pi + 1, high);
            }
        }
    }
    public static int partition(ArrayList<Integer> array, int low, int high){
       
        int pivot = array.get(high);
 
        int i =(low - 1);
 
        for (int j = low; j <= high - 1; j++) {
 
            if (array.get(j) < pivot) {
                i++;

                int temp = array.get(i);
                array.set(i, array.get(j));
                array.set(j, temp);
            }
        }
        int temp = array.get(i+1);
        array.set(i+1, array.get(high));
        array.set(high, temp);


        return (i + 1);
    }

    


    public static void bubbleSort(ArrayList<Integer> array){

        for(int i = 0; i<array.size()-1;i++){
            for(int j = 0; j<array.size()-i-1;j++){
                if(array.get(j) > array.get(j+1)){
                    int temp = array.get(j);
                    array.set(j,array.get(j+1));
                    array.set(j+1,temp);
               }
            }
        }
    }

    public static void insertionSort(ArrayList<Integer> array){

            int n = array.size();
            for (int i = 1; i < n; ++i) {
                int current = array.get(i);
                int j = i - 1;
     
                while (j >= 0 && array.get(j) > current) {
                    array.set(j+1,array.get(j));
                    j = j - 1;
                }
                array.set(j+1, current);
            }
        
     }
    
    public static void selectionSort(ArrayList<Integer> array){
        int n = array.size();
 
        for(int i = 0; i < n-1; i++){
            int min = i;
            for (int j = i+1; j < n; j++){
                if (array.get(j) < array.get(min)){
                    min = j;
                }
            }
            int temp = array.get(min);
            array.set(min,array.get(i));
            array.set(i,temp);
            
        }
    }

    public static void main(String[] args) throws Exception {

        Random random = new Random();

        int numberOfElements = 1000;
        int[] array = new int[numberOfElements];

        for(int i = 0; i<numberOfElements;i++){
            array[i] = (random.nextInt(100));
        }

        // tu sie konczy tworzenie tablicy do posortowania;
        // ----


        int numberOfBuckets = 4;

        bucketSort(array, numberOfBuckets);

        System.out.println("Posortowane wartości: ");

        for(int i = 0; i<array.length;i++){
            System.out.print(array[i] + " ");
            if(i%10==0) System.out.println();
        }


    }
}
