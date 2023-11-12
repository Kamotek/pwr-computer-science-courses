public class Zegar{
    private int czasOdPolnocy;
    private int hh, mm, ss;

    public Zegar(){
        hh = 0;
        mm = 0;
        ss = 0;
        czasOdPolnocy = 0;
    }
    public Zegar(int hh, int mm, int ss){
        this.hh = hh;
        this.mm = mm;
        this.ss = ss;
        czasOdPolnocy = hh*3600 + mm*60 + ss;
    }


    public void Przesun(int hh, int mm, int ss){
        czasOdPolnocy += hh*3600 + mm*60 + ss;
        this.ss += ss;
        this.mm += mm;
        this.hh += hh;
    }


    public int getHh() {
        return hh;
    }

    public int getMm() {
        return mm;
    }
    public int getSs(){
        return ss;
    }
    public void Wypisz(){
        int godzina = getHh();
        int minuta = getMm();
        int sekunda = getSs();
        if(czasOdPolnocy>86400)
            czasOdPolnocy = czasOdPolnocy-86400;
        if(godzina>=24)
            godzina = godzina%24;
        if(minuta>=59){
            minuta = minuta%59;
            godzina++;
        }
        if(sekunda>=59)
        {
            sekunda = sekunda%59;
            minuta++;
        }

        System.out.println("Czas od pólnocy w sekundach to: " + czasOdPolnocy);
        System.out.println("W formacie hh:mm:ss natomiast mamy czas: " + godzina + ":" + minuta + ":" + sekunda);
    }
}
