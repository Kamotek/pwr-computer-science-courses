package Bohaterzy;

import Atak.*;
import Ruch.*;
import Skok.*;

public abstract class Bohater {

    protected Skok skokInfo;
    protected Atak atakInfo;
    protected Ruch ruchInfo;


    public Bohater(){
        this.skokInfo = new PodstawowySkok();
        this.ruchInfo = new PodstawowyChod();
        this.atakInfo = new AtakPodstawowy();
    }
    public Bohater(Skok skokInfo, Atak atakInfo, Ruch ruchInfo) {
        this.skokInfo = skokInfo;
        this.atakInfo = atakInfo;
        this.ruchInfo = ruchInfo;
    }

    public void setAtakInfo(Atak atakInfo) {
        this.atakInfo = atakInfo;
    }

    public void setRuchInfo(Ruch ruchInfo) {
        this.ruchInfo = ruchInfo;
    }

    public void setSkokInfo(Skok skokInfo) {
        this.skokInfo = skokInfo;
    }

    public Skok getSkokInfo() {
        return skokInfo;
    }

    public Atak getAtakInfo() {
        return atakInfo;
    }

    public Ruch getRuchInfo() {
        return ruchInfo;
    }

    public abstract String name();

    @Override
    public String toString() {
        return
                "-----Bohater-----\n" +
                "-----------------\n" +
                " Skok: " + getSkokInfo() +
                "\n Atak: " + getAtakInfo() +
                "\n Ruch: " + getRuchInfo() +
                "\n-----------------\n";
    }
}
