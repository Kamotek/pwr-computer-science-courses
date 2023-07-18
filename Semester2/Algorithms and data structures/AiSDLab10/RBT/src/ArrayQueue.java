
import java.util.ArrayList;

public class ArrayQueue<T>{

    ArrayList<T> queue;

    public ArrayQueue()
    {
        queue = new ArrayList<>();
    }

    
    public boolean isEmpty()
    {
        return queue.isEmpty();
    }

    
    public boolean isFull()
    {
        return false;
    }

    
    public T dequeue() throws EmptyQueueException
    {
        if (queue.isEmpty())
            throw new EmptyQueueException();
        else
        {
            return queue.remove(0);
        }
    }

    
    public void enqueue(T elem)
    {
        queue.add(elem);
    }

    
    public int size()
    {
        return queue.size();
    }


}

