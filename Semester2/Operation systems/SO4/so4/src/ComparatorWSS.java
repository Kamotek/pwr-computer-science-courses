import java.util.Comparator;

public class ComparatorWSS implements Comparator<Proces> {

    @Override
    public int compare(Proces arg0, Proces arg1) {
        return arg0.pageReferencesWSS.size() - arg1.pageReferencesWSS.size();
    }

    
}
