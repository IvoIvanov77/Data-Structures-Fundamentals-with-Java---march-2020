package implementations;

import interfaces.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxHeap<E extends Comparable<E>> implements Heap<E> {

    private List<E> items;

    public MaxHeap() {
        this.items = new ArrayList<>();
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public void add(E element) {
        items.add(element);
        heapifyUp(size() - 1);
    }

    @Override
    public E peek() {
        if(items.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return items.get(0);
    }

    private void heapifyUp(int index) {
        while (index > 0 && isLess(index, getParent(index))) {
            Collections.swap(items, index, getParent(index));
            index = getParent(index);
        }
    }

    private boolean isLess(int parent, int index) {
        return items.get(index).compareTo(items.get(parent)) < 0;
    }

    private int getParent(int index) {
        return (index -1) / 2;
    }
}
