public class Walec extends Bryla{
    private int r;

    public Walec() {
        this.r = 1;
    }

    public Walec(String nazwa, int wysokoscH, int r) {
        super(nazwa, wysokoscH);
        this.r = r;
    }

    public double Objetosc(){
        return Math.PI*getWysokoscH()*r;
    }
    public double Pole(){
        return 2*Math.PI*r*(r+getWysokoscH());
    }

    public boolean obrotKwadratu(){
        return r==getWysokoscH();
    }

    @Override
    public String toString() {
        return "Nazwa: " + getNazwa() + " [a=" + r +  "]";
    }
}
