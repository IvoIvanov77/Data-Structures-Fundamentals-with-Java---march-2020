package implementations;

import java.util.LinkedHashMap;
import java.util.Map;

public class TreeFactory {
    private Map<Integer, Tree<Integer>> nodesByKeys;

    public TreeFactory() {
        this.nodesByKeys = new LinkedHashMap<>();
    }

    public Tree<Integer> createTreeFromStrings(String[] input) {
        for (String s : input) {
            String[] pair = s.split("\\s+");
            int parent = Integer.valueOf(pair[0]);
            int child = Integer.valueOf(pair[1]);
            createNodeByKey(parent);
            createNodeByKey(child);
            addEdge(parent, child);

        }
        return getRoot();
    }

    private Tree<Integer> getRoot() {
        for (Integer key : nodesByKeys.keySet()) {
            Tree<Integer> tree = nodesByKeys.get(key);
            if(tree.getParent() == null) {
                return tree;
            }
        }
        return null;
    }

    public Tree<Integer> createNodeByKey(int key) {
        nodesByKeys.putIfAbsent(key, new Tree<>(key));
        return nodesByKeys.get(key);
    }

    public void addEdge(int parent, int child) {
        nodesByKeys.get(parent).addChild(nodesByKeys.get(child));
        nodesByKeys.get(child).setParent(nodesByKeys.get(parent));
    }
}



