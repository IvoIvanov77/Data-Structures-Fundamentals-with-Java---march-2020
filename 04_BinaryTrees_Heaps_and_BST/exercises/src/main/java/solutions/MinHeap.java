package solutions;

import interfaces.Decrease;
import interfaces.Heap;
import model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinHeap<E extends Comparable<E> & Decrease<E>> implements Heap<E> {

    private List<E> items;

    public MinHeap() {
        this.items = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.items.size();
    }

    @Override
    public void add(E element) {
        items.add(element);
        heapifyUp(lastIndex());
    }

    @Override
    public E peek() {
        if(size() == 0) {
            throw new IllegalStateException("Empty queue");
        }
        return this.items.get(0);
    }

    @Override
    public E poll() {
        E item = peek();
        Collections.swap(items, 0, lastIndex());
        items.remove(lastIndex());
        heapifyDown(0);
        return item;
    }

    @Override
    public void decrease(E element) {
        int index = this.items.indexOf(element);
        E item = items.get(index);
        item.decrease();
        heapifyUp(index);
    }

    private void heapifyUp(int index) {
        while (index > 0 && isLess(index, getParent(index))) {
            Collections.swap(items, index, getParent(index));
            index = getParent(index);
        }
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

    private boolean isLess(int first, int second) {
        return items.get(first).compareTo(items.get(second)) < 0;
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
