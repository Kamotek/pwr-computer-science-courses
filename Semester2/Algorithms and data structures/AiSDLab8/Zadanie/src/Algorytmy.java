import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Algorytmy{

    int dlugoscTablicy;
    int[] tablica;
    int[] interwaly;

    public Algorytmy(int dlugoscTablicy){
        this.dlugoscTablicy = dlugoscTablicy;

        Random random = new Random();
        tablica = new int[dlugoscTablicy];
        interwaly = new int[dlugoscTablicy];

        for(int i = 0; i<dlugoscTablicy;i++){
            tablica[i] = random.nextInt(dlugoscTablicy);
        }
    }

    // -------------------------------Nizej znajduja sie generatory ciagow opisanych w zadaniu-------------------------------------


    public int[] kopiowanieTablicy(int[] array){

        int[] temp = new int[array.length];
        System.arraycopy(array, 0, temp, 0, dlugoscTablicy);

        return temp;
    }

    public Integer[] generatorInterwalowA(){
      
        ArrayList<Integer> tempArray = new ArrayList<>();

        int l = 0;
        for(int i = 0; i<dlugoscTablicy;i++){
            if(l > l*3+1) break;
            l = l*3+1;
            if(l < 0 ) break;
            if(l > dlugoscTablicy) break;
                tempArray.add(l);  
            
        }

        Collections.reverse(tempArray);

  

        return tempArray.toArray(new Integer[tempArray.size()]);
    }


    public Integer[] generatorInterwalowB(){
        ArrayList<Integer> tempArray = new ArrayList<>();

        int l = 0;
        int k = 0;
        for(int i = 0; i<dlugoscTablicy;i++){
            //if(l >= (int) (Math.pow(2, k)+1)) break;
            if(l > dlugoscTablicy) break;
            l = (int) (Math.pow(2, k)+1);
            k++;
            tempArray.add(l);  
        }

        Collections.reverse(tempArray);

        tempArray.add(1);

        return tempArray.toArray(new Integer[dlugoscTablicy]);
    }


    public Integer[] generatorInterwalowC(){
        ArrayList<Integer> tempArray = new ArrayList<>();

        int l = 0;
        int k = 0;
        for(int i = 0; i<dlugoscTablicy;i++){
            if(l > (int) (Math.pow(2, k)+1)) break;
            l = (int) (Math.pow(2, k)-1);
            k++;
            tempArray.add(l);  
        }

        Collections.reverse(tempArray);

        return tempArray.toArray(new Integer[dlugoscTablicy]);
    }


    public Integer[] generatorInterwalowD(){
        ArrayList<Integer> tempArray = new ArrayList<>();

        int l = 1;
        int temp1 = 0, temp2 = 0;
        for(int i = 0; i<dlugoscTablicy;i++){
            tempArray.add(l);  
            temp1 = temp2;
            temp2 = temp1 + l;
            if(l > temp1 + temp2) break;
            l = temp1 + temp2;
        }

        Collections.reverse(tempArray);

        return tempArray.toArray(new Integer[dlugoscTablicy]);
    }


    public Integer[] generatorInterwalowE(){
        ArrayList<Integer> tempArray = new ArrayList<>();

        int n = dlugoscTablicy;
        int h = n/2;

        for(int i = 0;i<dlugoscTablicy/2;i++){
            tempArray.add(h);
            h *= 0.75;
            if(h == 0) tempArray.add(1);
           // if(h == 0 ) break;
        }
//
        



       // Collections.reverse(tempArray);

        return tempArray.toArray(new Integer[dlugoscTablicy]);
    }

    // ------------------Tutaj znajduje sie wywolanie poszczegolnych algorytmow-----------------------------------------------

    public void badanieShell(){

        
        System.out.println();
        System.out.println("Badanie algorytmow dla ciagu a) i " + dlugoscTablicy + " elementow: ");
        System.out.println("----------");
        long preTime = System.nanoTime();
        shellA(kopiowanieTablicy(tablica), generatorInterwalowA());
        long posTime = System.nanoTime();

        long time = (posTime-preTime)/1000000;

        System.out.println("Dla podpunktu 1. czas wynosi: " + time + " ms");

     
        preTime = System.nanoTime();
        shellB(kopiowanieTablicy(tablica), generatorInterwalowA());
        posTime = System.nanoTime();

        time = (posTime-preTime)/1000000;

        System.out.println("Dla podpunktu 2. czas wynosi: " + time + " ms");

        preTime = System.nanoTime();
        shellC(kopiowanieTablicy(tablica), generatorInterwalowA());
        posTime = System.nanoTime();

        time = (posTime-preTime)/1000000;

        System.out.println("Dla podpunktu 3. czas wynosi: " + time + " ms");

        shellC(kopiowanieTablicy(tablica), generatorInterwalowA());

        //---

        
 
        System.out.println();
        System.out.println("Badanie algorytmu dla ciagu b) i " + dlugoscTablicy + " elementow: ");
        System.out.println("----------");
        preTime = System.nanoTime();

        shellA(kopiowanieTablicy(tablica), generatorInterwalowB());
        posTime = System.nanoTime();

        time = (posTime-preTime)/1000000;

        System.out.println("Dla podpunktu a) czas wynosi: " + time + " ms");

     
        preTime = System.nanoTime();
        shellB(kopiowanieTablicy(tablica), generatorInterwalowB());
        posTime = System.nanoTime();

        time = (posTime-preTime)/1000000;

        System.out.println("Dla podpunktu b) czas wynosi: " + time + " ms");

        preTime = System.nanoTime();
        shellC(kopiowanieTablicy(tablica), generatorInterwalowB());
        posTime = System.nanoTime();

        time = (posTime-preTime)/1000000;

        System.out.println("Dla podpunktu c) czas wynosi: " + time + " ms");



        //---

        System.out.println();
        System.out.println("Badanie algorytmu dla ciagu c) i " + dlugoscTablicy + " elementow: ");
        System.out.println("----------");
        preTime = System.nanoTime();
        shellA(kopiowanieTablicy(tablica), generatorInterwalowC());
        posTime = System.nanoTime();

        time = (posTime-preTime)/1000000;

        System.out.println("Dla podpunktu a) czas wynosi: " + time + " ms");

     
        preTime = System.nanoTime();
        shellB(kopiowanieTablicy(tablica), generatorInterwalowC());
        posTime = System.nanoTime();

        time = (posTime-preTime)/1000000;

        System.out.println("Dla podpunktu b) czas wynosi: " + time + " ms");

        preTime = System.nanoTime();
        shellC(kopiowanieTablicy(tablica), generatorInterwalowC());
        posTime = System.nanoTime();

        time = (posTime-preTime)/1000000;

        System.out.println("Dla podpunktu c) czas wynosi: " + time + " ms");

        shellC(kopiowanieTablicy(tablica), generatorInterwalowC());



        //---

        System.out.println();
        System.out.println("Badanie algorytmu dla ciagu d) i " + dlugoscTablicy + " elementow: ");
        System.out.println("----------");
        preTime = System.nanoTime();
        shellA(kopiowanieTablicy(tablica), generatorInterwalowD());
        posTime = System.nanoTime();

        time = (posTime-preTime)/1000000;

        System.out.println("Dla podpunktu a) czas wynosi: " + time + " ms");

     
        preTime = System.nanoTime();
        shellB(kopiowanieTablicy(tablica), generatorInterwalowD());
        posTime = System.nanoTime();

        time = (posTime-preTime)/1000000;

        System.out.println("Dla podpunktu b) czas wynosi: " + time + " ms");

        preTime = System.nanoTime();
        shellC(kopiowanieTablicy(tablica), generatorInterwalowD());
        posTime = System.nanoTime();

        time = (posTime-preTime)/1000000;

        System.out.println("Dla podpunktu c) czas wynosi: " + time + " ms");

        shellC(kopiowanieTablicy(tablica), generatorInterwalowD());

        //---
        System.out.println();
        System.out.println("Badanie algorytmu dla ciagu e) i " + dlugoscTablicy + " elementow: ");
        System.out.println("----------");
        preTime = System.nanoTime();
        shellA(kopiowanieTablicy(tablica), generatorInterwalowE());
        posTime = System.nanoTime();

        time = (posTime-preTime)/1000000;

        System.out.println("Dla podpunktu a) czas wynosi: " + time + " ms");

     
        preTime = System.nanoTime();
        shellB(kopiowanieTablicy(tablica), generatorInterwalowE());
        posTime = System.nanoTime();

        time = (posTime-preTime)/1000000;

        System.out.println("Dla podpunktu b) czas wynosi: " + time + " ms");

        preTime = System.nanoTime();
        shellC(kopiowanieTablicy(tablica), generatorInterwalowE());
        posTime = System.nanoTime();

        time = (posTime-preTime)/1000000;

        System.out.println("Dla podpunktu c) czas wynosi: " + time + " ms");

        shellC(kopiowanieTablicy(tablica), generatorInterwalowE());
        
        //---
        System.out.println("Dla porownania:");
        preTime = System.nanoTime();
        insertsort(kopiowanieTablicy(tablica));
        posTime = System.nanoTime();

        time = (posTime-preTime)/1000000;

        System.out.println("Dla vanilla InsertionSort czas wynosi: " + time + " ms");

        preTime = System.nanoTime();
        bubblesort(kopiowanieTablicy(tablica));
        posTime = System.nanoTime();

        time = (posTime-preTime)/1000000;

        System.out.println("Dla vanilla BubbleSort czas wynosi: " + time + " ms");

   



    }

    // ------------------Tutaj znajduja sie algorytmy shella z poszczegolnych podpunktow-------------------------


    private void shellA(int [] array, Integer[] interwaly)
    {
        int n = array.length;
        int iteration = 1;
        int actDistance = interwaly[iteration];
        int comp, cur;

        while (actDistance >= 1)
        {
//            if(interwaly[iteration] > array.length) iteration++;
            for (int i = actDistance; i < n; i++)
            {
                cur = array[i];
                comp = i;
                while (comp >= actDistance && cur < array[comp-actDistance])
                {
                    array[comp] = array[comp-actDistance];
                    comp -= actDistance;
                }
                array[comp] = cur;
            }
            iteration++;
            if(iteration >= interwaly.length) break;

            if(interwaly[iteration] == null) break;
            actDistance = interwaly[iteration];
        }
    }

    private void shellB(int [] array, Integer [] interwaly)
    {
        int n = array.length;
        int iteration = 0;
        int actDistance = interwaly[iteration];
        int comp, cur;

        while (actDistance > 1)
        {
            if (actDistance != 1)
            {
                for (int i=actDistance; i<n; i++)
                {
                    cur = array[i];
                    comp = i;
                    while (comp >= actDistance && cur < array[comp-actDistance])
                    {
                        array[comp] = array[comp-actDistance];
                        comp -= actDistance;
                    }
                    array[comp] = cur;
                }
            }
            if(interwaly[iteration] == null) break;

            actDistance = interwaly[iteration++];



        }
        if (actDistance == 1){
            bubblesort(array);
        }
        
    }

    private void shellC(int [] array, Integer [] interwaly)
    {
        int n = array.length;
        int iteration = 0;
        int actDistance = interwaly[iteration];
        int comp, cur;


        while (actDistance>1)
        {
            if (actDistance != 1)
            {
                for (int i = actDistance; i < n; i++)
                {
                    cur = array[i];
                    comp = i;
                    while (comp >= actDistance)
                    {
                        if (cur < array[comp - actDistance])
                            swap(array, comp, comp-actDistance);
                        comp -= actDistance;
                    }
                }
            }
            if(interwaly[iteration] == null) break;

            actDistance = interwaly[iteration++];


        }
        if (actDistance==1)
        {
            insertsort(array);

        }
     
    }

    // ------------------Nizej znajduja sie algorytmy proste i funkcje je wspomagajace-------------------------------------

    private void insertsort(int [] array)
    { int n = array.length;
        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;
 
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    private void bubblesort(int [] tabl)
    {
        int size = tabl.length;
        for (int i = 0; i < size-1; i++)
        {
            for (int j = 0; j < size-i-1; j++)
            {
                if (tabl[j] > tabl[j+1])
                    swap(tabl, j+1, j);
            }
        }
    }

    private void swap(int [] tabl, int left, int right)
    {
        int temp = tabl[left];
        tabl[left] = tabl[right];
        tabl[right] = temp;
    }

}
