import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class App {

    public static ArrayList<Obj> makeIndex() throws FileNotFoundException, IOException{
        ArrayList<Obj> temp = new ArrayList<>();
        String file = "aforyzm.txt";
        int cur_line = 1;

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine())!=null){
                String[] splitted = line.split("[.,?!   -]");
                for(String s : splitted){
                        if(objectContainsString(s, temp)){
                            int id = objectSearchForId(s, temp);
                            temp.get(id).rows.add(cur_line);
                        }else{
                            temp.add(new Obj(s,cur_line));
                        }
                }
                cur_line++;
            }
        }
        return temp;
    }

    public static boolean objectContainsString(String s, ArrayList<Obj> array){
        for(Obj o : array){
            if(o.getValue().equals(s)) return true;
        }
        return false;
    }

    public static int objectSearchForId(String s, ArrayList<Obj> array){
        for(Obj o : array){
            if(o.getValue().equals(s)) return array.indexOf(o);
        }
        return 0;
    }
    public static void main(String[] args) throws Exception, EmptyQueueException {
        System.out.println("Hello, World!");

        ArrayList<Obj> tempArray = makeIndex();

      //  Collections.sort(tempArray, new StringComp());


        RBT rbt = new RBT();

        for (Obj obj : tempArray) {
            rbt.insert(obj);
        }

        rbt.nodeCounter();
        rbt.printTree();
        rbt.breadthFirstSearch();
        rbt.delete("wadzi");
        rbt.breadthFirstSearch();


    }



}
