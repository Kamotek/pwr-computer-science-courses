import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorPracownicy implements Iterator<Pracownik> {
    private Pracownik[] pracownicy;
    private int index = 0;

    public IteratorPracownicy(Pracownik[] pracownicy){
        this.pracownicy = pracownicy;
    }

    @Override
    public boolean hasNext() {
        return index < pracownicy.length;
    }

    @Override
    public Pracownik next() {
        if(!hasNext())
            throw new NoSuchElementException();
        return pracownicy[index++];
    }
}
