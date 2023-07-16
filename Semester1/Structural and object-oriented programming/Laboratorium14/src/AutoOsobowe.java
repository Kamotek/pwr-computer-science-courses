public class AutoOsobowe extends Auto {
    private String model;


    public AutoOsobowe(String marka, int lk, long nrNadwozia, boolean diesel, String model, double zuzycie) {
        super(marka, lk, nrNadwozia, diesel, zuzycie);
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    double ilePaliwa(){
        return  getLk()/getZuzycie();
    }

    @Override
    public String toString(){
        return "Auto osobowe, parametry: " +super.toString();
    }
}
