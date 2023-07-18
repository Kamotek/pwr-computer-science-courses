import java.util.ArrayList;

public class Firma {

    public ArrayList<Magazyn> magazyny;

    public Firma()
    {
        this.magazyny = new ArrayList<>();
    }

    public ArrayList<Magazyn> getMagazyny() {
        return magazyny;
    }

    public void przychodyFirmy()
    {
        double przychody = 0;

        for (int i=0; i<getMagazyny().size(); i++)
            przychody+=getMagazyny().get(i).getSumaKwot();

        System.out.println("Przychody firmy: " + przychody + " zł");
    }
}