public class Zlecenie implements StrategiaObliczaniaWynagrodzenia{
    int godziny;
    double stawka;
    double koszta;

    public Zlecenie(int godziny, double stawka, double koszta) {
        this.godziny = godziny;
        this.stawka = stawka;
        this.koszta = koszta;
    }

    public int getGodziny() {
        return godziny;
    }

    public double getStawka() {
        return stawka;
    }

    public double getKoszta() {
        return koszta;
    }

    @Override
    public double obliczanieWynagrodzenia() {
        return (getStawka()*getGodziny())+getKoszta();
    }
}
