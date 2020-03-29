package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<E> implements LinkedList<E> {

    private Node<E> first;

    private Node<E> last;

    private int size;

    public SinglyLinkedList() {
        this.size = 0;
    }

    @Override
    public void addFirst(E element) {
        Node<E> node = new Node<>(element);
        if(isEmpty()) {
            this.first = node;
            this.last = node;
        }else {
            node.next = first;
            first.previous = node;
            first = node;
        }
        size ++;
    }

    @Override
    public void addLast(E element) {
        Node<E> node = new Node<>(element);
        if(isEmpty()) {
            this.first = node;
            this.last = node;
        }else {
            node.previous = last;
            last.next = node;
            last = node;
        }
        size ++;
    }

    @Override
    public E removeFirst() {
        E element = getFirst();
        this.first = this.first.next;
        this.first.previous = null;
        size --;
        return element;
    }

    @Override
    public E removeLast() {
        E element = getLast();
        this.last = this.last.previous;
        this.last.next = null;
        size --;
        return element;
    }

    @Override
    public E getFirst() {
        if(isEmpty()) {
            throw new IllegalStateException("Collection is empty");
        }
        return first.getElement();
    }

    @Override
    public E getLast() {
        if(isEmpty()) {
            throw new IllegalStateException("Collection is empty");
        }
        return last.getElement();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private Node<E> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }

    private class Node<T>{
        private T element;
        private Node<T> next;
        private Node<T> previous;

        Node(T element) {
            this.element = element;
        }

        T getElement() {
            return element;
        }
    }
}
