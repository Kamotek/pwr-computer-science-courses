package Bohaterzy;

import Atak.Atak;
import Ruch.Ruch;
import Skok.Skok;

public class Czarek extends Bohater {
    public Czarek(){
        super();
    }
    public Czarek(Atak atakInfo, Skok skokInfo, Ruch ruchInfo){
        super(skokInfo,atakInfo,ruchInfo);
    }

    public String name(){
        return ("Czarek");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
