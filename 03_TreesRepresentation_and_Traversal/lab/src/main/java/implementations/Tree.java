package implementations;

import interfaces.AbstractTree;

import java.util.*;

public class Tree<E> implements AbstractTree<E> {

    private E item;

    private Tree<E> parent;

    private List<Tree<E>> children;

    public Tree(E item) {
        this.item = item;
        this.parent = null;
        this.children = new ArrayList<>();
    }

    @SafeVarargs
    public Tree(E item, Tree<E>... children) {
        this(item);
        for (Tree<E> child : children) {
            this.children.add(child);
            child.parent = this;
        }
    }

    @Override
    public List<E> orderBfs() {
        List<E> result = new ArrayList<>();
        if (this.item == null) {
            return result;
        }
        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            Tree<E> current = queue.poll();
            result.add(current.item);
            for (Tree<E> child : current.children) {
                queue.offer(child);
            }
        }

        return result;
    }

    @Override
    public List<E> orderDfs() {
        List<E> result = new ArrayList<>();
        dfs(this, result);
        return result;
    }

    private void dfs(Tree<E> tree, List<E> order) {
        for (Tree<E> child : tree.children) {
            dfs(child, order);
        }
        order.add(tree.item);
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {
        addChild(parentKey, this, child);
    }


    public void addChild(E parentKey, Tree<E> root, Tree<E> child) {
        if(root.item.equals(parentKey)){
            root.children.add(child);
            child.parent = root;
            return;
        }
        for (Tree<E> c : root.children) {
            addChild(parentKey, c, child);
        }
    }
	
	@Override
    public void removeNode(E nodeKey) {
        if(this.item.equals(nodeKey)) {
            this.item = null;
            this.children = Collections.emptyList();
        }
        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            Tree<E> current = queue.poll();
            for (Tree<E> child : current.children) {
                if (child.item.equals(nodeKey)) {
                    current.children.remove(child);
                    child.parent = null;
                    return;
                }
                queue.offer(child);
            }
        }
    }

    @Override
    public void swap(E firstKey, E secondKey) {
        Tree<E> firstNode = findNode(this, firstKey);
        Tree<E> secondNode = findNode(this, secondKey);
        if(firstNode == null || secondNode == null) {
            return;
        }
        if (firstNode.parent == null) {
            swapRoot(firstNode, secondNode);
        }else if(secondNode.parent == null) {
            swapRoot(secondNode, firstNode);
        } else {
            swapBranches(firstNode, secondNode);
        }
    }

    private Tree<E> findNode(Tree<E> root, E key) {
        if(root.item.equals(key)) {
            return root;
        }
        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Tree<E> current = queue.poll();
            for (Tree<E> child : current.children) {
                if (child.item.equals(key)) {
                    return child;
                }
                queue.offer(child);
            }
        }
        return null;
    }

    private void swapRoot(Tree<E> root, Tree<E> child) {
        root.children = child.children;
        root.item = child.item;

        child.parent.children.remove(child);
        child.parent = null;
        child.children = null;
        child.item = null;
    }

    private void swapBranches(Tree<E> firstNode, Tree<E> secondNode) {
        Tree<E> firstParent = firstNode.parent;
        Tree<E> secondParent = secondNode.parent;
        int firstNodeIndex = firstParent.children.indexOf(firstNode);
        int secondNodeIndex = secondParent.children.indexOf(secondNode);

        firstNode.parent = secondParent;
        secondParent.children.set(secondNodeIndex, firstNode);
        secondNode.parent = firstParent;
        firstParent.children.set(firstNodeIndex, secondNode);
    }
}



