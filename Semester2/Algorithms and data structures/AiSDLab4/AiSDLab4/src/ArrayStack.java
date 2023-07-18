import java.util.EmptyStackException;

public class ArrayStack<T> implements IStack<T> {
    T array[];
    int topIndex;
    
    public ArrayStack(int specifiedCapacity){
        topIndex = 0;
        this.array = (T[]) (new Object[specifiedCapacity]);
    }

    public ArrayStack(){
        this(16);
    }

    public boolean isEmpty(){
        return topIndex == 0;
    }

    public boolean isFull(){
        return topIndex == array.length;
    }

    public int size(){
        return topIndex;
    }

    public void push(T element) throws FullStackException{
        if(isFull()) throw new FullStackException();
        array[topIndex++] = element;
    }

    public T pop() throws EmptyStackException{
        if(isEmpty()) throw new EmptyStackException();
        return array[--topIndex];
    }
    public T top() throws EmptyStackException{
        if(isEmpty()) throw new EmptyStackException();
        return array[topIndex-1];
    }

    public void reverse(){
        T temp;
        int counter = 0;
        int size = size();
        ArrayStack<T> tempStack = new ArrayStack<>(size());
        
        for(int i = 0; i<=size-2;i++){
            temp = pop();
            while(!isEmpty()){
                tempStack.push(pop());
            }
            while(!tempStack.isEmpty()){
                if(counter == i){push(temp);}
                push(tempStack.pop());
                counter++;
            }
            counter  = 0;
        }
        
    }
}