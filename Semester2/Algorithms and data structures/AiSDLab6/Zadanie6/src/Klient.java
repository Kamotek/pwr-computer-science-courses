public class Klient {
    private String nazwaKlienta;
    private ListQueue<Zamowienie> kolejkaZamowien;
    
    public Klient(String nazwaKlienta) {
        this.nazwaKlienta = nazwaKlienta;
        this.kolejkaZamowien = new ListQueue<>();
    }
    public String getNazwaKlienta() {
        return nazwaKlienta;
    }
    public void setNazwaKlienta(String nazwaKlienta) {
        this.nazwaKlienta = nazwaKlienta;
    }
    public ListQueue<Zamowienie> getKolejkaZamowien() {
        return kolejkaZamowien;
    }
    public void setKolejkaZamowien(ListQueue<Zamowienie> kolejkaZamowien) {
        this.kolejkaZamowien = kolejkaZamowien;
    }

    

    
}
