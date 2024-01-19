1.
public class FullException extends Exception {
    public FullException() {
    }

    public FullException(String message) {
        super(message);
    }
}



public class EmptyException extends Exception {
    public EmptyException() {
    }

    public EmptyException(String message) {
        super(message);
    }
}



public class EmptyException extends Exception {
    public EmptyException() {
    }

    public EmptyException(String message) {
        super(message);
    }
}


public static void main(String[] args) throws FullException, EmptyException {
    MyQueue<String> queue = new CyclicArrayQueue<>(3);
    queue.enqueue("kot");
    queue.enqueue("pies");
    queue.enqueue("krowa");
    System.out.println(queue.first());
    queue.dequeue();
    queue.enqueue("owca");
    System.out.println(queue.first());
    queue.dequeue();
    queue.enqueue("kangur");
    System.out.println(queue.first());
}
