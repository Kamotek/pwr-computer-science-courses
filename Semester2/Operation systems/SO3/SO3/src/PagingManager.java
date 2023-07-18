import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Random;

public class PagingManager {

    int[] ramToVirtualMemoryIndexTranslator;
    int[] pageTemp; //jego zastosowania beda sie roznic w zaleznosci od algorytmu
    int pageCounter;
    int ramMemorySize;
    int virtualMemorySize;
    int usedAlgorithm;
    ArrayList<Proces> queue;

    int pageFaults;

    RAM ramMemory;
    VirtualMemory virtualMemory;

    LinkedHashSet<Integer> uniqueNumberSet = new LinkedHashSet<>();
    ArrayList<Integer> uniqueNumberArray = new ArrayList<>();

    public PagingManager(int ramSize, int virtualMemorySize, int usedAlgorithm, ArrayList<Proces> queue){
        this.ramToVirtualMemoryIndexTranslator = new int[ramSize];
        this.pageTemp = new int[ramSize];

        this.ramMemory = new RAM(ramSize);
        this.virtualMemory = new VirtualMemory(virtualMemorySize);
        

        this.pageCounter = 0;
        this.pageFaults = 0;

        this.ramMemorySize = ramSize;
        this.virtualMemorySize = virtualMemorySize;
        this.usedAlgorithm = usedAlgorithm;
        this.queue = queue;
        
        if(usedAlgorithm == 1){RANDOM();}
        if(usedAlgorithm == 2){FIFO();}
        if(usedAlgorithm == 3){OPT();}
        if(usedAlgorithm == 4){LRU();}
        if(usedAlgorithm == 5){ALRU();}
    }

    public void ALRU(){
        ArrayList<Integer> indexesQueue = new ArrayList<>();
        ArrayList<Boolean> secondLife = new ArrayList<>();


        int tempVar = 0;
        int iterator = 0;

        while(!queue.isEmpty()){
            while(!queue.get(0).pageReferences.isEmpty()){
                if(pageCounter == ramMemorySize-1){
                    if(pageScan(queue.get(0).pageReferences.get(0))){
                     
                        pageFaults++;
                        if(!secondLife.get(0)){
                            ramMemory.memoryFrame[indexesQueue.get(0)] = queue.get(0).pageReferences.get(0);
                            indexesQueue.add(indexesQueue.get(0));
                            indexesQueue.remove(0);
                            secondLife.remove(0);
                            secondLife.add(true);
                        }else{
                            secondLife.add(false);
                            secondLife.remove(0);
                            indexesQueue.add(indexesQueue.get(0));
                            indexesQueue.remove(0);
                        }

                    }
                    queue.get(0).pageReferences.remove(0);
                    indexesQueue.add(indexesQueue.get(0));
                    indexesQueue.remove(0);

                }
                else{
                    if(pageScan(queue.get(0).pageReferences.get(0))){
                        ramMemory.memoryFrame[pageCounter++] = queue.get(0).pageReferences.get(0);
                        indexesQueue.add(pageCounter);
                        secondLife.add(true);
                    }
                    queue.get(0).pageReferences.remove(0);
                    indexesQueue.add(indexesQueue.get(0));
                    indexesQueue.remove(0);
                }
            }queue.remove(0);
        }
      



        System.out.println("Ilość błędów strony dla algorytmu A-LRU: "+pageFaults);

    }

    public void LRU(){

        ArrayList<Integer> indexesQueue = new ArrayList<>();

        while(!queue.isEmpty()){
            while(!queue.get(0).pageReferences.isEmpty()){
                if(pageCounter == ramMemorySize-1){
                    if(pageScan(queue.get(0).pageReferences.get(0))){
                     
                        pageFaults++;

                       ramMemory.memoryFrame[indexesQueue.get(0)] = queue.get(0).pageReferences.get(0);
                       indexesQueue.add(indexesQueue.get(0));
                       indexesQueue.remove(0);

                    }
                    queue.get(0).pageReferences.remove(0);
                    indexesQueue.add(indexesQueue.get(0));
                    indexesQueue.remove(0);

                }
                else{
                    if(pageScan(queue.get(0).pageReferences.get(0))){
                        ramMemory.memoryFrame[pageCounter++] = queue.get(0).pageReferences.get(0);
                        indexesQueue.add(pageCounter);
                    }
                    queue.get(0).pageReferences.remove(0);
                    indexesQueue.add(indexesQueue.get(0));
                    indexesQueue.remove(0);
                }
            }queue.remove(0);
        }
        System.out.println("Ilość błędów strony dla algorytmu LRU: "+pageFaults);

    }

    public void OPT(){

        ArrayList<Integer> allPages = new ArrayList<>();

        for (Proces p : queue) {
            for (int i : p.pageReferences) {
                allPages.add(i);
            }
        }

        int size = allPages.size();

        for (int i = 0; i<size;i++) {
            if(pageCounter==ramMemorySize-1){
                if(pageScan(allPages.get(0))){
                    optUpdate(allPages);
                    ramMemory.memoryFrame[lookInRam()] = allPages.get(0);
                    pageFaults++;
                }
                allPages.remove(0);
            }
            else{
                if(pageScan(allPages.get(0))){
                    ramMemory.memoryFrame[pageCounter++] = allPages.get(0);
                }        
                allPages.remove(0);


            }
        }x();



        System.out.println("Ilość błędów strony dla algorytmu OPT: "+pageFaults);
    }

    private int lookInRam(){
        for (int leastUsedFuture : uniqueNumberArray) {
            for(int i = 0; i<ramMemorySize;i++){
                if(ramMemory.memoryFrame[i]==leastUsedFuture) return i;
            }
        }
        return 0;
    }

    private void optUpdate(ArrayList<Integer> allPages){


       uniqueNumberSet.clear();
       uniqueNumberArray.clear();

        for (int i : allPages) {
         uniqueNumberSet.add(i);   
        }

        for (int i : uniqueNumberSet) {
            uniqueNumberArray.add(i);
        }


        Collections.reverse(uniqueNumberArray);
    }


    private void FIFO(){

        ArrayList<Integer> indexesQueue = new ArrayList<>();

        while(!queue.isEmpty()){
            while(!queue.get(0).pageReferences.isEmpty()){
                if(pageCounter == ramMemorySize-1){
                    if(pageScan(queue.get(0).pageReferences.get(0))){
                     
                        pageFaults++;

                       ramMemory.memoryFrame[indexesQueue.get(0)] = queue.get(0).pageReferences.get(0);
                       indexesQueue.add(indexesQueue.get(0));
                       indexesQueue.remove(0);

                    }
                    queue.get(0).pageReferences.remove(0);

                }
                else{
                    if(pageScan(queue.get(0).pageReferences.get(0))){
                        ramMemory.memoryFrame[pageCounter++] = queue.get(0).pageReferences.get(0);
                        indexesQueue.add(pageCounter);
                    }
                    queue.get(0).pageReferences.remove(0);
                }
            }queue.remove(0);
        }
        System.out.println("Ilość błędów strony dla algorytmu FIFO: "+pageFaults);

    }
    private void x(){
        pageFaults/=2;
    }
    
    private void RANDOM(){
        int tempPointer = 0;
        Random random = new Random();

        while(!queue.isEmpty()){
            while(!queue.get(0).pageReferences.isEmpty()){
                if(pageCounter == ramMemorySize-1){
            
                    tempPointer = random.nextInt(ramMemorySize-1);

                    if(pageScan(queue.get(0).pageReferences.get(0))){
                        ramMemory.memoryFrame[tempPointer] =  queue.get(0).pageReferences.get(0);
                        pageFaults++;
                    }
                    queue.get(0).pageReferences.remove(0);
                }
                else{
                    if(pageScan(queue.get(0).pageReferences.get(0))){
                        ramMemory.memoryFrame[pageCounter++] = queue.get(0).pageReferences.get(0);
                    }
                    queue.get(0).pageReferences.remove(0);
                }
            }queue.remove(0);
        }


        System.out.println("Ilość błędów strony dla algorytmu Random: "+pageFaults);
    }


    public boolean pageScan(int pageReference){
        for(int i = 0; i<ramMemorySize;i++){
            if(ramMemory.memoryFrame[i] == pageReference) return false;
        }

        return true;
    }


}
