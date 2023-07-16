public class KartaSeniora extends KartaKlienta{

    public String typ(){
        return "Karta Seniora";
    }
    public KartaSeniora(){
    }
    public KartaSeniora(int numer, String nazwisko){
        super(numer, nazwisko);
    }
    public double rabat(){
        return 0.85;
    }
}
