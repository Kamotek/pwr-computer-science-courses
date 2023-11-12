import java.io.Serializable;

public class Proces implements Serializable, Cloneable {
    private static int procesId = 0;
    private int procesDlugosc;
    private int czasZgloszenia;
    private int czasOczekiwania;

    private boolean zaglodzony;
    private boolean wykonany;

    private boolean rozpoczety;

    public Proces clone(){
        try {
            Proces clone = (Proces) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public Proces(int procesFazaDlugosc, int czasZgloszenia) {
        procesId++;
        this.procesDlugosc = procesFazaDlugosc;
        this.czasZgloszenia = czasZgloszenia;
        this.wykonany = false;
        this.rozpoczety = false;
        this.zaglodzony = true;
    }

    public void setWykonany(boolean wykonany) {
        this.wykonany = wykonany;
    }

    public boolean isWykonany() {
        return wykonany;
    }

    public boolean isZaglodzony() {
        return zaglodzony;
    }

    public void setZaglodzony(boolean zaglodzony) {
        this.zaglodzony = zaglodzony;
    }

    public void wykonaj(int i) {
        setZaglodzony(false);

        if(getProcesDlugosc()>i) {
            setProcesDlugosc(getProcesDlugosc() - i);
            if(!isRozpoczety())
                setRozpoczety(true);
        }
        else {
            setProcesDlugosc(0);
            setWykonany(true);
            setRozpoczety(false);
        }
    }

    public boolean isRozpoczety() {
        return rozpoczety;
    }

    public void setRozpoczety(boolean rozpoczety) {
        this.rozpoczety = rozpoczety;
    }

    public static int getProcesId() {
        return procesId;
    }

    public static void setProcesId(int procesId) {
        Proces.procesId = procesId;
    }

    public int getProcesDlugosc() {
        return procesDlugosc;
    }

    public void setProcesDlugosc(int procesDlugosc) {
        this.procesDlugosc = procesDlugosc;
    }

    public int getCzasZgloszenia() {
        return czasZgloszenia;
    }

    public void setCzasZgloszenia(int czasZgloszenia) {
        this.czasZgloszenia = czasZgloszenia;
    }

    public int getCzasOczekiwania() {
        return czasOczekiwania;
    }

    public void setCzasOczekiwania(int czasOczekiwania) {
        this.czasOczekiwania = czasOczekiwania;
    }

}
