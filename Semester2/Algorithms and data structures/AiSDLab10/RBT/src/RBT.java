import java.util.ArrayList;

public class RBT {
    Node root;
    public RBT(){
        this.root = null;
    }

    public void insert(Obj obj){
        Node newNode = new Node(obj);
        Node nodeTemp1 = null; //temp node
        Node nodeTemp2 = this.root;

        while(nodeTemp2!=null){ 
            nodeTemp1 = nodeTemp2;
            if(newNode.getValue().compareTo(nodeTemp2.getValue()) < 0)
                nodeTemp2 = nodeTemp2.getLeft();
            else
                nodeTemp2 = nodeTemp2.getRight();
        }
        newNode.setParent(nodeTemp1);
        
        if(nodeTemp1==null)
            this.root = newNode;
        else if(newNode.getValue().compareTo(nodeTemp1.getValue()) < 0)
            nodeTemp1.setLeft(newNode);
        else if (newNode.getValue().compareTo(nodeTemp1.getValue()) > 0)
            nodeTemp1.setRight(newNode);

        if (newNode.getParent() == null){
            newNode.setColor(false);
                return;
        }
        if (newNode.getParent().getParent() == null)
            return;

        fix(newNode);
    }

    public void fix(Node node){

        Node uncle;

        while (node.getParent().isColor()) // naprawiamy dopoki rodzic jest czerwony
        {
            if (node.getParent().getParent().getRight() != null && node.getParent().getValue().equals(node.getParent().getParent().getRight().getValue()))
            {
                uncle = node.getParent().getParent().getLeft();
                if (uncle != null && uncle.isColor()) //przypadek I, czyli czerwony wujek
                {
                    uncle.setColor(false);            
                    node.getParent().setColor(false);
                    node.getParent().getParent().setColor(true);
                    node = node.getParent().getParent();
                }
                else // wujek czarny
                {
                    if (node.getParent().getLeft() != null && node.getValue().equals(node.getParent().getLeft().getValue())) //przypadek 2 - element jest lewym dzieckiem
                    {
                        node = node.getParent(); 
                        rotateRight(node);
                    }
                    node.getParent().setColor(false); //przypadek 3 - element jest prawym dzieckiem
                    node.getParent().getParent().setColor(true);
                    rotateLeft(node.getParent().getParent());
                }
            }
            else // symetrycznie - rodzic jest lewym dzieckiem dziadka
            {
                uncle = node.getParent().getParent().getRight(); 
                if (uncle != null && uncle.isColor()) // przypadek 1, wujek jest czerwony
                {
                    uncle.setColor(false); 
                    node.getParent().setColor(false);
                    node.getParent().getParent().setColor(true);
                    node = node.getParent().getParent();
                }
                else
                {
                    if (node.getParent().getRight() != null && node.getValue().equals(node.getParent().getRight().getValue())) // przypadek 2 - nodeent jest prawym dzieckiem rodzica - rotacja w lewo i przejście do przypadku 3
                    {
                        node = node.getParent(); 
                        rotateLeft(node);
                    }
                    node.getParent().setColor(false); // przypadek 3 - elem jest lewym dzieckiem rodzica - przekolorowanie rodzica i dziadka
                    node.getParent().getParent().setColor(true);
                    rotateRight(node.getParent().getParent());
                }
            }
            if (node.getValue().equals(root.getValue()))
                break;
        }
        root.setColor(false); // root musi byc czarny

    }

    public void rotateLeft(Node node){
        Node right = node.getRight(); // prawe dziecko
        node.setRight(right.getLeft());

        if (right.getLeft() != null)
            right.getLeft().setParent(node);

        right.setParent(node.getParent());

        if (node.getParent() == null)
            this.root = right;

        else if (node.getValue().equals(node.getParent().getLeft().getValue()))
            node.getParent().setLeft(right);

        else
            node.getParent().setRight(right);

        right.setLeft(node);
        node.setParent(right);
    }
    private void rotateRight(Node node)
    {
        Node left = node.getLeft();
        node.setLeft(left.getRight());

        if (left.getRight() != null)
            left.getRight().setParent(node);
        

        left.setParent(node.getParent());

        if (node.getParent() == null)
            this.root = left;

        else if (node.getValue().equals(node.getParent().getRight().getValue()))
            node.getParent().setRight(left);

        else
            node.getParent().setLeft(left);

        left.setRight(node);
        node.setParent(left);
    }

 

    public void nodeCounter(){
        System.out.println("Number of nodes:" + nodeCounterHelp(root));
    }

    private int nodeCounterHelp(Node node)
    {
        int nodeCounter = 0;

        if (node == null)
            return 0;

        nodeCounter++;

        if (node.getLeft() != null)
            nodeCounter+=nodeCounterHelp(node.getLeft());
        if (node.getRight() != null)
            nodeCounter+=nodeCounterHelp(node.getRight());

        return nodeCounter;
    }

    public void printTree(){
        System.out.println("Skorowidz: ");
        inOrder(this.root);

    }

    private void inOrder(Node node)
    {
        if (node.getLeft() != null)
        {
            inOrder(node.getLeft());
        }


        // to mozna poprawic
        System.out.print(node.getValue() + " ");
        for (int i: node.getLines()) {
            System.out.print(i);
            System.out.print(", ");
        }
        System.out.println();

        if (node.getRight() != null)
        {
            inOrder(node.getRight());
        }
    }

    public void breadthFirstSearch() throws EmptyQueueException{
        System.out.println("----------------BFS-----------------------");

        levelOrder(this.root);
        
        System.out.println("------------------------------------------");
    }

    public void levelOrder(Node node) throws EmptyQueueException
    {

        if (node != null)
        {
            ArrayList<Node> que = new ArrayList<>();
            que.add(node);
            while (!que.isEmpty())
            {
                Node n = que.get(0);

        // to mozna poprawic
        System.out.print(n.getValue() + " ");
        for (int i: n.getLines()) {
            System.out.print(i);
            System.out.print(", ");
        }
        System.out.println();

                if (n.getLeft() != null)
                    que.add(n.getLeft());
                if (n.getRight() != null)
                    que.add(n.getRight());
            que.remove(0);

            }
        }
    }






private void repairDelete(Node elem)
{
    Node w;
    while (elem != null && !elem.getValue().equals(root.getValue()) && !elem.isColor())
    {
        if (elem.getValue().equals(elem.getParent().getLeft().getValue())) // element jest lewym dzieckiem rodzica
        {
            w = elem.getParent().getRight(); // brat - prawe dziecko rodzica
            if (w.isColor()) // przypadek I - brat czerwony - przekolorowanie i rotacja w lewo
            {
                w.setColor(false);
                elem.getParent().setColor(true);
                rotateLeft(elem.getParent());
                w = elem.getParent().getRight(); // aktualizujemy brata - po rotacji spadł w dół, więc jest prawym dzieckiem
            }
            if (!w.getLeft().isColor() && !w.getRight().isColor()) // przypadek II - obydwoje dzieci brata są czarne
            {
                w.setColor(true); // przekolorowanie brata
                elem = elem.getParent(); // przesuwamy się w górę o 1 poziom - do rodzica
            }
            else
            {
                if (!w.getRight().isColor()) // przypadek III - prawe dziecko brata czarne, lewe - czerwone
                {
                    w.getLeft().setColor(false); // przekolorowanie
                    w.setColor(true);
                    rotateRight(w); // rotacja w prawo
                    w = elem.getRight().getParent(); // przygotowanie do przypadku IV - aktualizacja brata po rotacji
                }
                // przypadek IV - prawe dziecko brata czerwone, lewe - czarne
                w.setColor(elem.getParent().isColor()); // przekolorowanie brata na kolor rodzica
                elem.getParent().setColor(false);
                w.getRight().setColor(false);
                rotateLeft(elem.getParent()); // rotacja w lewo
                elem = root; // przesuwamy analizę do korzenia
            }
        }
        else // lustrzane odbicie
        {
            w = elem.getParent().getLeft();
            if (elem.getValue().equals(elem.getParent().getRight().getValue()))
            {
                w = elem.getParent().getLeft();
            }
            if (w.isColor())
            {
                w.setColor(false);
                elem.getParent().setColor(true);
                rotateRight(elem.getParent());
                w = elem.getParent().getLeft();
            }
            if (!w.getRight().isColor() && !w.getLeft().isColor())
            {
                w.setColor(true);
                elem = elem.getParent();
            }
            else
            {
                if (!w.getLeft().isColor())
                {
                    w.getRight().setColor(false);
                    w.setColor(true);
                    rotateLeft(w);
                    w = elem.getLeft().getParent();
                }
                w.setColor(elem.getParent().isColor());
                elem.getParent().setColor(false);
                w.getLeft().setColor(false);
                rotateRight(elem.getParent());
                elem = root;
            }
        }
    }
    if (elem != null)
        elem.setColor(false);
}

private Node minVal(Node node)
{
    while (node.getLeft() != null)
        node = node.getLeft();
    return node;
}

private Node nextCan(Node node){
    if (node.getRight() != null)
    {
        return minVal(node.getRight());
    }
    Node y = node.getParent();
    while (y!=null && node.getValue().equals(y.getRight().getValue()))
    {
        node = y;
        y = y.getParent();
    }
    return y;
}

private void swap(Node x, Node y)
{
    String v1 = x.getValue();
    String v2 = y.getValue();
    ArrayList<Integer> l1 = x.getLines();
    ArrayList<Integer> l2 = y.getLines();

    x.setValue(v2);
    y.setValue(v1);
    x.setLines(l2);
    y.setLines(l1);
}

    private Node searchHelp(Node node, String value){
        if (node == null || value.equals(node.getValue()))
            return node;
        if (value.compareTo(node.getValue()) < 0)
            return searchHelp(node.getLeft(), value);
        return searchHelp(node.getRight(), value);
    }

    public Node search(String value){
        return searchHelp(root, value);
    }


    public void delete(String value){
    Node z = this.search(value);
    Node x = null;;
    Node y = null;
    if (z == null){
        System.out.println("Wyraz nie znaleziony :()");
        return;
    }
    else{
        if (z.getLeft() == null || z.getRight() == null)
            y = z; // nasz element jest ostatni (lisc)
        else
            y = nextCan(z); // element nie jest liściem, wiec zastepujemy go
        if (y.getLeft()!=null)
            x = y.getLeft(); 
        else
            x = y.getRight(); 
        if (x!=null)
            x.setParent(y.getParent()); // przestawiamy rodzica (na rodzica usuniętego elementu)
        if (y.getParent() == null)
            this.root = x; // zastepowanie korzenia
        else
        {
            if (y.getValue().equals(y.getParent().getLeft().getValue()))
                y.getParent().setLeft(x); // x musi też być lewym dzieckiem
            else
                y.getParent().setRight(x); // x musi też być prawym dzieckiem
        }
        if (!y.getValue().equals(z.getValue()))
            swap(z, y); // zamiana usuniętego elementu z jego następnikiem (jeśli usunięty element nie był liściem)
    }
    if (!y.isColor()) // jeżeli usunięto węzeł czarny - naprawiamy drzewo (od rodzica)
        repairDelete(x);
    }
}
