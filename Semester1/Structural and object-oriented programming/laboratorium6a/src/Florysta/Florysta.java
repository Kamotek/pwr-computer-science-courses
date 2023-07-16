package Florysta;
import Bukiet.*;

public class Florysta {
    public static void tworzBukiet(Bukiet kwiat){
        kwiat.setDostepny(true);
    }
    public static void sprzedajBukiet(Bukiet kwiat){
        kwiat.setDostepny(false);
     }
}
