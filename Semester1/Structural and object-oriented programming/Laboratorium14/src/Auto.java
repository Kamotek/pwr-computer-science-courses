public abstract class Auto {
    private String marka;
    private int lk;
    private long nrNadwozia;
    private boolean diesel;

    private double zuzycie;

    public String getMarka() {
        return marka;
    }

    public int getLk() {
        return lk;
    }

    public long getNrNadwozia() {
        return nrNadwozia;
    }

    public boolean isDiesel() {
        return diesel;
    }

    public double getZuzycie() {
        return zuzycie;
    }

    public Auto(String marka, int lk, long nrNadwozia, boolean diesel, double zuzycie) {
        this.marka = marka;
        this.lk = lk;
        this.nrNadwozia = nrNadwozia;
        this.diesel = diesel;
        this.zuzycie = zuzycie;
    }
    abstract double ilePaliwa();

    @Override
    public String toString() {
        return "Auto{" +
                "marka='" + marka + '\'' +
                ", lk=" + lk +
                ", nrNadwozia=" + nrNadwozia +
                ", diesel=" + diesel +
                ", zuzycie=" + zuzycie +
                '}';
    }
}
