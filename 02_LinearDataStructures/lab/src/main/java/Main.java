import implementations.Queue;

public class Main {
    public static void main(String[] args) {
        Queue<String> queue = new Queue<>();
        queue.offer("1");
        queue.offer(("2"));
        queue.offer("3");
        System.out.println(queue.poll());
        System.out.println();
        queue.offer("4");
        queue.offer("5");
        for (String s : queue) {
            System.out.println(s);
        }
    }
}
