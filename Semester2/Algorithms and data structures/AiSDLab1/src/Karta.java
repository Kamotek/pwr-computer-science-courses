
public class Karta implements Comparable<Karta>{
    private int kolor;
    private int wartosc;

    @Override
    public String toString() {
        return "Karta{" +
                "kolor=" + kolor +
                ", wartosc=" + wartosc +
                '}';
    }

    public Karta(int kolor, int wartosc){
        this.kolor = kolor;
        this.wartosc = wartosc;
    }

    public int getKolor() {
        return kolor;
    }

    public int getWartosc() {
        return wartosc;
    }

    @Override
    public int compareTo(Karta o) {
        String temp = Integer.toString(o.wartosc)+Integer.toString(o.kolor);
        String temp2 = Integer.toString(this.wartosc)+Integer.toString(this.kolor);
        return temp2.compareTo(temp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Karta karta = (Karta) o;

        if (getKolor() != karta.getKolor()) return false;
        return getWartosc() == karta.getWartosc();
    }

}
