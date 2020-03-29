package implementations;

import interfaces.AbstractStack;

import java.util.Iterator;

public class Stack<E> implements AbstractStack<E> {

    private Node<E> top;

    private int size;

    public Stack() {
        this.size = 0;
    }

    @Override
    public void push(E element) {
        this.top = new Node<>(element, top);
        size ++;
    }

    @Override
    public E pop() {
        E element = peek();
        Node<E> node = top.previous;
        top.previous = null;
        top = node;
        size --;
        return element;
    }

    @Override
    public E peek() {
        if(isEmpty()){
            throw new IllegalStateException("Empty stack");
        }
        return top.getElement();
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
            private Node<E> current = top;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E element = current.element;
                current = current.previous;
                return element;
            }
        };
    }

    private class Node<E>{
        private E element;
        private Node<E> previous;

        Node(E element) {
            this.element = element;
        }

        Node(E element, Node<E> previous) {
            this(element);
            this.previous = previous;
        }

        E getElement() {
            return element;
        }
    }
}
