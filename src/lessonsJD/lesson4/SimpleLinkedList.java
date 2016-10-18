package lessonsJD.lesson4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList implements Iterable {

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

    public Iterator iterator() {
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

    private class SLLIterator implements Iterator {

        int cursor;

        public boolean hasNext() {
            return cursor != size;
        }

        public Node next() {
            if (cursor + 1 <= size) {
                Node node = root;
                for (int i = 1; i <= cursor; i++) {
                    node = node.node;
                }
                cursor++;
                return node;
            }
            throw new NoSuchElementException();
        }
    }
}
