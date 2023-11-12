public class Prostopadloscian extends Bryla {
    private int a;
    private int b;

    public Prostopadloscian() {
        this.a = 1;
        this.b = 1;

    }

    public Prostopadloscian(String nazwa, int wysokoscH, int a, int b) {
        super(nazwa, wysokoscH);
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    @Override
    public double Objetosc() {
        return a*b*super.getWysokoscH(); // Nie trzeba tutaj wskazywać rodzica, ale tak wyglada to czytelniej, mozna by tez uzyc protected i by nie trzeba bylo uzywac gettera
    }
    @Override
    public double Pole() {
        return (a*b*2) + (a*getWysokoscH()*2) + (b*getWysokoscH()*2);
    }
    public boolean jestSzescianem(){
        return ((a==b) & (a==getWysokoscH()));
    }

    @Override
    public String toString() {
        return "Nazwa: " + getNazwa() + " [a=" + a + " b=" + b + "]";
    }
}
