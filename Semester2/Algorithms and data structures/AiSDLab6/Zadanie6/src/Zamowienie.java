public class Zamowienie {
    private String nazwaTowaru;
    private int liczbaSztuk;
    private double cenaJednostkowa;
    public Zamowienie(String nazwaTowaru, int liczbaSztuk, double cenaJednostkowa) {
        this.nazwaTowaru = nazwaTowaru;
        this.liczbaSztuk = liczbaSztuk;
        this.cenaJednostkowa = cenaJednostkowa;
    }
    public String getNazwaTowaru() {
        return nazwaTowaru;
    }
    public void setNazwaTowaru(String nazwaTowaru) {
        this.nazwaTowaru = nazwaTowaru;
    }
    public int getLiczbaSztuk() {
        return liczbaSztuk;
    }
    public void setLiczbaSztuk(int liczbaSztuk) {
        this.liczbaSztuk = liczbaSztuk;
    }
    public double getCenaJednostkowa() {
        return cenaJednostkowa;
    }
    public void setCenaJednostkowa(double cenaJednostkowa) {
        this.cenaJednostkowa = cenaJednostkowa;
    }

    
}