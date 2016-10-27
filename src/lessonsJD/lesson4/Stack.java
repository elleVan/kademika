package lessonsJD.lesson4;

import java.util.ArrayList;
import java.util.List;

public class Stack {

    private List<Object> list = new ArrayList<Object>();

    public Stack() {
    }

    public void push(Object obj) {
        list.add(obj);
    }

    public Object pop() {
        int size = list.size();
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        Object result = list.get(size - 1);
        list.remove(size - 1);
        return result;
    }

    public Object peak() {
        int size = list.size();
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        return list.get(size - 1);
    }

    public void print() {
        for (Object el : list) {
            System.out.println(el.toString());
        }
    }
}
