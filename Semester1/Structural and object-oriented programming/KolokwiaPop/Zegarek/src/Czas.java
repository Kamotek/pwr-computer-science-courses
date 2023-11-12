import java.util.Comparator;

public class Czas implements Comparable<Czas> {
    private int godzina;
    private int minuta;
    private int sekunda;

    public Czas(int godzina, int minuta, int sekunda) {
        this.godzina = godzina;
        this.minuta = minuta;
        this.sekunda = sekunda;

        while(this.godzina>24){this.godzina-=24;}

        while(this.sekunda >= 60){
            this.minuta++;
            this.sekunda-=60;
        }
        while(this.minuta >= 60){
            this.godzina++;
            this.minuta-=60;
        }
    }

    public int getGodzina() {
        return godzina;
    }

    public int getMinuta() {
        return minuta;
    }

    public int getSekunda() {
        return sekunda;
    }

    public void setGodzina(int godzina) {
        this.godzina = godzina;
    }

    public void setMinuta(int minuta) {
        this.minuta = minuta;
    }

    public void setSekunda(int sekunda) {
        this.sekunda = sekunda;
    }

    public void przesunSekundy(int sekundy) {
        if (sekundy < 60) {
            this.sekunda += sekundy;
        } else {
            System.out.println("Nieprawidłowa wartość");
        }
    }
    public void przesunGodziny(int godziny) {
        if (godziny < 24) {
            this.godzina += godziny;
        } else {
            System.out.println("Nieprawidłowa wartość");
        }
    }
    public void przesunMinuty(int minuty) {
        if (minuty < 60) {
            this.minuta += minuty;
        } else {
            System.out.println("Nieprawidłowa wartość");
        }
        if(this.minuta>59){this.godzina++;this.minuta-=60;if(this.godzina>24){this.godzina-=24;}}
    }
    public int compareTo(Czas o1) {
        int temp=0;
        if (this.getGodzina() * 3600 + this.getMinuta() * 60 + this.getSekunda() == o1.getGodzina() * 3600 + o1.getMinuta() * 60 + o1.getSekunda()) {
            temp = 0;
        }
        if (this.getGodzina() * 3600 + this.getMinuta() * 60 + this.getSekunda() > o1.getGodzina() * 3600 + o1.getMinuta() * 60 + o1.getSekunda()) {
            temp = 1;
        }
        if (this.getGodzina() * 3600 + this.getMinuta() * 60 + this.getSekunda() < o1.getGodzina() * 3600 + o1.getMinuta() * 60 + o1.getSekunda()) {
            temp = -1;
        }
        return temp;
    }
}