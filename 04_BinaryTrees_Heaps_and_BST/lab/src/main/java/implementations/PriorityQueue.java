package implementations;

import interfaces.AbstractQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {

    private List<E> items;

    public PriorityQueue() {
        this.items = new ArrayList<>();
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public void add(E element) {
        items.add(element);
        heapifyUp(lastIndex());
    }

    @Override
    public E peek() {
        if(items.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return items.get(0);
    }

    @Override
    public E poll() {
        E head = peek();
        Collections.swap(items, 0, lastIndex());
        items.remove(lastIndex());
        heapifyDown(0);
        return head;
    }

    private void heapifyDown(int index) {
        while (index < size() / 2) {
            int child = leftChild(index);
            if(rightChild(index) < size() && isLess(rightChild(index), child)) {
                child = rightChild(index);
            }
            if(isLess(child, index)){
                Collections.swap(items, index, child);
                index = child;
            } else {
                break;
            }
        }
    }

    private void heapifyUp(int index) {
        while (index > 0 && isLess(index, getParent(index))) {
            Collections.swap(items, index, getParent(index));
            index = getParent(index);
        }
    }

    private boolean isLess(int first, int second) {
        return items.get(second).compareTo(items.get(first)) < 0;
    }

    private int getParent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return leftChild(index) + 1;
    }

    private int lastIndex() {
        return items.size() - 1;
    }
}
