package implementations;

import interfaces.List;

import java.util.Iterator;

public class ArrayList<E> implements List<E> {

    private final static int INITIAL_CAPACITY = 4;

    private Object[] items;
    private int size;
    private int capacity;

    public ArrayList() {
        this.items = new Object[INITIAL_CAPACITY];
        this.size = 0;
        this.capacity = INITIAL_CAPACITY;
    }

    @Override
    public boolean add(E element) {
        ensureCapacity();
        items[size ++] = element;
        return true;
    }

    @Override
    public boolean add(int index, E element) {
        ensureCapacity();
        shiftRight(index);
        items[index] = element;
        size ++;
        return true;
    }

    @Override
    public E get(int index) {
        checkIsValid(index);
        return (E) items[index];
    }

    @Override
    public E set(int index, E element) {
        checkIsValid(index);
        E previousItem = (E) items[index];
        items[index] = element;
        return previousItem;
    }

    @Override
    public E remove(int index) {
        checkIsValid(index);
        E item = (E) items[index];
        shiftLeft(index);
        size --;
        return item;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if(items[i].equals(element)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if(items[i].equals(element)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size() && items[index] != null;
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }

    private void ensureCapacity() {
        if(size >= capacity){
            Object[] tmp = new Object[capacity * 2];
            for (int i = 0; i < items.length; i++) {
                tmp[i] = items[i];
            }
            items = tmp;
            capacity *= 2;
        }
    }

    private void shiftLeft(int index) {
        for (int i = index; i < size - 1; i++) {
            items[i] = items[i + 1];
        }
        items[size - 1] = null;
    }

    private void shiftRight(int index) {
        for (int i = size; i >= index; i--) {
            items[i] = items[i - 1];
        }
    }

    private void checkIsValid(int index){
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }
}
