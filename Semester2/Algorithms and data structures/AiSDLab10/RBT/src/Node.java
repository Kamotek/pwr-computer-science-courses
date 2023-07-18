import java.util.ArrayList;

public class Node {
   
    String value;
    ArrayList<Integer> lines;
    boolean color; // T red / F black
    Node left;
    Node right;
    Node parent;

    public Node(Obj obj) {
        this.value = obj.value;
        this.lines = new ArrayList<Integer>();
        this.lines = obj.rows;
        this.color = true;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    // ---

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ArrayList<Integer> getLines() {
        return lines;
    }

    public void setLines(ArrayList<Integer> lines) {
        this.lines = lines;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }   

    

    
}
