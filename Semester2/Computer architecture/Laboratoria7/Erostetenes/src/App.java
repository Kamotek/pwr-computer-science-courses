public class App {
    public static void main(String[] args) throws Exception {


        long preTime = System.nanoTime();
        int n = 60;
        int[] array = new int[60];
        int[] primes = new int[60];

        for(int i = 0; i<n; i++){
            array[i] = i;
        }

        for(int i = 0;i<n/2;i++){
            for(int j = 0; j < n; j++){
                if(array[i]!=1 && array[i] != array[j] && array[i]!= 0 && array[j] !=0){
                    if(array[j]%array[i]==0){
                        array[j] = 0;
                    }
                }
            }
        }

        int j = 0;
        for(int i = 0; i<n; i++){
           if(array[i]!=0){
            primes[j] = array[i];
            j++;
           }
        }
        long postTime = System.nanoTime();

        System.out.println("Liczb pierwszych jest: " + j);

        for(int i = 0;i<j;i++){
            System.out.println(primes[i]);
        } 

        System.out.println("Czas wykonania tego algorytmu wyniósł " + (postTime-preTime) + " nanosekund");
    }
}
