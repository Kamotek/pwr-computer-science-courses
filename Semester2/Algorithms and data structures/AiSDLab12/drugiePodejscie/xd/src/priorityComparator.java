import java.util.Comparator;

class priorityComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y)
    {
        if(x.weight == y.weight){
            if(y.value == '`'){return -1;}
            if(x.value == '`'){return 1;}
            if(y.value > x.value){return -1;}
            if(y.value < x.value){return 1;}

        }
            
        return x.weight - y.weight;
    }
}