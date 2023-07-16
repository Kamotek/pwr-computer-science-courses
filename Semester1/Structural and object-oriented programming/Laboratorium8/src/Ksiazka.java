public class Ksiazka {
    protected int sygnatura;
    protected double kara;
    protected boolean czyKsiazkaOddanaWTerminie;

    public Ksiazka(){
        this.sygnatura = 0;
        this.kara = 0;
        this.czyKsiazkaOddanaWTerminie = true;
    }

    public Ksiazka(int sygnatura, double kara, boolean zwrot){
        this.sygnatura = sygnatura;
                if(zwrot == true){
                    this.kara = 0;
                    this.czyKsiazkaOddanaWTerminie = true;
                }
                else{
                    this.kara = kara;
                }
    }

    @Override
    public String toString() {
        return "Ksiazka [" +
                "sygnatura=" + sygnatura +
                ", kara=" + kara +
                ", czyKsiazkaOddanaWTerminie=" + czyKsiazkaOddanaWTerminie +
                ']';
    }
}
