package implementations;

import interfaces.ReversedCollection;

import java.util.Iterator;

public class ReversedList<E> implements ReversedCollection<E> {

    private final static int INITIAL_CAPACITY = 2;

    private Object[] items;

    private int size;

    public ReversedList() {
        this.items = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(E element) {
        ensureCapacity();
        items[size ++] = element;
    }

    private void ensureCapacity() {
        if(size == items.length) {
            Object[] arr = new Object[items.length * 2];
            for (int i = 0; i < items.length; i++) {
                arr[i]  = items[i];
            }
            items = arr;
        }
    }

    @Override
    public E get(int index) {
        return (E) items[calcIndex(index)];
    }

    @Override
    public E removeAt(int index) {
        E item = get(index);
        shiftLeft(calcIndex(index));
        return item;
    }

    private void shiftLeft(int index) {
        for (int i = index; i < size - 1 ; i++) {
            items[i] = items[i + 1];
        }
        items[ -- size] = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return items.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public E next() {
                return get(index ++);
            }
        };
    }

    private int calcIndex(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return size - 1 - index;
    }
}
