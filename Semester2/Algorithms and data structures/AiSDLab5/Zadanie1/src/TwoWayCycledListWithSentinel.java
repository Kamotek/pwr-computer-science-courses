import java.io.Serializable;
import java.util.AbstractList;
import java.util.Iterator;

public class TwoWayCycledListWithSentinel<T> extends AbstractList<T> implements Serializable {

    private class Element implements Serializable{

        private T value;
        private Element next;
        private Element prev;

        public T getValue() { return value; }

        public void setValue(T value) { this.value = value; }

        public Element getNext() {return next;}

        public void setNext(Element next) {this.next = next;}

        public Element getPrev() {return prev;}

        public void setPrev(Element prev) {this.prev = prev;}

        Element(T data){this.value=data;}

        public void insertAfter(Element elem)
        {
            elem.setNext(this.getNext());
            elem.setPrev(this);
            this.getNext().setPrev(elem);
            this.setNext(elem);
        }

        public void insertBefore(Element elem)
        {
            elem.setNext(this);
            elem.setPrev(this.getPrev());
            this.getPrev().setNext(elem);
            this.setPrev(elem);
        }

        public void remove()
        {
            this.getNext().setPrev(this.getPrev());
            this.getPrev().setNext(this.getNext());
        }
    }

    Element sentinel=null;

    public TwoWayCycledListWithSentinel()
    {
        sentinel=new Element(null);
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);
    }

    private Element getElement(int index)
    {
        Element elem=sentinel.getNext();
        int counter=0;

        while(elem!=sentinel && counter<index)
        {
            counter++;
            elem=elem.getNext();
        }

        if(elem==sentinel)
            throw new IndexOutOfBoundsException();

        return elem;
    }

    private Element getElement(T value)
    {
        Element elem=sentinel.getNext();
        int counter=0;

        while(elem!=sentinel && !value.equals(elem.getValue()))
        {
            counter++;
            elem=elem.getNext();
        }

        if(elem==sentinel)
            return null;

        return elem;
    }

    public boolean isEmpty()
    {
        return sentinel.getNext()==sentinel;
    }

    public void clear()
    {
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);
    }

    public boolean contains(Object value)
    {
        return indexOf(value)!=-1;
    }

    public T get(int index)
    {
        Element elem=getElement(index);
        return elem.getValue();
    }

    public T set(int index, T value)
    {
        Element elem=getElement(index);
        T retValue=elem.getValue();
        elem.setValue(value);
        return retValue;
    }

    public boolean add(T value)
    {
        Element newElem=new Element(value);
        sentinel.insertBefore(newElem);
        return true;
    }

    public void add(int index, T value)
    {
        Element newElem=new Element(value);

        if(index==0) sentinel.insertAfter(newElem);
        else
            {
            Element elem=getElement(index-1);
            elem.insertAfter(newElem);
            }
    }

    public int indexOf(Object value)
    {
        Element elem=sentinel.getNext();
        int counter=0;

        while(elem!=sentinel && !elem.getValue().equals(value))
        {
            counter++;
            elem=elem.getNext();
        }

        if(elem==sentinel)
            return -1;
        return counter;
    }

    public T remove(int index)
    {
        Element elem=getElement(index);
        elem.remove();
        return elem.getValue();
    }

    public boolean remove(Object value)
    {
        Element elem=getElement((T) value);
        if(elem==null) return false;
        elem.remove();
        return true;
    }

    public int size()
    {
        Element elem=sentinel.getNext();
        int counter=0;
        while(elem!=sentinel){
            counter++;
            elem=elem.getNext();}
        return counter;
    }

    public Iterator<T> iterator() {
        return new ListIterator();}


    private class ListIterator implements Iterator<T>
    {
        Element _current=sentinel;

        public boolean hasNext()
        {
            return _current.getNext()!=sentinel;
        }

        public T next()
        {
            _current=_current.getNext();
            return _current.getValue();
        }
    }





}
