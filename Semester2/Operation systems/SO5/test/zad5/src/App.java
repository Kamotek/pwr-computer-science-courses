import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class App {

    static int minTime = 4;
    static int maxTime = 10;
    static int minExeTime = 2;
    static int maxExeTime = 30;
    static int minLoad = 5;
    static int maxLoad = 25;

    static int processorAmount = 30;
    static int queueSize = 300;
    static int p = 60;
    static int r = 30;
    static int z = 5; 
    public static void main(String[] args) throws IOException {



        Generators generator = new Generators(minTime, maxTime, minExeTime, maxExeTime, minLoad, maxLoad);
        Strategies strategies = new Strategies(processorAmount, queueSize, p, r, z, generator);
        System.out.println("\nSTRATEGIA 1");
        System.out.println(strategies.strategia1().toString());
        System.out.println("\nSTRATEGIA 2");
        System.out.println(strategies.strategia2().toString());
        System.out.println("\nSTRATEGIA 3");
        System.out.println(strategies.strategia3().toString());

        //datasetCreate();
    }

    public static void datasetCreate() throws IOException {


        String dataSetName = "maxLoadFrom1to100";

        Generators generator = new Generators(minTime, maxTime, minExeTime, maxExeTime, minLoad, maxLoad);
        Strategies strategies = new Strategies(processorAmount,queueSize,p,r,z,generator);

        
        String src = "src/Strategia1-"+dataSetName+".csv";

        toCSV(src,"Average_Load,Deviation,Time,Querries_Count,Migration_count,Procesors_Count,Processes,p,r,z,maxLoad");
        toCSV(src,"0,0,0,0,0,"+processorAmount+","+queueSize+","+p+","+r+","+z+",0");
        for (int i = 1; i <= 100; i++) {
            maxLoad = i;
            generator = new Generators(minTime, maxTime, minExeTime, maxExeTime, minLoad, maxLoad);
            strategies = new Strategies(processorAmount,queueSize,p,r,z,generator);
            toCSV(src,strategies.strategia1().toCSV());
            System.out.println(i+"% done strat 1");
        }
        

        src = "src/Strategia2-"+dataSetName+".csv";

        toCSV(src,"Average_Load,Deviation,Time,Querries_Count,Migration_count,Procesors_Count,Processes,p,r,z,maxLoad");
        toCSV(src,"0,0,0,0,0,"+processorAmount+","+queueSize+","+p+","+r+","+z+",0");
        for (int i = 1; i <= 100; i++) {
            maxLoad = i;
            generator = new Generators(minTime, maxTime, minExeTime, maxExeTime, minLoad, maxLoad);
            strategies = new Strategies(processorAmount,queueSize,p,r,z,generator);
            toCSV(src,strategies.strategia2().toCSV());
            System.out.println(i+"% done strat 2");
        }
        
         src = "src/Strategia3-"+dataSetName+".csv";

        toCSV(src,"Average_Load,Deviation,Time,Querries_Count,Migration_count,Procesors_Count,Processes,p,r,z,maxLoad");
        toCSV(src,"0,0,0,0,0,"+processorAmount+","+queueSize+","+p+","+r+","+z+",0");


        for (int i = 1; i <= 100; i++) {
            maxLoad = i;
            generator = new Generators(minTime, maxTime, minExeTime, maxExeTime, minLoad, maxLoad);
            strategies = new Strategies(processorAmount,queueSize,p,r,z,generator);
            System.out.println("at least tutaj docieramy");
            toCSV(src,strategies.strategia3().toCSV());
            System.out.println(i+"% done strat 3");
        }

        
        System.out.println("Completed");
    }
    public static void toCSV(String src,String str) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(src, true));
        writer.append(str);
        writer.append('\n');

        writer.close();
    }
}
