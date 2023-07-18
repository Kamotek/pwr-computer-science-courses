import java.util.ArrayList;
import java.util.Arrays;

public class Node {
    

    ArrayList<Integer> adjacencyList;
    int value;

    Node previousNode;

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    public Node(int value, int direction, int length){
        this.value = value;
        this.adjacencyList = new ArrayList<>(10);
        
        this.previousNode = null;

        for(int i = 0; i<10;i+=2){
            adjacencyList.add(i, i/2);
            adjacencyList.add(i+1, -1);
        }    
        

        adjacencyList.set(direction*2, direction);
        adjacencyList.set(direction*2+1, length);
    }

    public Node(int value){
        this.value = value;
        this.adjacencyList = new ArrayList<>(10);

        this.previousNode = null;

        for(int i = 0; i<10;i+=2){
            adjacencyList.add(i, i/2);
            adjacencyList.add(i+1, -1);
        }    
        
    }

    public void addAdjacency(int direction, int length){



        adjacencyList.set(direction*2, direction);
        adjacencyList.set(direction*2+1, length);
    }

    public void showAdjacencyListOfNode(){
        String thisNodeCityName = getCityName(getValue());
        for(int i = 0; i<adjacencyList.size();i = i+2){
            if(adjacencyList.get(i+1) == -1 || adjacencyList.get(i+1) == 0) continue;
            String destinationCityName = getCityName(adjacencyList.get(i));
            System.out.println("Połączenie z grafu " + getValue() + "(" + thisNodeCityName + ") do grafu " + adjacencyList.get(i) + "(" + destinationCityName + ") o długości " + adjacencyList.get(i+1) + " (km)");
        }
    }

    public String getCityName(int nodeValue){
        switch(nodeValue){
            case 0:
                return "Wrocław";
            case 1:
                return "Oława";
            case 2:
                return "Brzeg";
            case 3:
                return "Nysa";
            case 4: 
                return "Opole";
            default:   
                return "---";
        }


    }

    public int pathToNeighbour(Node node){

        int x = -1;
        for(int i = 0;i<node.getAdjacencyList().size(); i+=2){
            if(node.adjacencyList.get(i) == this.getValue()){
                x = node.adjacencyList.get(i+1);
            }
        } 

        return x;
    }

    public int listSize(){
        return getAdjacencyList().size();
    }

    public ArrayList<Integer> getAdjacencyList() {
        return adjacencyList;
    }

    public int getValue() {
        return value;
    }
}
