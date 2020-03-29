package implementations;

import interfaces.AbstractBinarySearchTree;

public class BinarySearchTree<E extends Comparable<E>> implements AbstractBinarySearchTree<E> {

    private Node<E> root;

    public BinarySearchTree() {
    }

    public BinarySearchTree(Node<E> root) {
        this.root = root;
    }

    @Override
    public void insert(E element) {
        if (this.root == null) {
            this.root = new Node<>(element);
        } else {
            insertNode(this.root, element);
        }
    }

    @Override
    public boolean contains(E element) {
        return find(element) != null;
    }

    @Override
    public AbstractBinarySearchTree<E> search(E element) {
        Node<E> node = find(element);
        return node == null ? null : new BinarySearchTree<>(node);
    }

    @Override
    public Node<E> getRoot() {
        return this.root;
    }

    @Override
    public Node<E> getLeft() {
        return getRoot().leftChild;
    }

    @Override
    public Node<E> getRight() {
        return getRoot().rightChild;
    }

    @Override
    public E getValue() {
        return getRoot().value;
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
}
