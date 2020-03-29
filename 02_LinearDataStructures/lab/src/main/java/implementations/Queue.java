package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {

    private Node<E> head;

    private Node<E> tail;

    private int size;

    public Queue() {
        this.size = 0;
    }

    @Override
    public void offer(E element) {
        Node<E> node = new Node<>(element);
        if (size == 0){
            this.head = node;
            this.tail = node;
        } else {
            this.tail.next = node;
            this.tail = node;
        }
        size ++;
    }

    @Override
    public E poll() {
        E element = peek();
        Node<E> node = this.head.next;
        this.head.next = null;
        this.head = node;
        this.size --;
        return element;
    }

    @Override
    public E peek() {
        if(isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return this.head.getElement();
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

            private Node<E> current = head;

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

    private class Node<E>{
        private E element;
        private Node<E> next;

        Node(E element) {
            this.element = element;
        }

        E getElement() {
            return element;
        }
    }
}
