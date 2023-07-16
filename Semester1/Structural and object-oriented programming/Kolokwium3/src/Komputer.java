public class Komputer {
    private String nazwaFirmy;
    private String marka;
    private int wielkoscRam;
    private Procesor procesor = new Procesor();

    public Komputer(String nazwaFirmy, String marka, int wielkoscRam,Procesor procesor) {
        this.nazwaFirmy = nazwaFirmy;
        this.marka = marka;
        this.wielkoscRam = wielkoscRam;
        this.procesor = procesor;
    }

    public String getNazwaFirmy() {
        return nazwaFirmy;
    }
    public Procesor getProcesor(){
        return procesor;
    }
}
