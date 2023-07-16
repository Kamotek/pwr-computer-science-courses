import java.io.*;

public class Main {

    public static void tworzenieTablicy() throws ArrayIndexOutOfBoundsException{
        int[] tablica = new int[5];

        //try {
            for (int i = 0; i <= 5; i++)
                tablica[i] = 1;
        //}catch(ArrayIndexOutOfBoundsException aiobe){}
    }

    public static double iteracjaTablicy(int iloscIteracji){
        double beforeTime;
        double afterTime;
        double srednia = 0;
        for(int i=0;i<=iloscIteracji;i++){
            beforeTime = System.nanoTime();
            try {
            tworzenieTablicy();
            }catch(ArrayIndexOutOfBoundsException x){}
            afterTime = System.nanoTime();

            srednia+=(afterTime-beforeTime);
        }
        return srednia/iloscIteracji;

    }
    public static int dzieleniePrzezZero() //throws ArithmeticException
     {
        try{
        int a = 10;
        int b = 0;
        return a/b;
        }catch(ArithmeticException x){return 0;}


    }

    public static double iteracjaDzielenia(int iloscIteracji){
        double beforeTime;
        double afterTime;
        double srednia = 0;
        for(int i=0;i<=iloscIteracji;i++){
            beforeTime = System.nanoTime();
          //try {
              dzieleniePrzezZero();
          //}catch(ArithmeticException x){}
            afterTime = System.nanoTime();

            srednia+=(afterTime-beforeTime);
        }
        return srednia/iloscIteracji;

    }

    public static void czytaniePliku() throws IOException
     {
        //    try {
                FileReader fr = new FileReader("x.txt");
                BufferedReader br = new BufferedReader(fr);
          //  }catch(IOException x){}
    }

    public static double iteracjaCzytaniaPliku(int iloscIteracji){
        double beforeTime;
        double afterTime;
        double srednia = 0;

        for(int i=0;i<=iloscIteracji;i++){
            beforeTime = System.nanoTime();
            try {
                czytaniePliku();
            }catch(IOException x){}
            afterTime = System.nanoTime();

            srednia+=(afterTime-beforeTime);
        }
        return srednia/iloscIteracji;

    }


    public static void serializacja() //throws NotSerializableException, IOException
     {
        try {
            try {
                KlasaBezMetody x = new KlasaDruga(5);

                FileOutputStream fout = new FileOutputStream("file.txt");
                ObjectOutputStream oout = new ObjectOutputStream(fout);

                oout.writeObject(x);

                oout.close();
            }catch(NotSerializableException nse){}
        } catch (IOException io) {
          //  System.out.println(io);
        }
    }

    public static double iteracjaSerializacji(int iloscIteracji){
        double beforeTime;
        double afterTime;
        double srednia = 0;

        for(int i=0;i<=iloscIteracji;i++){
            beforeTime = System.nanoTime();
 //           try {
   //             try {
                    serializacja();
     //           } catch (NotSerializableException nse) {}
       //     }catch(IOException x){}
            afterTime = System.nanoTime();

            srednia+=(afterTime-beforeTime);
        }
        return srednia/iloscIteracji;

    }


    public static void main(String[] args) {

        //System.out.println("Czas wykonania 10000 operacji wynosi: " + iteracjaDzielenia(10000));
        /*
        System.out.println("Średni czas wykonania 100 operacji wynosi: " + iteracjaDzielenia(100));
        System.out.println("Średni czas wykonania 1000 operacji wynosi: " + iteracjaDzielenia(1000));
        System.out.println("Średni czas wykonania 5000 operacji wynosi: " + iteracjaDzielenia(1000));
        System.out.println("Średni czas wykonania 10000 operacji wynosi: " + iteracjaDzielenia(10000));
        */

        for(int i=0;i<10;i++)
        System.out.println("Czas wykonania 10000 operacji wynosi: " + iteracjaTablicy(10000));
    }
}