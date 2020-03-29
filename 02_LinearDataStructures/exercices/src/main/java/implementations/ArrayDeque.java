package implementations;

import interfaces.Deque;

import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E> {
    private static final int INITIAL_CAPACITY = 7;

    private Object[] items;

    private int size;

    private int head;

    private int tail;

    public ArrayDeque() {
        this.items = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(E element) {
        addLast(element);
    }

    @Override
    public void offer(E element) {
        addLast(element);
    }

    @Override
    public void addFirst(E element) {
        if(isEmpty()){
            addLast(element);
        }else {
            ensureCapacity();
            items[-- head] = element;
            size ++;
        }

    }

    @Override
    public void addLast(E element) {
        if(isEmpty()){
            int middle = items.length / 2;
            items[middle] = element;
            head = tail = middle;
        }else {
            ensureCapacity();
            items[++ tail] = element;
        }
        size ++;
    }

    @Override
    public void push(E element) {
        addFirst(element);
    }

    @Override
    public void insert(int index, E element) {
        int indexToInsert = calcIndex(index);
        if(indexToInsert == head) {
            addFirst(element);
        } else {
            shiftRight(indexToInsert);
            items[calcIndex(index)] = element;
        }
    }

    private void shiftRight(int index) {
        E item = getAt(tail);
        for (int i = tail; i > index ; i --) {
            items[i] = items[i - 1];
        }
        addLast(item);
    }

    @Override
    public void set(int index, E element) {
        int i = calcIndex(index);
        items[i] = element;
    }

    @Override
    public E peek() {
        if(isEmpty()) {
            return null;
        }
        return getAt(head);
    }

    @Override
    public E poll() {
        return removeFirst();
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    @Override
    public E get(int index) {
        return getAt(calcIndex(index));
    }

    @Override
    public E get(Object object) {
        int index = getIndex(object);
        return index == -1 ? null : getAt(index);
    }

    @Override
    public E remove(int index) {
        int indexToRemove = calcIndex(index);
        E item = getAt(indexToRemove);
        shiftLeft(indexToRemove);
        return item;
    }

    @Override
    public E remove(Object object) {
        int indexToRemove = getIndex(object);
        if(indexToRemove == -1) {
            return null;
        }
        E item = get(object);
        shiftLeft(indexToRemove);
        return item;
    }

    @Override
    public E removeFirst() {
        if(isEmpty()) {
            return null;
        }
        E item = peek();
        if(size == 1) {
            items[head] = null;
            head = tail = 0;
        }else {
            items[head ++] = null;
        }
        size --;
        return item;
    }

    @Override
    public E removeLast() {
        if(isEmpty()) {
            return null;
        }
        if (size == 1) {
            return removeFirst();
        }
        E item = getAt(tail);
        items[tail --] = null;
        size --;
        return item;
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
    public void trimToSize() {
        Object[] array = new Object[size];
        int index = 0;
        for (int i = head; i <= tail; i++) {
            array[index ++] = items[i];
        }
        items = array;
        head = 0;
        tail = size - 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int index = head;
            @Override
            public boolean hasNext() {
                return index <= tail;
            }

            @Override
            public E next() {
                return getAt(index ++);
            }
        };
    }

    private int calcIndex(int index) {
        int result = head + index;
        if(isEmpty() || result < head || result > tail) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private E getAt(int index){
        if(index < head || index > tail) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return (E) items[index];
    }

    private int getIndex(Object object) {
        for (int i = head; i <= tail; i++) {
            if (items[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    private void shiftLeft(int index) {
        for (int i = index; i < tail; i++) {
            items[i] = items[i + 1];
        }
        removeLast();
    }

    private void ensureCapacity() {
        if(tail == items.length - 1 || head == 0) {
            Object[] newItems = new Object[items.length * 2 + 1];
            int index = head;
            head = newItems.length / 2 - size / 2;
            tail = head + size - 1;
            for (int i = head; i <= tail ; i++) {
                newItems[i] = items[index ++];
            }
            items = newItems;
        }
    }
}
