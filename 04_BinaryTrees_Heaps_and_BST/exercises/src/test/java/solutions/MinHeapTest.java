package solutions;

import interfaces.Heap;
import model.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class MinHeapTest {

    private MinHeap<Product> minHeap;

    private List<Integer> elements;

    @Before
    public void setUp() {
        this.minHeap = new MinHeap<>();
        this.elements = new ArrayList<>(List.of(15, 25, 6, 9, 5, 8, 17, 16));
        for (Integer element : elements) {
            this.minHeap.add(new Product(element));
        }
    }

    @Test
    public void testHeapifyUpAddOne() {
        Heap<Product> heap = new MinHeap<>();
        heap.add(new Product(13));
        assertEquals(13, heap.peek().getPrice());
    }

    @Test
    public void testHeapifyUpAddMany() {
        assertEquals(5, minHeap.peek().getPrice());
    }

    @Test
    public void testSizeShouldBeCorrect() {
        assertEquals(8, this.minHeap.size());
    }

    @Test
    public void testDecreaseSingleElement() {
        MinHeap<Product> heap = new MinHeap<>();

        heap.add(new Product(3));

        heap.decrease(new Product(3));

        assertEquals(2, heap.peek().getPrice());
    }

    @Test
    public void testPoll() {
        List<Integer> expected = this.elements.stream().sorted().collect(Collectors.toList());
        for (Integer integer : expected) {
            assertSame(integer, minHeap.poll().getPrice());
        }
    }
}