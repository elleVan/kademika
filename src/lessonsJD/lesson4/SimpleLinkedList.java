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

    public void addAfter(Object obj, Object prev) {
        if (root == null) {
            throw new IllegalStateException("Element is missing");
        } else {
            Node node = root;
            while (node.node != null) {
                if (node.obj == prev) {
                    break;
                }
                node = node.node;
            }
            if (node.obj != prev) {
                throw new IllegalStateException("Element is missing");
            }
            Node next = node.node;
            node.node = new Node(obj, next);
            size++;
        }
    }

    public void printList() {
        if (root != null) {
            System.out.println(root.obj.toString());
            Node node = root;
            while (node.node != null) {
                node = node.node;
                System.out.println(node.obj.toString());
            }
        }
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
