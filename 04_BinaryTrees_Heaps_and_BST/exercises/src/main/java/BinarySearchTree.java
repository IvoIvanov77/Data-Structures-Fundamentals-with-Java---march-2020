import java.util.ArrayList;
import java.util.function.Consumer;

import java.util.List;

public class BinarySearchTree<E extends Comparable<E>> {

    private Node<E> root;

    private int size;

    public static class Node<E> {
        private E value;
        private Node<E> leftChild;
        private Node<E> rightChild;

		public Node(E value) {
            this.value = value;
        }

        public Node<E> getLeft() {
            return this.leftChild;
        }

        public Node<E> getRight() {
            return this.rightChild;
        }

        public E getValue() {
            return this.value;
        }
    }
	
	public void eachInOrder(Consumer<E> consumer) {
        eachInOrder(this.root, consumer);
    }

    public Node<E> getRoot() {
        return this.root;
    }

    public void insert(E element) {
        if(this.root == null) {
            this.root = new Node<>(element);
        } else {
            insertNode(this.root, element);
        }
        size ++;
    }

    public boolean contains(E element) {
        return find(element) != null;
    }
    public BinarySearchTree<E> search(E element) {
        Node<E> node = find(element);
        if (node == null) {
            throw new IllegalArgumentException("The tree is empty");
        }
        BinarySearchTree<E> tree = new BinarySearchTree<>();
        clone(tree, node);
        return tree;
    }

    public List<E> range(E first, E second) {
        List<E> result = new ArrayList<>();
        this.eachInOrder(e -> {
            if(e.compareTo(first) >= 0 && e.compareTo(second) <= 0) {
                result.add(e);
            }
        });

        return result;
    }

    public void deleteMin() {
        if (this.root == null) {
            throw new IllegalArgumentException("The tree is empty");
        }
        if (this.root.getLeft() == null){
            this.root = this.root.getRight();
            size --;
            return;
        }
        Node<E> current = this.root;
        while (current.leftChild != null) {
            if (current.getLeft().getLeft() == null){
                current.leftChild = current.getLeft().getRight();
                size --;
                return;
            }
            current = current.leftChild;
        }
    }

    public void deleteMax() {
        if (this.root == null) {
            throw new IllegalArgumentException("The tree is empty");
        }
        if (this.root.getRight()== null){
            this.root = this.root.leftChild;
            size --;
            return;
        }
        Node<E> current = this.root;
        while (current.rightChild != null) {
            if (current.rightChild.rightChild == null){
                current.rightChild = current.getRight().getLeft();
                size --;
                return;
            }
            current = current.rightChild;
        }
    }

    public int count() {
        return size;
    }

    public int rank(E element) {
        return rank(this.root, element);
    }

    public E ceil(E element) {
        return ceil(this.root, element);
    }

    public E floor(E element) {
        return flour(this.root, element);
    }

    private int rank(Node<E> root, E element) {
        int count = 0;
        if(root != null) {
            if(root.getValue().compareTo(element) < 0) {
                count += 1;
                count += getElementsCount(root.leftChild);
                count += rank(root.rightChild, element);
            } else  {
                count += rank(root.getLeft(), element);
            }
        }
        return count;
    }

    private int getElementsCount(Node<E> node) {
        int count = 0;
        if(node != null) {
            count += 1;
            count += getElementsCount(node.getLeft());
            count += getElementsCount(node.getRight());
        }
        return count;
    }

    private E flour(Node<E> root, E element) {
        E currentResult = null;
        while (root != null) {
            if(root.getValue().compareTo(element) >= 0) {
                root = root.getLeft();
            } else {
                if(currentResult == null || root.getValue().compareTo(currentResult) > 0) {
                    currentResult = root.getValue();
                }
                root = root.getRight();
            }
        }
        return currentResult;
    }

    private E ceil(Node<E> root, E element) {
        E currentResult = null;
        while (root != null) {
            if(root.getValue().compareTo(element) <= 0) {
                root = root.getRight();
            } else {
                if(currentResult == null || root.getValue().compareTo(currentResult) < 0) {
                    currentResult = root.getValue();
                }
                root = root.getLeft();
            }
        }
        return currentResult;
    }

    private void eachInOrder(Node<E> root, Consumer<E> consumer) {
        if(root == null) {
            return;
        }
        eachInOrder(root.leftChild, consumer);
        consumer.accept(root.value);
        eachInOrder(root.rightChild, consumer);
    }

    private void insertNode(Node<E> root, E element) {
        if(element.compareTo(root.value) > 0) {
            if(root.rightChild == null) {
                root.rightChild = new Node<>(element);
            } else {
                insertNode(root.rightChild, element);
            }
        } else if (element.compareTo(root.value) < 0) {
            if(root.leftChild == null) {
                root.leftChild = new Node<>(element);
            } else {
                insertNode(root.leftChild, element);
            }
        }
    }

    private Node<E> find(E element) {
        Node<E> node = this.root;
        while (node != null) {
            int compare = element.compareTo(node.value);
            if(compare == 0) {
                return node;
            }
            if (compare > 0) {
                node = node.rightChild;
            } else {
                node = node.leftChild;
            }
        }
        return null;
    }

    private void clone(BinarySearchTree<E> tree, Node<E> root) {
        if(root == null) {
            return;
        }
        tree.insert(root.value);
        clone(tree, root.leftChild);
        clone(tree, root.rightChild);
    }
}
