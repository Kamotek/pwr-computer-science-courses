import java.util.ArrayList;
import java.util.Collections;

import org.w3c.dom.NodeList;

public class Graph {
    
    ArrayList<Node> nodeList;
    ArrayList<Integer> path;


    int[] lengths;

    public Graph(){
        nodeList = new ArrayList<>();
        path = new ArrayList<>();

    }


    public void addNode(int value, int direction, int length){
        boolean nodeExists = false;
        int index = 0;

        for(int i = 0; i<nodeList.size();i++){
            if(nodeList.get(i).getValue() == value){
                nodeExists = true;
                index = i;
                break;
            }
        }

        if(!nodeExists)
            nodeList.add(new Node(value, direction, length));
        else{
            nodeList.get(index).addAdjacency(direction, length);
        } 
            
    }


    public void addNode(int value){
        boolean nodeExists = false;
        int index = 0;

        for(int i = 0; i<nodeList.size();i++){
            if(nodeList.get(i).getValue() == value){
                nodeExists = true;
                index = i;
                break;
            }
        }

        if(!nodeExists)
            nodeList.add(new Node(value));
    
            
    }

    public void showAllAdjacencies(){
        for (Node n : nodeList) {
            showNodeAdjacency(n.getValue());
        }
    }

    public void showNodeAdjacency(int value){
        for (Node n : nodeList) {
            if(n.getValue() == value){
                n.showAdjacencyListOfNode();
            }
        }
    }

    public void showNodeAdjacency(String valueName){

        int value = convertFromNameToValue(valueName);

        for (Node n : nodeList) {
            if(n.getValue() == value){
                n.showAdjacencyListOfNode();
            }
        }
    }

    public int convertFromNameToValue(String valueName){
        if(valueName.equals("Wrocław"))
            return 0;
        else if(valueName.equals("Oława"))
            return 1;
        else if(valueName.equals("Brzeg"))
            return 2;
        else if(valueName.equals("Nysa"))
            return 3;
        else if(valueName.equals("Opole"))
            return 4;
        
        return 5;
    }

    public void deepFirstSearch() throws FullStackException, EmptyStackException{
        int originIndex = 0;

        ArrayStack<Node> stack = new ArrayStack<>();
        ArrayList<Node> visited = new ArrayList<>();

        stack.push(nodeList.get(originIndex));
        visited.add(nodeList.get(originIndex));
        int[] lengths = new int[nodeList.size()];
        System.out.println("Wpisujemy wezel o numerze: " + stack.top().getValue());

        while(!stack.isEmpty()){
            int temp = 0;
            for(int i = 0; i<stack.top().listSize();i+=2){
                if(!visited.contains(nodeList.get(i/2))){
                    lengths[temp] = stack.top().getAdjacencyList().get(i+1);
                }else{lengths[temp] = -1;}
                    temp++;   
                }
            if(!visited.contains(nodeList.get(shortestDistance(lengths)))){
                stack.push(nodeList.get(shortestDistance(lengths)));
                visited.add(nodeList.get(shortestDistance(lengths)));

                System.out.println("Wpisujemy wezel o numerze: " + stack.top().getValue());
            }
            if(shortestDistance(lengths) == 0) {stack.pop();}
            
        }
    }

    public void findShortestPath(int destination){
        
        int originIndex = 0;

        

        
        ArrayList<Node> visited = new ArrayList<>();
        ArrayList<Node> unvisited = new ArrayList<>();
        lengths = new int[nodeList.size()+1];


        int temp = 0;
        for (Node n : nodeList) {
            unvisited.add(n);
            lengths[temp] = -1;
            temp++;
        }

        

        
        // iterujemy dopoki wszystkich nie odwiedzimy
        // zaczynajac od naszego origina
        // iterujemy po wszystkich strzalkach jakie z niego wychodza
        // pod warunkiem ze nasza nowa dlugosc jest mniejsza niz ostatnia zapisana
        // wpisujemy do jakiejs tablicy ze odpowiedni node ma taka dlugosc
        // wybieramy ta strzalke ktora ma najmniejsza dlugosc
        // przechodzimy do niej
        // usuwamy poprzedni node z unvisited
        // zapisujemy naszemu aktywnemu aktualnemu nodowi ze jego previous to ten ktory wlasnie usunelismy z tablicy unvisited
        // w sumie od razu mozemy nadpisac nodowi jakis totalshortestlength czy cos takiego
        // i pozniej po tym totalshoretst length bedziemy dlugosc odczytywac

        visited.add(unvisited.get(originIndex));
        unvisited.set(originIndex,null);

        while(visited.size() < unvisited.size()){
            temp = 0;
            
            Node activeNode = nodeList.get(originIndex);


            for(int i = 0; i<activeNode.getAdjacencyList().size();i+=2){
                if(activeNode.getAdjacencyList().get(i+1)>0){

                    int tempSum =sum(nodeList.get(activeNode.getAdjacencyList().get(i)));
                    Node tempNode =  nodeList.get(activeNode.getAdjacencyList().get(i)).getPreviousNode();
                    nodeList.get(activeNode.getAdjacencyList().get(i)).setPreviousNode(activeNode);
                    int tempSum2 = sum(nodeList.get(activeNode.getAdjacencyList().get(i)));
                    if(tempSum2>tempSum && tempSum!=0){
                        nodeList.get(activeNode.getAdjacencyList().get(i)).setPreviousNode(tempNode);
                    }
                }
            }
                if(unvisited.contains(activeNode)){
            
                for(int i = 0; i<activeNode.listSize();i+=2){
                    if(lengths[temp]<activeNode.getAdjacencyList().get(i+1)){
                        lengths[temp] = activeNode.getAdjacencyList().get(i+1);
                    }
                    temp++;                
                }
            }
            if(unvisited.get(originIndex)!= null){
             visited.add(unvisited.get(originIndex));
             unvisited.set(originIndex, null);
             originIndex = shortestDistance(lengths);
             clearArrayOfLenghts(lengths);
            }else{
                originIndex = getNotNull(unvisited);
            }
        }

        


        System.out.println("Najkrótsza droga do węzła " + destination + " ma długość " + sum(nodeList.get(destination)));
        System.out.println("Prowadzi ona przez następujące węzły: ");
        shortestPathNodes(nodeList.get(destination));
        Collections.reverse(path);

        for (int i: path) {
            System.out.print(" " + i + ", ");
        }
        System.out.println();
        


    }

    public void clearArrayOfLenghts(int[] lengths){
        for(int i = 0;i<lengths.length;i++){
            lengths[i] = -1;
        }
    }
    public int getNotNull(ArrayList<Node> unvisited){

        for (Node n : unvisited) {
            if(n != null){
                 return n.value;
            }
        }
        return 0;
    }

    public int shortestDistance(int[] array){
        int minVal = array[0];
        int index = 0;



        for(int i = 0; i<array.length;i++){
            if(array[i] > 0){
                if(minVal <= 0){
                minVal = array[i];
                index = i;
                }
                else{
                    if(minVal > array[i]){
                minVal = array[i];
                index = i;
                    }
                }
            }
        }

        return index;
    }

     public int sum(Node node){
         int sum = 0;

         if(node.previousNode!=null){
         sum+=sum(node.previousNode);
         sum+=node.pathToNeighbour(node.previousNode);
         }

         return sum;
     }


     public void shortestPathNodes(Node node){

        if(node.previousNode!=null){
            path.add(node.value);
            shortestPathNodes(node.previousNode);
        }

    }
    
}
