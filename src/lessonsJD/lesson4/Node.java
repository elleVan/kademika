package lessonsJD.lesson4;

public class Node {

    private Object o;
    private Node node;

    public Node() {
    }

    public Node(Object o, Node node) {
        this.o = o;
        this.node = node;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
