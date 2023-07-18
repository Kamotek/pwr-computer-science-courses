import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneratorProcesow {
    private int minimalnaDlugoscProcesu;
    private int maksymalnaDlugoscProcesu;
    private int iloscProcesow;

    private int ziarno;

    public GeneratorProcesow(int minimalnaDlugoscProcesu, int maksymalnaDlugoscProcesu, int iloscProcesow, int ziarno) {
        this.minimalnaDlugoscProcesu = minimalnaDlugoscProcesu;
        this.maksymalnaDlugoscProcesu = maksymalnaDlugoscProcesu;
        this.iloscProcesow = iloscProcesow;
        this.ziarno = ziarno;
    }

    public int getMinimalnaDlugoscProcesu() {
        return minimalnaDlugoscProcesu;
    }

    public int getMaksymalnaDlugoscProcesu() {
        return maksymalnaDlugoscProcesu;
    }

    public int getIloscProcesow() {
        return iloscProcesow;
    }

    public ArrayList<Boolean> generatorCzasuDodaniaProcesu(int szanse){

        // dla szanse = 2 mamy 50%
        // dla szanse = 3 mamy 33%, dla 4 25%, dla 5 20% etc etc
        Random random = new Random();
        ArrayList<Boolean> tab = new ArrayList<>();
        tab.add(true);

        for(int i = 0; i<iloscProcesow+1;i++){
            if((random.nextInt(100)%szanse)==0) tab.add(true);
            if((random.nextInt(100)%szanse)!=0) tab.add(false);
        }

        return tab;
    }

    public ArrayList<Proces> generator(int ziarno){

        // wiecej malych / duzych
        // duze na koncu / duze na poczatku
        // na zmiane maly-duzy
        // naturalny

        ArrayList<Proces> list = new ArrayList<>();

        if(ziarno == 0)
            list = generatorProsty();
        if(ziarno == 1)
            list = generatorNaZmiane();
        if(ziarno == 2)
            list = generatorRosnacy();
        if(ziarno == 3)
            list = generatorMalejacy();


        return list;
    }

    public ArrayList<Proces> generatorMalejacy(){
        ArrayList<Proces> tablicaProcesow = new ArrayList<>();
        int dlugosc = 0;
        Random random = new Random();

        for(int indexProcesu = 0; indexProcesu < getIloscProcesow();indexProcesu++){
          //  if(indexProcesu<getIloscProcesow()/2) dlugosc = random.nextInt(getMinimalnaDlugoscProcesu())+(getMinimalnaDlugoscProcesu()+getMaksymalnaDlugoscProcesu())/2;
            //if(indexProcesu>=getIloscProcesow()/2) dlugosc = random.nextInt(getMaksymalnaDlugoscProcesu())-(getMinimalnaDlugoscProcesu()+getMaksymalnaDlugoscProcesu())/2;
            if(indexProcesu<getIloscProcesow()/2) dlugosc = getMaksymalnaDlugoscProcesu();
            if(indexProcesu>=getIloscProcesow()/2) dlugosc = getMinimalnaDlugoscProcesu();
            tablicaProcesow.add(new Proces(dlugosc, indexProcesu));
        }

        return tablicaProcesow;
    }

    public ArrayList<Proces> generatorRosnacy(){
        ArrayList<Proces> tablicaProcesow = new ArrayList<>();
        int dlugosc = 0;
        Random random = new Random();

        for(int indexProcesu = 0; indexProcesu < getIloscProcesow();indexProcesu++){
         //   if(indexProcesu>getIloscProcesow()/2) dlugosc = random.nextInt(getMinimalnaDlugoscProcesu())+(getMinimalnaDlugoscProcesu()+getMaksymalnaDlugoscProcesu())/2;
         //   if(indexProcesu<=getIloscProcesow()/2) dlugosc = random.nextInt(getMaksymalnaDlugoscProcesu())-(getMinimalnaDlugoscProcesu()+getMaksymalnaDlugoscProcesu())/2;
            if(indexProcesu>getIloscProcesow()/2) dlugosc = getMaksymalnaDlugoscProcesu();
            if(indexProcesu<=getIloscProcesow()/2) dlugosc = getMinimalnaDlugoscProcesu();

            tablicaProcesow.add(new Proces(dlugosc, indexProcesu));
        }

        return tablicaProcesow;

    }

    public ArrayList<Proces> generatorNaZmiane(){
        ArrayList<Proces> tablicaProcesow = new ArrayList<>();
        int sumaDlugosci = 0;
        int dlugosc = 0;

        for(int indexProcesu = 0; indexProcesu < getIloscProcesow();indexProcesu++) {
            if (indexProcesu % 2 == 0) dlugosc = getMinimalnaDlugoscProcesu();
            if (indexProcesu % 2 != 0) dlugosc = getMaksymalnaDlugoscProcesu();
            tablicaProcesow.add(new Proces(dlugosc, indexProcesu));
        }

        System.out.println("Dlugosc procesu o indeksie 100 =" + tablicaProcesow.get(100).getProcesDlugosc());
        System.out.println("Dlugosc procesu o indeksie 101 =" + tablicaProcesow.get(101).getProcesDlugosc());

        return tablicaProcesow;
    }

    public ArrayList<Proces> generatorProsty(){

        Random random = new Random();
        ArrayList<Proces> tablicaProcesow = new ArrayList<>();
        int sumaDlugosci = 0;
        int dlugosc;

        for(int indexProcesu = 0; indexProcesu < getIloscProcesow();indexProcesu++){
            dlugosc = random.nextInt(getMaksymalnaDlugoscProcesu()-getMinimalnaDlugoscProcesu())+getMinimalnaDlugoscProcesu();
            tablicaProcesow.add(new Proces(dlugosc, indexProcesu));
            //tablicaProcesow.add(new Proces(20, indexProcesu, sumaDlugosci));
        }
        return tablicaProcesow;
    }

    public void serializatorProcesow(){
        ArrayList<Proces> listaProcesow = generator(ziarno);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("procesy.txt"));
            for (Proces x : listaProcesow
            ) {
                oos.writeObject(x);
            }
            oos.close();
        }catch(IOException x){System.out.println("Coś nie działa przy serializacji" + x);}
    }

    public ArrayList<Proces> deserializatorProcesow(){
        ArrayList<Proces> tablicaProcesow = new ArrayList<>();

        try {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("procesy.txt"));

                Proces proces = (Proces) ois.readObject();
                while (proces != null) {
                    tablicaProcesow.add(proces);
                    proces = (Proces) ois.readObject();
                }
            }catch(ClassNotFoundException y){System.out.println("Klasa nie znaleziona");}
        }catch(IOException x){System.out.println("Coś się staneło przy deserializacji" + x);}
        return tablicaProcesow;
    }

}
