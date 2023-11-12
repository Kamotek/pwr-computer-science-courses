public class Transakcja{
    protected static int numerTransakcji = 0;
    protected int id;

    static
    protected double kwota;
    KartaKlienta karta;
    public Transakcja(){
        this.id = numerTransakcji;
        this.numerTransakcji++;

        this.kwota = 0;
        this.karta = null;
    }
    public Transakcja(double kwota, KartaKlienta karta){
        this.id = numerTransakcji;
        this.numerTransakcji++;
        this.kwota = kwota;
        this.karta = karta;
    }

    public double kwotaDoZaplaty(){
        if(karta!=null)return kwota*karta.rabat();
        return kwota;
    }

    @Override
    public String toString() {
        return "Transakcja [" +
                "kwota=" + kwotaDoZaplaty() +
                ", karta=" + karta.typ() +
                " numerTransakcji=" + id + ']';
    }
}
