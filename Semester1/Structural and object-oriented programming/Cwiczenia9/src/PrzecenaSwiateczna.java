import java.math.BigDecimal;

public class PrzecenaSwiateczna implements Przecenianie{

    @Override
        public BigDecimal przecena(BigDecimal wartosc){
        return wartosc.multiply(BigDecimal.valueOf(0.9));
    }
}

