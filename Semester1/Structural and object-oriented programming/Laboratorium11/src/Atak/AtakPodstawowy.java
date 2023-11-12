package Atak;

import java.io.Serializable;

public class AtakPodstawowy implements Atak, Serializable {
    @Override
    public String toString(){
        return "Podstawowy atak";
    }
}
