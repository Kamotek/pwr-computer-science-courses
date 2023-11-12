package mojaklasa;

public class Data {
    int zmienna1 = 3;
    double zmienna2 = 6;

    private int dd;
    private int mm;
    private int yy;
    public Data(int a, int b, int c){
        dd = a;
        mm = b;
        yy = c;
    }
    public Data(){
        dd = 25;
        mm = 10;
        yy = 2022;
    }
    // throw new IllegalArgumentException();
    public void metoda1(){
        if(dd<=0 && dd>=32)
            System.out.println("Zły dzień");

    }
    public void metoda2(){
        if(mm >= 12 && mm<=1)
    }
    public boolean czyDataPoprawna(){
          // Sztos metoda -->> boolean equals()  J384Ć  if(obj1 == obj2) <<-- To gówno
    }
    public int getDD(){
        return dd;
    }
    public setDD(int n){
        dd = n;
    }
}

