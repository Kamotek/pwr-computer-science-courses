public class Stos {
    private int[] stos;
    static int licznik;
    private int wysokoscStosu;

    public Stos(int wysokoscStosu){
        this.wysokoscStosu = wysokoscStosu;
        this.stos = new int[this.wysokoscStosu];
        this.licznik = 0;
    }

    public int[] getStos() {
        return stos;
    }

    public void setStos(int[] stos) {
        this.stos = stos;
    }

    public static int getLicznik() {
        return licznik;
    }

    public int getWysokoscStosu() {
        return wysokoscStosu;
    }

    public void dodawanieElementu(int liczba){
        try {
            this.stos[licznik] = liczba;
            licznik++;
        }catch(ArrayIndexOutOfBoundsException e){System.out.println("Przekroczono zakres");}
    }
    public void usuwanieElementu(){
        try {
            if(licznik>=0) {
                int[] temp = new int[licznik];
                for (int i = 0; i < licznik - 1; i++) {
                    temp[i] = this.stos[i];
                }
                setStos(temp);
                licznik--;
            }else{throw new ArrayIndexOutOfBoundsException();}

        }catch(ArrayIndexOutOfBoundsException e){System.out.println("Brak elementow na stosie");}
    }
    public void wyswietlElementyStosu(){
        if(licznik>0)
            System.out.println(this.stos[licznik-1]);
        if(licznik==0)
            System.out.println("Pusto");
    }

    public boolean czyPusty(){
        if(licznik==0){
            return true;
        }else{return false;}
    }

    public boolean czyPelny(){
        if(licznik==0){
            return false;
        }else{return true;}
    }
}
