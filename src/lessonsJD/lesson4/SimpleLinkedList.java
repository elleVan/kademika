package lessonsJD.lesson4;

public class SimpleLinkedList {

    private Node root;
    private int size;

    public SimpleLinkedList() {
    }

    public void addFirst(Object o) {
        root = new Node(o, root);
        size++;
    }

    public void addLast(Object o) {
    }

    public void addAfter(Object o, Object prev) {
    }

    public int getSize() {
        return size;
    }

    private class Node {
        Object o;
        Node node;

        public Node(Object o, Node node) {
            this.o = o;
            this.node = node;
        }
    }
}
