package Bohaterzy;

import Atak.Atak;
import Ruch.Ruch;
import Skok.Skok;

public class Wladek extends Bohater {
    public Wladek(){
        super();
    }
    public Wladek(Atak atakInfo, Skok skokInfo, Ruch ruchInfo){
        super(skokInfo,atakInfo,ruchInfo);
    }


    public String name(){
        return ("Wladek");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
