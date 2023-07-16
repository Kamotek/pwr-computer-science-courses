package Atak;

import Atak.Atak;

import java.io.Serializable;

public class MagicznyAtak implements Atak, Serializable {
    @Override
    public String toString(){
        return "Magiczny Atak";
    }
}
