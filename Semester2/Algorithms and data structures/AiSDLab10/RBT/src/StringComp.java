import java.util.Comparator;

public class StringComp implements Comparator<Obj>{

    @Override
    public int compare(Obj arg0, Obj arg1) {
        return arg0.value.charAt(0) - arg1.value.charAt(0);
    }
    
}