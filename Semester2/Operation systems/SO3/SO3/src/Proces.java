import java.util.ArrayList;
import java.util.Random;

public class Proces {

    ArrayList<Integer> pageReferences;

    public Proces(int processSize, int referenceIndex, int indexOffset){
        this.pageReferences = new ArrayList<>(processSize);
        Random random = new Random();

        while(processSize>0){
            pageReferences.add(random.nextInt(indexOffset*2+1)+referenceIndex-indexOffset);
            processSize--;
        }
    }

    Proces(Proces another){
        this.pageReferences = new ArrayList<>();

        for (int i : another.pageReferences) {
            this.pageReferences.add(i);
        }
    }

    public ArrayList<Integer> getPageReferences() {
        return pageReferences;
    }

    public void setPageReferences(ArrayList<Integer> pageReferences) {
        this.pageReferences = pageReferences;
    }

    

    
}
