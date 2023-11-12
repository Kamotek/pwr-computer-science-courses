public class Urzednik extends Pracownik{
    double placa;
    float premia;

    public Urzednik(String nazwisko, double stawka, float etat, float premia){
        super(nazwisko, etat, stawka);
        this.placa = stawka*(8*etat) + (this.placa*premia);
    }

    public Urzednik(){
    }
}
