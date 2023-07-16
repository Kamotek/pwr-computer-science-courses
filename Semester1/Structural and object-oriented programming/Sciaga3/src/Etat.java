public class Etat implements StrategiaObliczaniaWynagrodzenia{
    double pensja;
    double premia;

    public Etat(double pensja){
        this.pensja = pensja;
        this.premia = 500;
    }

    public double getPensja() {
        return pensja;
    }

    public double getPremia() {
        return premia;
    }

    @Override
    public double obliczanieWynagrodzenia(){
        return getPensja() + getPremia();
    }
}
