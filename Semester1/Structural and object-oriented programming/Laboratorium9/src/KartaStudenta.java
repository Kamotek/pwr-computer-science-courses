public class KartaStudenta extends KartaKlienta {

    public KartaStudenta(){}

    public String typ(){
        return "Karta Studenta";
    }
    public KartaStudenta(int numer, String nazwisko){
        super(numer, nazwisko);
    }
    public double rabat(){
        return 0.9;
    }
}
