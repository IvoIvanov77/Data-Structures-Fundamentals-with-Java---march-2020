package solutions;

import java.util.*;
import java.util.stream.Collectors;

public class BinaryTree {

    private int key;

    private BinaryTree first;

    private BinaryTree second;

    public BinaryTree(int key, BinaryTree first, BinaryTree second) {
        this.key = key;
        this.first = first;
        this.second = second;
    }

    public Integer findLowestCommonAncestor(int first, int second) {
        BinaryTree currentCommon = null;
        Queue<BinaryTree> queue = new ArrayDeque<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            BinaryTree current = queue.poll();
            if(current.containsKey(first) && current.containsKey(second)) {
                currentCommon = current;
            }
            if(current.first != null) {
                queue.offer(current.first);
            }
            if(current.second != null) {
                queue.offer(current.second);
            }
        }
        return currentCommon == null ? null : currentCommon.key;
    }

    public List<Integer> topView() {
        Map<Integer, Coordinates> map = new HashMap<>();
        populateMap(map, this, 0, 0);
        return map.entrySet().stream()
                .filter(es -> Math.abs(es.getValue().x) >= es.getValue().y)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private boolean containsKey(int key) {
        Queue<BinaryTree> queue = new ArrayDeque<>();
        queue.offer(this);
        while (!queue.isEmpty()) {
            BinaryTree current = queue.poll();
            if (current.key == key) {
                return true;
            }
            if(current.first != null) {
                queue.offer(current.first);
            }
            if(current.second != null) {
                queue.offer(current.second);
            }
        }
        return false;
    }

    private void populateMap(Map<Integer, Coordinates> map, BinaryTree root, int x, int y) {
        if(root == null) {
            return;
        }
        map.put(root.key, new Coordinates(x, y));
        populateMap(map, root.first, x-1, y + 1);
        populateMap(map, root.second, x + 1, y +1);
    }

    private static class Coordinates {
        int x;
        int y;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
