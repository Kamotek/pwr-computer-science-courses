public class HuffmanNode implements Comparable<HuffmanNode>{
    int weight;
    char value;

    String actualNumber;

    public String getActualNumber() {
        return actualNumber;
    }


    public void setActualNumber(String actualNumber) {
        this.actualNumber = actualNumber;
    }


    HuffmanNode left;
    HuffmanNode right;


    public HuffmanNode(int weight, HuffmanNode left, HuffmanNode right) {
        this.weight = weight;
        this.value = '`';
        this.left = left;
        this.right = right;
    }


    public HuffmanNode(int weight, char value) {
        this.weight = weight;
        this.value = value;
        this.left = null;
        this.right = null;
    }


    @Override
    public String toString() {
        return "HuffmanNode [weight=" + weight + ", value=" + value + "]" + "Actual number = " + getActualNumber();
    }


    



    @Override
    public int compareTo(HuffmanNode arg0) {
        
       

        return this.getWeight() - arg0.getWeight();
    }


    public int getWeight() {
        return weight;
    }


    public void setWeight(int weight) {
        this.weight = weight;
    }


    public char getValue() {
        return value;
    }


    public void setValue(char value) {
        this.value = value;
    }


    public HuffmanNode getLeft() {
        return left;
    }


    public void setLeft(HuffmanNode left) {
        this.left = left;
    }


    public HuffmanNode getRight() {
        return right;
    }


    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    

    


}
