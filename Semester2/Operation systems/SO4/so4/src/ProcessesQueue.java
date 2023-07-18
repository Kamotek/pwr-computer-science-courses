import java.util.ArrayList;
import java.util.Random;

public class ProcessesQueue {
    
    ArrayList<Proces> processArray;
    int queueLength;
    int virtualMemorySize;
    int procesOffset;
    int procesSize;


    public ProcessesQueue(int queueLength, int virtualMemorySize, int procesSize, int procesOffset){
        this.processArray = new ArrayList<>(queueLength);

      this.queueLength = queueLength;
      this.virtualMemorySize = virtualMemorySize;

      this.procesOffset = procesOffset;
      this.procesSize = procesSize;

      queueGenerator();

    }

    ProcessesQueue(ProcessesQueue another){
        this.processArray = new ArrayList<>(queueLength);

        this.queueLength = another.queueLength;
        this.virtualMemorySize = another.virtualMemorySize;
  
        this.procesOffset = another.procesOffset;
        this.procesSize = another.procesSize;

      
        for (Proces proces : another.processArray) {
            this.processArray.add(new Proces(proces));
        }
    }

    private void queueGenerator(){
        Random random = new Random();

        for(int i = 0; i<queueLength;i++){
            processArray.add(new Proces(procesSize, random.nextInt(virtualMemorySize-procesOffset)+procesOffset, procesOffset));
        }
    }

    public ArrayList<Proces> getProcessArray() {
        return processArray;
    }

    public void setProcessArray(ArrayList<Proces> processArray) {
        this.processArray = processArray;
    }

    public int getQueueLength() {
        return queueLength;
    }

    public void setQueueLength(int queueLength) {
        this.queueLength = queueLength;
    }

    public int getVirtualMemorySize() {
        return virtualMemorySize;
    }

    public void setVirtualMemorySize(int virtualMemorySize) {
        this.virtualMemorySize = virtualMemorySize;
    }

    public int getProcesOffset() {
        return procesOffset;
    }

    public void setProcesOffset(int procesOffset) {
        this.procesOffset = procesOffset;
    }

    public int getProcesSize() {
        return procesSize;
    }

    public void setProcesSize(int procesSize) {
        this.procesSize = procesSize;
    }

    
}
    

