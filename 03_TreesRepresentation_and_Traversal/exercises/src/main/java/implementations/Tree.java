package implementations;

import interfaces.AbstractTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tree<E> implements AbstractTree<E> {

    private E key;

    private Tree<E> parent;

    private List<Tree<E>> children;

    private int counter;

    private Tree<E> result;

    private int sum;

    public Tree(E key) {
        this.key = key;
        this.children = new ArrayList<>();
    }

    @Override
    public void setParent(Tree<E> parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(Tree<E> child) {
        this.children.add(child);
    }

    @Override
    public Tree<E> getParent() {
        return this.parent;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public String getAsString() {
        StringBuilder builder = new StringBuilder();
        dfsString(this, builder, 0, "  ");
        return builder.toString().trim();
    }

    private void dfsString(Tree<E> root, StringBuilder builder, int level, String delimiter) {
        builder.append(String.join("", Collections.nCopies(level, delimiter)))
                .append(root.key)
                .append("\r\n");
        level ++;
        for (Tree<E> child : root.children) {
            dfsString(child, builder, level, delimiter);
        }
    }

    @Override
    public List<E> getLeafKeys() {
        List<E> result = new ArrayList<>();
        ArrayDeque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            Tree<E> tree = queue.poll();
            if(tree.children.isEmpty()) {
                result.add(tree.key);
            } else {
                for (Tree<E> child : tree.children) {
                    queue.offer(child);
                }
            }
        }
        return result;
    }

    @Override
    public List<E> getMiddleKeys() {
        List<E> result = new ArrayList<>();
        ArrayDeque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            Tree<E> tree = queue.poll();
            if(!tree.children.isEmpty() && tree.parent != null) {
                result.add(tree.key);
            }
            for (Tree<E> child : tree.children) {
                queue.offer(child);
            }
        }
        return result;
    }

    @Override
    public Tree<E> getDeepestLeftmostNode() {
        dfsLeftmostNode(this, 0);
        return result;
    }

    private void dfsLeftmostNode(Tree<E> root, int level) {
        if(level > counter) {
            counter = level;
            result = root;
        }
        for (Tree<E> child : root.children) {
            dfsLeftmostNode(child, level + 1);
        }
    }

    @Override
    public List<E> getLongestPath() {
        List<E> path = new ArrayList<>();
        dfsLongestPath(this, 0, path);
        return path;
    }

    private void dfsLongestPath(Tree<E> root, int level, List<E> path) {
        if(level >= path.size()) {
            if(!path.isEmpty()) {
                path.set(path.size() - 1, root.parent.key);
            }
            path.add(root.key);
        }

        for (Tree<E> child : root.children) {
            dfsLongestPath(child, level + 1, path);
        }
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {
        List<List<E>> result = new ArrayList<>();
        pathsWithGivenSum(this, 0, sum, 0, new ArrayList<>(), result);
        return result;
    }

    private void pathsWithGivenSum(Tree<E> root, int level, int sum, int currentSum,
                                   List<E> path, List<List<E>> result) {
        if(level >= path.size()) {
            path.add(root.key);
            currentSum += (Integer) root.key;
        }
        if(root.children.isEmpty()) {
            if (currentSum == sum) {
                result.add(path);
            }
            currentSum = 0;
            path = new ArrayList<>();
        }
        for (Tree<E> child : root.children) {
            pathsWithGivenSum(child, level + 1, sum, currentSum, path, result);
        }
    }

    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        List<Tree<E>> result = new ArrayList<>();
        dfsTreesWithGivenSum(this, result, sum);
        return result;
//        return bfsTreesWithGivenSum(this, sum);
    }

    private void treeSum(Tree<E> tree) {
        sum  += (int) tree.key;
        for (Tree<E> child : tree.children) {
           treeSum(child);
        }
    }

    private int getSum(Tree<E> tree) {
        sum = 0;
        treeSum(tree);
        return sum;
    }

    private void dfsTreesWithGivenSum(Tree<E> root, List<Tree<E>> result, int sum) {
        if( getSum(root) == sum) {
            result.add(root);
        }
        for (Tree<E> child : root.children) {
            dfsTreesWithGivenSum(child, result, sum);
        }
    }

    private List<Tree<E>> bfsTreesWithGivenSum(Tree<E> root, int sum) {
        List<Tree<E>> result = new ArrayList<>();
        ArrayDeque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Tree<E> tree = queue.poll();
            if(getSum(tree) == sum) {
                result.add(tree);
            }
            for (Tree<E> child : tree.children) {
                queue.offer(child);
            }
        }
        return result;
    }
}



