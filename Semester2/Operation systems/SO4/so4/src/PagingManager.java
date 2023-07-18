import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;

public class PagingManager {

    int[] ramToVirtualMemoryIndexTranslator;
    int[] pageTemp; //jego zastosowania beda sie roznic w zaleznosci od algorytmu
    int pageCounter;
    int ramMemorySize;
    int virtualMemorySize;
    int algorithm;
    ArrayList<Proces> queue;
    ArrayList<Integer> emptySpaces;
    int thrashing;
    int tempThrash;

    int pageFaults;

    RAM ramMemory;
    VirtualMemory virtualMemory;

    LinkedHashSet<Integer> uniqueNumberSet = new LinkedHashSet<>();
    ArrayList<Integer> uniqueNumberArray = new ArrayList<>();

    public PagingManager(int ramSize, int virtualMemorySize, ArrayList<Proces> queue, int algorithm){
        this.ramToVirtualMemoryIndexTranslator = new int[ramSize];
        this.pageTemp = new int[ramSize];

        this.ramMemory = new RAM(ramSize);
        this.virtualMemory = new VirtualMemory(virtualMemorySize);
        

        this.pageCounter = 0;
        this.pageFaults = 0;

        this.thrashing = 0;
        this.tempThrash = 0;

        this.ramMemorySize = ramSize;
        this.virtualMemorySize = virtualMemorySize;
        this.queue = queue;
        this.algorithm = algorithm;

        this.emptySpaces = new ArrayList<>();

        if(algorithm == 1) EqualLRU();
        if(algorithm == 2) PropLRU();
        if(algorithm == 3) FaultControlLRU();
        if(algorithm == 4) ZonalLRU();
    }

    public void ZonalLRU(){
        int procesSwap = 0;

        ArrayList<Integer> indexesQueue = new ArrayList<>();
        ArrayList<Proces> ProcesWSS = new ArrayList<>();
        HashSet<Integer> unicalSum = new HashSet<>();

        int pageReferencesAmount = 0;
        int processesAmount = queue.size();
        int counter = 0;

        for (Proces p : queue) {
            pageReferencesAmount+=p.getPageReferences().size();
        }

        int workingSetSizeSum = 0;

        while(!queue.isEmpty()){
            workingSetSizeSum +=unicalSum.size();
            ProcesWSS.add(new Proces(queue.get(0)));
            if(ProcesWSS.size()>1){
            Collections.sort(ProcesWSS, new ComparatorWSS());
            }
            unicalSum.clear();
            
            if(workingSetSizeSum >= ramMemorySize){
                killProcess(ProcesWSS.get(0));
                procesSwap++;
            }
            while(!queue.get(0).pageReferences.isEmpty()){
                unicalSum.add(queue.get(0).pageReferences.get(0));
                counter++;
                if(pageCounter == ramMemorySize-1){
                    if(pageScan(queue.get(0).pageReferences.get(0))){
                        tempThrash++;
                        if(tempThrash ==  ramMemorySize/2) {tempThrash=0;thrashing++;}
                        if(!uniqueNumberArray.isEmpty()){
                            int tempp =getRamIndex(uniqueNumberArray.get(0));
                            ramMemory.memoryFrame[tempp] = queue.get(0).pageReferences.get(0);
                            uniqueNumberArray.remove(0);

                            

                        }
                        if(uniqueNumberArray.isEmpty())
                        pageFaults++;
                       ramMemory.memoryFrame[indexesQueue.get(0)] = queue.get(0).pageReferences.get(0);
                       indexesQueue.add(indexesQueue.get(0));
                       indexesQueue.remove(0);

                    }
                    queue.get(0).pageReferences.remove(0);
                    indexesQueue.add(indexesQueue.get(0));
                    indexesQueue.remove(0);
                    pageReferencesAmount--;

                }
                else{
                    if(pageScan(queue.get(0).pageReferences.get(0))){
                        ramMemory.memoryFrame[pageCounter++] = queue.get(0).pageReferences.get(0);
                        indexesQueue.add(pageCounter);
                    }
                    queue.get(0).pageReferences.remove(0);
                    indexesQueue.add(indexesQueue.get(0));
                    indexesQueue.remove(0);
                    pageReferencesAmount--;
                }

            }
            if(!queue.get(0).pageReferences.isEmpty()) {queue.add(queue.get(0)); queue.remove(0);  procesSwap++;}
            if(queue.get(0).pageReferences.isEmpty()){queue.remove(0);processesAmount--;}
            counter = 0;
        }
        System.out.println("Ilość błędów strony dla algorytmu ZonqlLRU: "+pageFaults);
        System.out.println("Ilosc zmian procesu dla algorytmu ZonalLRU: "+procesSwap);
        System.out.println("Ilosc wystapien zjawiska thrashingu dla algorytmu ZonalLRU " + thrashing);


    }

    public void killProcess(Proces proces){
        for (int i : proces.pageReferencesWSS) {
            for(int j = 0; i<ramMemorySize;i++){
                if(ramMemory.memoryFrame[j] == i){
                    uniqueNumberArray.add(ramMemory.memoryFrame[j]);
                }
            }
        }
    }

    public int getRamIndex(int x){
        for(int i = 0; i<ramMemorySize;i++){
            if(ramMemory.memoryFrame[i] == x){return i;}
        }
        return 0;
    }


    public void FaultControlLRU(){

        int  procesSwap = 0;
        ArrayList<Integer> indexesQueue = new ArrayList<>();

        int pageReferencesAmount = 0;
        int processesAmount = queue.size();
        int counter = 0;
        for (Proces p : queue) {
            pageReferencesAmount+=p.getPageReferences().size();
        }

        int upLimit = 5;
        int downLimit = 5;
        int tempCounter = 0;
        int tempTime = 0;

        while(!queue.isEmpty()){

            while(!queue.get(0).pageReferences.isEmpty() && counter < pageReferencesAmount/(processesAmount)){
                tempTime++;
                counter++;

                if(tempTime == 10){
                    if((tempCounter)<downLimit) {counter--;}
                    if((tempCounter)>upLimit) {counter++;}
                    tempCounter = 0; tempTime = 0;
                }
                if(pageCounter == ramMemorySize-1){
                    if(pageScan(queue.get(0).pageReferences.get(0))){

                       tempThrash++;
                       tempCounter++;
                       if(tempThrash ==  ramMemorySize/2) {tempThrash=0;thrashing++;}
                     
                        pageFaults++;

                       ramMemory.memoryFrame[indexesQueue.get(0)] = queue.get(0).pageReferences.get(0);
                       indexesQueue.add(indexesQueue.get(0));
                       indexesQueue.remove(0);
                        
                    }
                    queue.get(0).pageReferences.remove(0);
                    indexesQueue.add(indexesQueue.get(0));
                    indexesQueue.remove(0);
                    pageReferencesAmount--;

                }
                else{
                    if(pageScan(queue.get(0).pageReferences.get(0))){
                        ramMemory.memoryFrame[pageCounter++] = queue.get(0).pageReferences.get(0);
                        indexesQueue.add(pageCounter);
                    }
                    queue.get(0).pageReferences.remove(0);
                    indexesQueue.add(indexesQueue.get(0));
                    indexesQueue.remove(0);
                    pageReferencesAmount--;
                }
            }
            if(!queue.get(0).pageReferences.isEmpty()) {queue.add(queue.get(0)); queue.remove(0); procesSwap++;}
            if(queue.get(0).pageReferences.isEmpty()){queue.remove(0); processesAmount--;}
            counter = 0;
        }
        System.out.println("Ilość błędów strony dla algorytmu FaultControlLRU: "+pageFaults);
        System.out.println("Ilosc zmian procesu dla algorytmu FaultControlLRU: "+procesSwap);
        System.out.println("Ilosc wystapien zjawiska thrashingu dla algorytmu FaultControlLRU " + thrashing);

    }

    public void PropLRU(){
        int procesSwap = 0;

        ArrayList<Integer> indexesQueue = new ArrayList<>();

        int pageReferencesAmount = 0;
        int processesAmount = queue.size();
        int counter = 0;

        for (Proces p : queue) {
            pageReferencesAmount+=p.getPageReferences().size();
        }



        while(!queue.isEmpty()){
         
            while(!queue.get(0).pageReferences.isEmpty() && counter < pageReferencesAmount/(processesAmount)){
                counter++;
                if(pageCounter == ramMemorySize-1){
                    if(pageScan(queue.get(0).pageReferences.get(0))){
                        tempThrash++;
                        if(tempThrash ==  ramMemorySize/2) {tempThrash=0;thrashing++;}
                      
                     
                        pageFaults++;

                       ramMemory.memoryFrame[indexesQueue.get(0)] = queue.get(0).pageReferences.get(0);
                       indexesQueue.add(indexesQueue.get(0));
                       indexesQueue.remove(0);

                    }
                    queue.get(0).pageReferences.remove(0);
                    indexesQueue.add(indexesQueue.get(0));
                    indexesQueue.remove(0);
                    pageReferencesAmount--;

                }
                else{
                    if(pageScan(queue.get(0).pageReferences.get(0))){
                        ramMemory.memoryFrame[pageCounter++] = queue.get(0).pageReferences.get(0);
                        indexesQueue.add(pageCounter);
                    }
                    queue.get(0).pageReferences.remove(0);
                    indexesQueue.add(indexesQueue.get(0));
                    indexesQueue.remove(0);
                    pageReferencesAmount--;
                }

            }
            if(!queue.get(0).pageReferences.isEmpty()) {queue.add(queue.get(0)); queue.remove(0);  procesSwap++;}
            if(queue.get(0).pageReferences.isEmpty()){queue.remove(0);processesAmount--;}
            counter = 0;
        }
        System.out.println("Ilość błędów strony dla algorytmu PropLRU: "+pageFaults);
        System.out.println("Ilosc zmian procesu dla algorytmu PropRU: "+procesSwap);
        System.out.println("Ilosc wystapien zjawiska thrashingu dla algorytmu PropLRU " + thrashing);


    }

    

    public void EqualLRU(){

        int  procesSwap = 0;
        ArrayList<Integer> indexesQueue = new ArrayList<>();

        int pageReferencesAmount = 0;
        int processesAmount = queue.size();
        int counter = 0;
        for (Proces p : queue) {
            pageReferencesAmount+=p.getPageReferences().size();
        }



        while(!queue.isEmpty()){

            while(!queue.get(0).pageReferences.isEmpty() && counter < ramMemorySize){
                counter++;
                if(pageCounter == ramMemorySize-1){
                    if(pageScan(queue.get(0).pageReferences.get(0))){

                       tempThrash++;
                       if(tempThrash ==  ramMemorySize/2) {tempThrash=0;thrashing++;}
                     
                        pageFaults++;

                       ramMemory.memoryFrame[indexesQueue.get(0)] = queue.get(0).pageReferences.get(0);
                       indexesQueue.add(indexesQueue.get(0));
                       indexesQueue.remove(0);
                        
                    }
                    queue.get(0).pageReferences.remove(0);
                    indexesQueue.add(indexesQueue.get(0));
                    indexesQueue.remove(0);
                    pageReferencesAmount--;

                }
                else{
                    if(pageScan(queue.get(0).pageReferences.get(0))){
                        ramMemory.memoryFrame[pageCounter++] = queue.get(0).pageReferences.get(0);
                        indexesQueue.add(pageCounter);
                    }
                    queue.get(0).pageReferences.remove(0);
                    indexesQueue.add(indexesQueue.get(0));
                    indexesQueue.remove(0);
                    pageReferencesAmount--;
                }
            }
            if(!queue.get(0).pageReferences.isEmpty()) {queue.add(queue.get(0)); queue.remove(0); procesSwap++;}
            if(queue.get(0).pageReferences.isEmpty()){queue.remove(0); processesAmount--;}
            counter = 0;
        }
        System.out.println("Ilość błędów strony dla algorytmu EqualLRU: "+pageFaults);
        System.out.println("Ilosc zmian procesu dla algorytmu EqualLRU: "+procesSwap);
        System.out.println("Ilosc wystapien zjawiska thrashingu dla algorytmu EqualLRU " + thrashing);

    }




    public boolean pageScan(int pageReference){
        for(int i = 0; i<ramMemorySize;i++){
            if(ramMemory.memoryFrame[i] == pageReference) return false;
        }

        return true;
    }


}
