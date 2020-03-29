import java.util.List;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.insert(10);
        tree.insert(12);
        tree.insert(15);
        tree.insert(11);
        tree.insert(5);
        tree.insert(1);
        tree.insert(22);
        tree.insert(2);
        tree.insert(7);

        List<Integer> result = tree.range(11, 22);
        System.out.println(tree.ceil(11));
    }
}
