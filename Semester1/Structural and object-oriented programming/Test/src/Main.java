import com.sun.source.tree.Tree;

import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        TreeSet<Integer> intsy = new TreeSet<>();

        intsy.add(1);
        intsy.add(3);
        intsy.add(1);
        intsy.add(3); intsy.add(1);
        intsy.add(3);

        for (Integer i: intsy
             ) {
            System.out.println(i);
        }

    }
}