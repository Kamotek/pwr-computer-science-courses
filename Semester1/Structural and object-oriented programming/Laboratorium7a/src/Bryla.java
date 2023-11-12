public abstract class Bryla {
    private String nazwa;
    private int wysokoscH;
    public Bryla(){
        this.nazwa = "Bazowy";
        this.wysokoscH = 1;
    }
    public Bryla(String nazwa, int wysokoscH) {
        this.nazwa = nazwa;
        this.wysokoscH = wysokoscH;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getWysokoscH() {
        return wysokoscH;
    }

    @Override
    public String toString() {
        return "Bryla o nazwie: " + nazwa;
    }

    public abstract double Objetosc();
    public abstract double Pole();


}
