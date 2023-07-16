import java.math.BigDecimal;

public class PrzecenaWielkanocna implements Przecenianie {

    // %m.nf  <-- printf

    @Override
    public BigDecimal przecena(BigDecimal wartosc){
        return wartosc.multiply(BigDecimal.valueOf(0.9));
    }
}