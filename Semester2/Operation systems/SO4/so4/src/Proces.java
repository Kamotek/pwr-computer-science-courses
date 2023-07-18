import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Proces {

    ArrayList<Integer> pageReferences;
    HashSet<Integer> pageReferencesWSS;
    

    public Proces(int processSize, int referenceIndex, int indexOffset){
        this.pageReferences = new ArrayList<>(processSize);
        this.pageReferencesWSS = new HashSet<>();
        Random random = new Random();

        while(processSize>0){
            pageReferences.add(random.nextInt(indexOffset*2+1)+referenceIndex-indexOffset);
            processSize--;
        }
        for (int i : pageReferences) {
            pageReferencesWSS.add(i);
        }
    }

    Proces(Proces another){
        this.pageReferences = new ArrayList<>();
        this.pageReferencesWSS = new HashSet<>();

        for (int i : another.pageReferences) {
            this.pageReferences.add(i);
            this.pageReferencesWSS.add(i);
        }
    }

    public ArrayList<Integer> getPageReferences() {
        return pageReferences;
    }

    public void setPageReferences(ArrayList<Integer> pageReferences) {
        this.pageReferences = pageReferences;
    }

    

    
}
