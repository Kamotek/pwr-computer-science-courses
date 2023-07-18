import java.util.Comparator;

public class ComparatorSJF implements Comparator<Proces> {

    @Override
    public int compare(Proces o1, Proces o2) {
        return o1.getProcesDlugosc()-o2.getProcesDlugosc();
    }
}
