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
        if (root == null) {
            addFirst(o);
            return;
        } else {
            Node node = root;
            while (node.getNode() != null) {
                node = node.getNode();
            }
            Node newNode = new Node(o, null);
            node.setNode(newNode);
        }
        size++;
    }

    public void addAfter(Object o, Object prev) {
        if (root != null) {
            Node node = root;
            while (node.getNode() != null) {
                if (node.getO() == prev) {
                    break;
                }
                node = node.getNode();
            }
            if (node.getO() != prev) {
                return;
            }
            Node next = node.getNode();
            Node newNode = new Node(o, next);
            node.setNode(newNode);
        }
        size++;
    }

    public void printList() {
        if (root != null) {
            System.out.println(root.getO().toString());
            Node node = root;
            while (node.getNode() != null) {
                node = node.getNode();
                System.out.println(node.getO().toString());
            }
        }
    }

    public int getSize() {
        return size;
    }
}
