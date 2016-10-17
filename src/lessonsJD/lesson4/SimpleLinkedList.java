package lessonsJD.lesson4;

public class SimpleLinkedList {

    private Node root;
    private int size;

    public SimpleLinkedList() {
    }

    public void addFirst(Object obj) {
        root = new Node(obj, root);
        size++;
    }

    public void addLast(Object obj) {
        if (root == null) {
            addFirst(obj);
        } else {
            Node node = root;
            while (node.node != null) {
                node = node.node;
            }
            node.node = new Node(obj, null);
            size++;
        }
    }

    public void addAfter(Object o, Object prev) {
    }

    public int getSize() {
        return size;
    }

    private class Node {
        Object obj;
        Node node;

        public Node(Object obj, Node node) {
            this.obj = obj;
            this.node = node;
        }
    }
}
