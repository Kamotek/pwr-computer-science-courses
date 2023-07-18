public class ListStack <T> implements IStack<T>{

    OneWayLinkedListWithHead<T> _list;

    public ListStack()
    {
        _list = new OneWayLinkedListWithHead<T>();
    }

    @Override
    public boolean isEmpty()
    {
        return _list.isEmpty();

    }

    @Override
    public boolean isFull()
    {
        return false;
    }

    @Override
    public T pop() throws EmptyStackException
    {
        T value=_list.remove(0);
        if(value==null) throw new EmptyStackException();
        return value;
    }

    @Override
    public void push(T elem)
    {
        _list.add(0,elem);
    }

    @Override
    public int size()
    {
        return _list.size();
    }

    @Override
    public T top() throws EmptyStackException
    {
        T value=_list.get(0);
        if(value==null) throw new EmptyStackException();
        return value;
    }

}