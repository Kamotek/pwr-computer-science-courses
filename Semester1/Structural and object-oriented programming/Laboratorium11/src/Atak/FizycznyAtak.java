package Atak;

import Atak.Atak;

import java.io.Serializable;

public class FizycznyAtak implements Atak, Serializable {
    @Override
    public String toString() {
        return "Fizyczny atak";
    }
}
