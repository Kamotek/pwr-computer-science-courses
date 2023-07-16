import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        ArrayList<Pracownik> listaPracownikow = new ArrayList<Pracownik>(5);

        listaPracownikow.add(new Urzednik("Zygi", 100, 1,1));
        listaPracownikow.add(new Robotnik());
        listaPracownikow.add(new Robotnik());


        ArrayList<Pracownik> newList = new ArrayList<>();
        ArrayList<String> listaNazwisk = new ArrayList<>();

        for(Pracownik p: listaPracownikow){
            listaNazwisk.add(p.nazwisko);
        }

        for(String n : listaNazwisk){
            if()
        }







    }
}