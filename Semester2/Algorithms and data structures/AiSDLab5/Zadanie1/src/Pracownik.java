import java.io.Serializable;

public class Pracownik implements Serializable{

    

    private int pesel;

    private String imie;
    private String nazwisko;
    private String data;
    private double pensja;
    private int staz;
    private double premia;
    public Pracownik(int pesel, String imie, String nazwisko, String data, double pensja, int staz) {
        this.pesel = pesel;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.data = data;
        this.pensja = pensja;
        this.staz = staz;
        if(staz >= 20) this.premia = this.pensja*0.2;
        if(staz >= 10 && staz <20) this.premia = this.pensja*0.1;
        if(staz < 10) this.premia = 0;
    }

    public Pracownik(){
        this.pesel = 0;
        this.imie = "imie";
        this.nazwisko = "nazwisko";
        this.data = "01.01.1960";
        this.pensja = 3010;
        this.premia = 0; 

        

    }

    public void update(){
        if(getStaz() >= 20) this.premia = this.pensja*0.2;
        if(getStaz() >= 10 && staz <20) this.premia = this.pensja*0.1;
        if(getStaz() < 10) this.premia = 0;   
    }
    

    @Override
    public String toString() {
        return "Pracownik [pesel=" + pesel + ", imie=" + imie + ", nazwisko=" + nazwisko + ", data=" + data
                + ", pensja=" + pensja + ", staz=" + staz + ", premia=" + premia + "]";
    }
    public int getPesel() {
        return pesel;
    }
    public String getImie() {
        return imie;
    }
    public String getNazwisko() {
        return nazwisko;
    }
    public String getData() {
        return data;
    }
    public double getPensja() {
        return pensja;
    }
    public int getStaz() {
        return staz;
    }
    public double getPremia() {
        return premia;
    }
    public void setPesel(int pesel) {
        this.pesel = pesel;
    }
    public void setImie(String imie) {
        this.imie = imie;
    }
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
    public void setData(String data) {
        this.data = data;
    }
    public void setPensja(double pensja) {
        this.pensja = pensja;
    }
    public void setStaz(int staz) {
        this.staz = staz;
    }
    public void setPremia(double premia) {
        this.premia = premia;
    }
}
