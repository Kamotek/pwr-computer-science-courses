import java.util.ArrayList;
import java.util.Collections;

public class ProcessQueueGenerator {
    
    int procesQueueSize;
    ArrayList<Proces> procesQueue;
    ArrayList<Boolean> procesSpawnRate;

    int newProcessOccurance;







    public ProcessQueueGenerator(int procesQueueSize, int newProcessOccurance) {
        this.procesQueueSize = procesQueueSize;
        this.procesQueue = new ArrayList<>();
        this.procesSpawnRate = new ArrayList<>();
        this.newProcessOccurance = newProcessOccurance;
    }







    public void boolArrayGenerator(){


        for(int i = 0; i<100;i++)
            procesSpawnRate.add(false);

        for(int i = 0; i< newProcessOccurance; i++)
            procesSpawnRate.set(i, true);

        Collections.shuffle(procesSpawnRate);

        for (Boolean b : procesSpawnRate) {
            System.out.println(b);
        }

    }

}
