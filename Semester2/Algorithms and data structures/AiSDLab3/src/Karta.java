
public class Karta implements Comparable<Karta>{
    private int kolor;
    private int wartosc;

    private boolean powtorzenie;

    public void setKolor(int kolor) {
        this.kolor = kolor;
    }

    public void setWartosc(int wartosc) {
        this.wartosc = wartosc;
    }

    public boolean isPowtorzenie() {
        return powtorzenie;
    }

    public void setPowtorzenie(boolean powtorzenie) {
        this.powtorzenie = powtorzenie;
    }

    @Override
    public String toString() {
        if(wartosc == 14) {return "Karta()";} else {
            return "Karta{" +
                    "kolor=" + kolor +
                    ", wartosc=" + wartosc +
                    '}';
        }
    }

    public Karta(int kolor, int wartosc, boolean powtorzenie){
        this.kolor = kolor;
        this.wartosc = wartosc;
        this.powtorzenie = powtorzenie;
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
