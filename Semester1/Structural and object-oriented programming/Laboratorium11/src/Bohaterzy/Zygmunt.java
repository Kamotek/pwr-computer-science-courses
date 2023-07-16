package Bohaterzy;

import Atak.Atak;
import Ruch.Ruch;
import Skok.Skok;

public class Zygmunt extends Bohater {


    public Zygmunt(){
        super();
    }
    public Zygmunt(Atak atakInfo, Skok skokInfo, Ruch ruchInfo){
        super(skokInfo,atakInfo,ruchInfo);
    }

    public String name(){
        return ("Zygmunt");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
