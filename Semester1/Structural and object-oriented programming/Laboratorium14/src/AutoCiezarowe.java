public class AutoCiezarowe extends Auto{
    private String model;
    private boolean kratka;

    public AutoCiezarowe(String marka, int lk, long nrNadwozia, boolean diesel, String model, double zuzycie, boolean kratka) {
        super(marka, lk, nrNadwozia, diesel, zuzycie);
        this.model = model;
        this.kratka = kratka;
    }

    public String getModel() {
        return model;
    }


    public boolean isKratka() {
        return kratka;
    }

    double ilePaliwa() {
        return getLk() / getZuzycie();
    }
    @Override
    public String toString(){
        return "Auto ciężarowe: " + super.toString();
    }
}
