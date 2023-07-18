import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class App {

    public static char[] charArray;
    public static int[] charfreq;

    public static ArrayList<Integer> codesForLetters = new ArrayList<>();

    public static HuffmanNode root = null;

    public static StringBuilder encodedBuilder = new StringBuilder();
    public static void main(String[] args) throws Exception {
        String readFilePath = "/home/kamil/Documents/AiSDLab12/drugiePodejscie/xd/src/file.txt";
        String encodedFilePath = "/home/kamil/Documents/AiSDLab12/drugiePodejscie/xd/src/encodedFile.txt";

        dataArrayGenerator(readFile(readFilePath));

        System.out.println(readFile(readFilePath));


        for(int i = 0; i<charArray.length;i++){
            System.out.println(charArray[i] + " - " + charfreq[i]);
        }

        System.out.println("----- Dzialamy tutaj dalej -----");

        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(charArray.length, new priorityComparator());


        for(int i = 0; i<charArray.length;i++){
            priorityQueue.add(new HuffmanNode(charfreq[i], charArray[i]));
        }


        makeHuffmanTree(priorityQueue);

        encode(root, readFile(readFilePath));
        

        writeEncodedData(encodedBuilder.toString(), encodedFilePath);

        String encodedData = readFile(encodedFilePath);

        decode(encodedData, root);
    }

    public static void decode(String encodedData, HuffmanNode root){


        System.out.println("---------");
        String[] encodedDataArray = encodedData.split("");

        StringBuilder sb = new StringBuilder();
        HuffmanNode tempNode = root;


        for(int i = 0; i<encodedDataArray.length;i++){
            if(encodedDataArray[i].equals("1")){
                if(tempNode.getRight()!=null){
                    tempNode = tempNode.getRight();
                }
            }
            if(encodedDataArray[i].equals("0")){
                if(tempNode.getLeft()!=null){
                    tempNode = tempNode.getLeft();
                }
            }

            if(tempNode.getLeft()==null && tempNode.getRight() == null){sb.append(tempNode.getValue()); tempNode = root;}
        }

        System.out.println(sb.toString());
    }

    public static void writeEncodedData(String code, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(code);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void encode(HuffmanNode root, String text){
       
        String[] stringArray = text.split("");
        ArrayList<Character> charArrayList = new ArrayList<>();




        for(int i = 0; i< stringArray.length;i++){
            charArrayList.add(stringArray[i].charAt(0));
        }
                

        for (Character c : charArrayList) {
            searchForCode(c, root);
        }


         

    }

        public static void searchForCode(char c, HuffmanNode root){
            if(root.getValue() == c)
                encodedBuilder.append(root.getActualNumber());
            
                if(root.getLeft()!=null)
                    searchForCode(c, root.getLeft());
                if(root.getRight()!=null)
                    searchForCode(c, root.getRight());
            
        }

    public static void makeHuffmanTree(PriorityQueue<HuffmanNode> priorityQueue){

        HuffmanNode temp1 = null;
        HuffmanNode temp2 = null;
        HuffmanNode temp3 = null;
        while(priorityQueue.size()>1){
            

                 temp1 = priorityQueue.poll();
             //    System.out.println("Sciagamy " + temp1);

            
       
                 temp2 = priorityQueue.poll();
           //      System.out.println("Sciagamy " + temp2);




                temp3 = new HuffmanNode(temp1.getWeight()+temp2.getWeight(), temp1, temp2);
            
            
            //    System.out.println("Wkladamy " + temp3);

            priorityQueue.add(temp3);
        }


        printCode(priorityQueue.peek(), "");


        root = priorityQueue.poll();


    }

    public static String readFile(String filePath) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String text = sb.toString();

        return text;
    }

    public static void dataArrayGenerator(String str){
        String[] stringArray = str.split("");
        ArrayList<Character> charArrayList = new ArrayList<>();
        ArrayList<Integer> charArrayListNumbers = new ArrayList<>();

        for(int i = 0; i< stringArray.length;i++){
            charArrayList.add(stringArray[i].charAt(0));
        }

        for(int i = 0; i<charArrayList.size();i++){
            char temp = charArrayList.get(i);
            int counter = 1;
            for(int j = i+1; j<charArrayList.size();j++){
                if(temp == charArrayList.get(j)){
                    counter++;
                    charArrayList.remove(j);
                    j--;
                }
            }
            charArrayListNumbers.add(counter);
        }


        charArray = new char[charArrayList.size()];
        charfreq = new int[charArrayListNumbers.size()];

        for(int i = 0; i<charArrayList.size();i++){
            charArray[i] = charArrayList.get(i);
            charfreq[i] = charArrayListNumbers.get(i);
        }

    }


    public static void printCode(HuffmanNode root, String s)
    {
 
        if (root.left == null || root.right == null
            && Character.isLetter(root.value)) {
 
          
            System.out.println(root.value + "(" + root.weight + "): " + s);
     
            String temp = s;
            
            root.setActualNumber(s);

 
            return;
        }
 
        // if we go to left then add "0" to the code.
        // if we go to the right add"1" to the code.
 
        // recursive calls for left and
        // right sub-tree of the generated tree.
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

}
