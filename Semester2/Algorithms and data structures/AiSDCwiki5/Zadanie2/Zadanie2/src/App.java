import java.util.Random;

public class App {

    static void random(int arr[],int low,int high)
    {
     
        Random random= new Random();

        if(arr.length<100){
            int pivot = random.nextInt(high-low)+low;
            
            int temp1=arr[pivot]; 
            arr[pivot]=arr[high];
            arr[high]=temp1;
        }else{
            int[] pivotes = {arr[random.nextInt(high-low)+low], arr[random.nextInt(high)], arr[random.nextInt(high)]};

            for(int x = 0; x<pivotes.length-1;x++){
                for(int j = 0; j<pivotes.length-x-1;j++){
                    if(pivotes[j] < pivotes[j+1]){
                        int temp = pivotes[j];
                        pivotes[j] = pivotes[j+1];
                        pivotes[j+1] = temp;
                    }

                }
            }
            int pivot = pivotes[1];

            

        }
    }

    static int partition(int[] arr, int low, int high)
    {
        
        random(arr,low,high);
        int pivot = arr[high];
        
        
        

            
 
        int i = (low - 1);
 
        for (int j = low; j <= high - 1; j++) {
 
            if (arr[j] < pivot) {
 
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void quickSort(int[] arr, int low, int high)
    {
        if (low < high) {
 

            int pi = partition(arr, low, high);
 
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    public static void main(String[] args) throws Exception {
        Random random = new Random();

        int numberOfElements = 500;
        int[] array = new int[numberOfElements];

        for(int i = 0; i<numberOfElements;i++){
            array[i] = (random.nextInt(100));
        }

        int n = array.length;


        quickSort(array, 0, n - 1);

        for(int i = 0; i<array.length;i++){
            System.out.print(array[i] + " ");
            if(i%10 == 0){System.out.println();}
        }

    }
}
