package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class DoublyLinkedList<E> implements LinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> previous;

        public Node(E value) {
            this.element = value;
        }
    }

    public DoublyLinkedList() {
        this.size = 0;
    }

    @Override
    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        if (isEmpty()) {
            this.head = this.tail = newNode;
        }else {
            newNode.next = this.head;
            this.head.previous = newNode;
            this.head = newNode;
        }
        this.size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);
        if (isEmpty()) {
            addFirst(element);
        } else {
            newNode.previous = this.tail;
            tail.next = newNode;
            tail = newNode;
            this.size++;
        }
    }

    @Override
    public E removeFirst() {
        E element = getFirst();
        if (this.size == 1) {
            this.head = this.tail = null;
        } else {
            Node<E> currentHead = this.head;
            this.head = currentHead.next;
            this.head.previous = null;
            currentHead.next = null;
        }
        this.size--;
        return element;
    }

    private void ensureNotEmpty() {
        if (this.size == 0) {
            throw new IllegalStateException("Illegal remove for empty LinkedList");
        }
    }

    @Override
    public E removeLast() {
        E element = getLast();
        if (this.size == 1) {
            return removeFirst();
        }
        Node<E> currentTail = this.tail;
        this.tail = currentTail.previous;
        this.tail.next = null;
        currentTail.previous = null;
        this.size--;
        return element;
    }

    @Override
    public E getFirst() {
        ensureNotEmpty();
        return this.head.element;
    }

    @Override
    public E getLast() {
        ensureNotEmpty();
        return this.tail.element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
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
}
