public class KartaPodstawowa extends KartaKlienta {

    public String typ(){
        return "Karta Podstawowa";
    }
    public KartaPodstawowa(){}
    public KartaPodstawowa(int numer, String nazwisko){
        super(numer, nazwisko);
    }
    public double rabat(){
        return 1;
    }
}
