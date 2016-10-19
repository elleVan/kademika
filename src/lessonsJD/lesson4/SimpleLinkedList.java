package lessonsJD.lesson4;

import java.util.Iterator;

public class SimpleLinkedList implements Iterable<Object> {

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

    public Iterator<Object> iterator() {
        return new SLLIterator();
    }

    private class Node {
        Object obj;
        Node node;

        public Node(Object obj, Node node) {
            this.obj = obj;
            this.node = node;
        }
    }

    private class SLLIterator implements Iterator<Object> {

        Node cp;
        Node prev;

        @Override
        public boolean hasNext() {
            return (cp == null && root != null) || (cp != null && cp.node != null);
        }

        @Override
        public Object next() {
            if (cp == null && root != null) {
                cp = root;
                return cp.obj;
            }
            if (hasNext()) {
                prev = cp;
                cp = cp.node;
                return cp.obj;
            }
            throw new IllegalStateException();
        }

        @Override
        public void remove() {
            if (cp != null && prev != null) {
                prev.node = cp.node;
            } else if (cp != null) {
                root = cp.node;
            }
        }
    }
}
