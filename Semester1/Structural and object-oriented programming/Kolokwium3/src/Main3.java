public class Main3 {

    public static void metoda1(Komputer[] komputery){
        komputery[2] = new Komputer("xx","yy",8,new Procesor("intel",new Zegar(4,4)));
    }
    public  Komputer metoda2(Komputer[] komputery, String szukanaNazwa){
        Komputer x = null;
        for(int i=0;i< komputery.length;i++){
            if(komputery[i].getNazwaFirmy().equals(szukanaNazwa)){
                x = komputery[i];
            }
        }
        return x;
    }
    public Komputer metoda3(Komputer[] komputery, float gh, String szukanaNazwa){
        for(int i = 0;i< komputery.length;i++){
            if(metoda2(komputery,szukanaNazwa)){
                if( komputery[i].getProcesor().getZegar().getGh() < gh){
                    return komputery[i]
                }
            }
        }
    }
    public static void main(String[] args) {

        Komputer[] komputery = new Komputer[100];

        metoda1(komputery);


    }
}